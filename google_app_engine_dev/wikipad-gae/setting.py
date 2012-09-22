# -*- coding: utf-8 -*-

from datetime import timedelta
import os
import re

APPID = os.environ['APPLICATION_ID']
BLOG_HOME_RELATIVE_PATH = '/'
BLOG_ADMIN_RELATIVE_PATH = os.path.join(BLOG_HOME_RELATIVE_PATH, 'admin/')
MAJOR_DOMAIN = os.environ['HTTP_HOST']
MAJOR_HOST_URL = 'http://' + MAJOR_DOMAIN
FEED_DOMAIN = ''
MAJOR_HOST_URL = 'http://' + MAJOR_DOMAIN
if FEED_DOMAIN:
	BLOG_FEED_URL = 'http://%s/' % FEED_DOMAIN
else:
	BLOG_FEED_URL = MAJOR_HOST_URL + '/feed'

ONLY_USE_MAJOR_DOMAIN = True

CATEGORY_PATH_DELIMETER = '/'

TEMPLATE_RELATIVE_PATH = os.path.join('static','template')

ARTICLE_CACHE_TIME = 600
ARTICLES_CACHE_TIME = 600
SIDEBAR_CACHE_TIME = 600
HOT_ARTICLES_CACHE_TIME = 600
CATEGORY_CACHE_TIME = 600
TAGS_CACHE_TIME = 600

FEED_CACHE_TIME = 3600
SITEMAP_CACHE_TIME = 3600

SUBSCRIBER_CACHE_TIME = 21600
SUBSCRIBERS_CACHE_TIME = 86400

GAE_FETCH_LIMIT = 1000

OUTPUT_FULLTEXT_FOR_FEED = True
HUBS = ['http://pubsubhubbub.appspot.com/']
XML_RPC_ENDPOINTS = ['http://blogsearch.google.com/ping/RPC2', 'http://rpc.pingomatic.com/', 'http://ping.baidu.com/ping/RPC2']

COUNTER_TASK_DELAY = 60

ARTICLE_URL_PATTERN = re.compile(r'\d{4}/\d{2}/\d{2}/.+')
EMAIL_PATTER = re.compile(r'[\w\-\.+]+@(\w[\w\-]+\.)+[\w\-]+')
SUMMARY_DELIMETER = re.compile(r'\s*\<hr id="read-more" \/\>\s*')

BLOG_TITLE = u"Pythonee's Wikipad"
BLOG_SUB_TITLE = u"Pythonee's Wikipad"
BLOG_DESCRIPTION = BLOG_SUB_TITLE
BLOG_AUTHOR = 'pythonee'

ARTICLES_PER_PAGE = 10
HOT_ARTICLES_LIMIT = 8
LOCAL_TIME_ZONE = 'GMT-0'
LOCAL_TIME_DELTA = timedelta(hours=8)
DATE_FORMAT = '%Y-%m-%d'
MINUTE_FORMAT = '%Y-%m-%d %H:%M'
SECONDE_FORMAT = '%Y-%m-%d %H:%M:%S'

#TO filter mail from this app
ADMIN_EMAIL = "pythonee+wikipad@gmail.com"

LANGUAGE = 'zh-CN'
GOOGLE_CSE_ID = '003314338486782386267:zjpdvwkibto'
GOOGLE_ANALYTICS_ID = 'UA-27558213-1'
GOOGLE_CALENDAR_PRIVATE_FEED = 'https://www.google.com/calendar/feeds/h49dmr7d45f2o6v9rnkvag8h0c%40group.calendar.google.com/private/full'
