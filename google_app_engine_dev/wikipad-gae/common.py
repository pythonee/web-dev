# -*- coding: utf-8 -*-

import yui
import re
import os
import json
import traceback
import tenjin
import tenjin.gae;
import  pywapi
import string

from tenjin.helpers import *
from tenjin.helpers.html import *

from itertools import izip

from datetime import datetime, timedelta, tzinfo

from setting import *

import logging
logger = logging.getLogger()
logger.setLevel(logging.INFO)

from urllib import quote, unquote, urlencode

from datetime import datetime

from google.appengine.api import memcache, mail, urlfetch, taskqueue
from google.appengine.api import users
from google.appengine.ext.webapp import util
from google.appengine.ext import db,deferred
from google.appengine.datastore import datastore_query
from google.appengine.ext.appstats import recording

tenjin.gae.init()

TEMPLATE_ROOT = os.path.join(os.path.dirname(__file__)
        ,TEMPLATE_RELATIVE_PATH
        )

tenjin_config = {
        "path":  [TEMPLATE_ROOT,
                  os.path.join(TEMPLATE_ROOT, 'admin')
                 ],
        "preprocess": True,
        }

engine = tenjin.Engine(**tenjin_config)
fragcache = tenjin.helpers.fragment_cache
tenjin.logger = logger

memcache_client = memcache.Client()

def memcached(key, cache_time=0, key_suffix_calc_func=None):
    '''
    用memcache缓存函数返回结果

    @type key: str
    @param key: 用作memcache的key

    @type cache_time: int
    @param cache_time: 缓存的秒数

    @type key_suffix_calc_func: function
    @param key_suffix_calc_func: 计算key的附加后缀，本身或运算结果为None则认为无附加后缀

    @warning: 注意key_suffix_calc_func的参数必须和被缓存函数的参数一致，包括命名、顺序和缺省值。
    '''
    def wrap(func):
        def cached_func(*args, **kw):
            key_with_suffix = key

            if key_suffix_calc_func:
                key_suffix = key_suffix_calc_func(*args, **kw)
                if key_suffix is not None:
                    key_with_suffix = '%s:%s' % (key, key_suffix)

            value = memcache.get(key_with_suffix)
            if value is None:
                value = func(*args, **kw)
                memcache_client.set_multi_async({key_with_suffix: value}, cache_time)
            return value
        return cached_func
    return wrap

def incr_counter(key, url, delta=1):
    '''
    用memcache缓存计数

    @type key: str
    @param key: 用作memcache的key

    @type url: str
    @param url: 用作taskqueue的URL

    @type delta: int
    @param delta: 用作memcache.incr()的delta
    '''
    memcache.incr(key, delta, initial_value=0)
    lock_key = 'lock:' + key
    if not memcache.get(lock_key):
        memcache.set(lock_key, 1, COUNTER_TASK_DELAY - 1)
        try:
            taskqueue.add(queue_name='counter', url=url, params={'key': key}, countdown=COUNTER_TASK_DELAY)
        except:
            pass

def reverse(cursor):
  return datastore_query.Cursor.from_websafe_string(cursor).reversed().to_websafe_string()

def cache_articles(articles):
    size = len(articles)
    if size:
        last = size - 1
        mapping = {}
        for i in xrange(size):
            article = articles[i]
            id = article.key().id()
            published = article.published
            mapping['get_article_by_url:%s' % hash(article.url)] = article
            mapping['get_article_by_id:%s' % id] = article
            if i > 0:
                mapping['get_next_article:%s_%s' % (id, published)] = articles[i - 1]
            if i < last:
                mapping['get_previous_article:%s_%s' % (id, published)] = articles[i + 1]
        memcache_client.set_multi_async(mapping, ARTICLE_CACHE_TIME)

def clear_article_memcache(id=None, url=None):
    keys = ['get_articles_for_feed','get_articles_for_homepage']
    if id:
        keys.append('get_article_by_id:%s' % id)
        fragcache.store.delete('article:%s' % id)
    if url:
        keys.append('get_article_by_url:%s' % hash(url))
    memcache_client.delete_multi_async(keys)
    yui.flush_all_server_cache()

def clear_tags_memcache():
    memcache.delete('get_all_tags')

def clear_categories_memcache():
    memcache.delete('get_all_categories')

def log_request(app):
    def wrap(environ, start_response):
        memcache_client.incr_async('dynamic_requests', initial_value=0)
        return app(environ, start_response)
    return wrap

class BaseHandler(yui.HtmlRequestHandler):

    global TEMPLATE_ROOT,MAKO_LOOKUP

    def before(self, *args, **kw):
        super(BaseHandler, self).before(*args, **kw)
        request = self.request


    def render(self, template, context=None, globals=None, layout=None):
        if context is None:
            context = {'request': self.request}
        else:
            context['request'] = self.request

        return engine.render(template, context, globals, layout)

    def echo(self, template, context=None, globals=None, layout=None):
        self.write(self.render(template, context, globals, layout))

    def is_spider(self):
        user_agent = self.request.user_agent.lower()
        if 'bot' in user_agent or 'spider' in user_agent:
            return True
        return False

def fetch_with_cursor(query, fetch_limit, config=None):
    entities = query.fetch(fetch_limit, config=config)
    cursor = query.cursor()

    if len(entities) < fetch_limit or query.with_cursor(cursor).count(1) == 0:
        return entities,None
    else:
        return entities,cursor

def query_with_cursor(query, cursor):
    try:
        if cursor:
            query.with_cursor(cursor)
    except:
        pass
    return query

if LOCAL_TIME_ZONE:
    import pytz

    LOCAL_TIMEZONE = pytz.timezone(LOCAL_TIME_ZONE)
    UTC = pytz.utc

    def convert_to_local_time(dt):
        if dt.tzinfo:
            return LOCAL_TIMEZONE.normalize(dt)
        else:
            return LOCAL_TIMEZONE.normalize(UTC.localize(dt))

    def parse_time(time_string):
        try:
            dt = LOCAL_TIMEZONE.localize(datetime.strptime(time_string, '%Y-%m-%d %H:%M:%S')).astimezone(UTC).replace(tzinfo=None)
            return dt if dt.year >= 1900 else None # the datetime strftime() method requires year >= 1900
        except:
            return None
else:
    ZERO_TIME_DELTA = timedelta(0)

    class LocalTimezone(tzinfo):
        def utcoffset(self, dt):
            return LOCAL_TIME_DELTA

        def dst(self, dt):
            return ZERO_TIME_DELTA

    LOCAL_TIMEZONE = LocalTimezone()

    class UTC(tzinfo):
        def utcoffset(self, dt):
            return ZERO_TIME_DELTA

        def dst(self, dt):
            return ZERO_TIME_DELTA

    UTC = UTC()

    def convert_to_local_time(dt):
        if dt.tzinfo:
            return dt.astimezone(LOCAL_TIMEZONE)
        else:
            return dt.replace(tzinfo=UTC).astimezone(LOCAL_TIMEZONE)

    def parse_time(time_string):
        try:
            dt = datetime.strptime(time_string, '%Y-%m-%d %H:%M:%S').replace(tzinfo=LOCAL_TIMEZONE).astimezone(UTC).replace(tzinfo=None)
            return dt if dt.year >= 1900 else None # the datetime strftime() method requires year >= 1900
        except:
            return None

def get_local_now():
    return datetime.now(LOCAL_TIMEZONE)

def formatted_date(dt):
    return convert_to_local_time(dt).strftime(DATE_FORMAT)

def formatted_date_for_url(dt=None):
    if not dt:
        return get_local_now().strftime('%Y/%m/%d/')
    return convert_to_local_time(dt).strftime('%Y/%m/%d/')

def formatted_time(dt, display_second=True):
    return convert_to_local_time(dt).strftime(SECONDE_FORMAT if display_second else MINUTE_FORMAT)

def formatted_time_for_edit(dt):
    return convert_to_local_time(dt).strftime('%Y-%m-%d %H:%M:%S')

def sitemap_time_format(dt):
	if dt.tzinfo:
		dt = dt.astimezone(UTC)
	return dt.strftime('%Y-%m-%dT%H:%M:%S+00:00')

ISO_TIME_FORMAT = '%Y-%m-%dT%H:%M:%SZ'

def iso_time_format(dt):
    if dt.tzinfo:
        return convert_to_local_time(dt).strftime(ISO_TIME_FORMAT)
    else:
        return dt.strftime(ISO_TIME_FORMAT)

def iso_time_now():
    return datetime.utcnow().strftime(ISO_TIME_FORMAT)

def replace_special_chars_for_url(text):
    text = re.sub(u'[^\\w\u4e00-\u9fa5]+', ' ', text).strip()
    return re.sub('[\s]+','-',text)

def quoted_url(url, coding='utf-8'):
    return quote(url.encode(coding))

def unquoted_url(url, coding='utf-8'):
    return unquote(url).decode(coding, 'ignore')

def quoted_cursor(cursor, coding='utf-8'):
    return quote(cursor.encode(coding), '_.-+/=~,;&:!*$()')

def unquoted_cursor(cursor):
    if cursor:
        return unquote(cursor)
    return None

def strip(s):
    return s.strip() if s else s

SUBSCRIBER_PATTERN = re.compile(r'(\d+) subscriber', re.I)
def get_subscribers_from_ua(user_agent):
    match = SUBSCRIBER_PATTERN.search(user_agent)
    if match:
        return int(match.group(1))
    return 0

def ping_hubs(feed):
    data = urlencode({'hub.url': feed, 'hub.mode': 'publish'})
    rpcs = []
    for hub in HUBS:
        rpc = urlfetch.create_rpc(10)
        urlfetch.make_fetch_call(rpc, hub, data, urlfetch.POST)
        rpcs.append(rpc)
    for rpc in rpcs:
        try:
            rpc.wait()
        except:
            pass

def ping_xml_rpc(article_url):
    xml = engine.render('XML-RPC.xml', {'article_url': article_url})
    rpcs = []
    for endpoint in XML_RPC_ENDPOINTS:
        rpc = urlfetch.create_rpc(10)
        urlfetch.make_fetch_call(rpc, endpoint, xml, urlfetch.POST)
        rpcs.append(rpc)
    for rpc in rpcs:
        try:
            rpc.wait()
        except:
            pass

