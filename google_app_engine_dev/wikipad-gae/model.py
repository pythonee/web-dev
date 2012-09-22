# -*- coding: utf-8 -*-

from common import *

import becer

class EntityNotFound(object):
    def __nonzero__(self):
        return False

ENTITY_NOT_FOUND = EntityNotFound()
EVENTUAL_CONSISTENCY_CONFIG = db.create_config(read_policy=db.EVENTUAL_CONSISTENCY)
QUICK_LIMITED_EVENTUAL_CONSISTENCY_CONFIG = db.create_config(deadline=0.5, read_policy=db.EVENTUAL_CONSISTENCY)

class Article(becer.Model):
    title = db.StringProperty()
    time = db.DateTimeProperty(auto_now_add=True)
    mod_time = db.DateTimeProperty(auto_now_add=True)
    content = db.TextProperty()
    url = db.StringProperty()
    published = db.BooleanProperty(default=True)
    tags = db.StringListProperty()
    category = db.StringProperty()
    hits = db.IntegerProperty(default=0)

    @staticmethod
    @memcached('get_articles_for_homepage', ARTICLES_CACHE_TIME,
				lambda cursor=None, fetch_limit=ARTICLES_PER_PAGE: hash(cursor) if cursor else None)
    def get_articles_for_homepage(cursor=None, fetch_limit=ARTICLES_PER_PAGE):
        query = Article.all().filter('published =', True).order('-time')
        articles, cursor = fetch_with_cursor(query_with_cursor(query, cursor),
                                            fetch_limit,
                                            config=EVENTUAL_CONSISTENCY_CONFIG)
        cache_articles(articles)
        return articles,cursor

    @staticmethod
    def forward_query(cursor=None, fetch_limit=ARTICLES_PER_PAGE):
        query = Article.all().filter('published =', True).order('-time')
        articles, cursor = fetch_with_cursor(query_with_cursor(query, cursor),
                                            fetch_limit,
                                            config=EVENTUAL_CONSISTENCY_CONFIG)
        return articles,cursor

    @staticmethod
    def backward_query(cursor=None, fetch_limit=ARTICLES_PER_PAGE):
        query = Article.all().filter('published =', True).order('time')
        articles, cursor = fetch_with_cursor(query_with_cursor(query, cursor),
                                            fetch_limit,
                                            config=EVENTUAL_CONSISTENCY_CONFIG)
        return articles,cursor

    @staticmethod
    @memcached('get_article_by_url', ARTICLE_CACHE_TIME, lambda url: hash(url))
    def get_by_url(url):
        if len(url) <= 500:
            article = Article.all().filter('url =',url).filter('published =',
                    True).get(config=EVENTUAL_CONSISTENCY_CONFIG)

            if article:
                memcache_client.set_multi_async(
                        {'get_article_by_id:%s' %article.key().id() : article}
                        ,ARTICLE_CACHE_TIME)
                return article

            return ENTITY_NOT_FOUND

    @staticmethod
    @memcached('get_article_by_id', ARTICLE_CACHE_TIME, lambda id: id)
    def get_article_by_id(id):
        if id > 0:
            article = Article.get_by_id(id)
            if article:
                memcache_client.set_multi_async(
                        {'get_article_by_url:%s' % hash(article.url): article},
                        ARTICLE_CACHE_TIME)
                return article
        return ENTITY_NOT_FOUND

    @staticmethod
    def get_unpublished_articles(cursor=None, fetch_limit=ARTICLES_PER_PAGE):
        query = Article.all().filter('published =', False).order('-mod_time')
        articles, cursor = fetch_with_cursor(query_with_cursor(query, cursor), fetch_limit)
        cache_articles(articles)
        return articles, cursor

    @staticmethod
    def remove(article_id):
        if article_id > 0:
            article = Article.get_by_id(article_id)
            if article:
                try:
                    article.delete()
                except:
                    return False
            return True
        return False

    def category_name(self):
        return Category.path_to_name(self.category)

    def html_teaser(self):
        content = self.content
        teaser = SUMMARY_DELIMETER.split(content,1)[0]
        return teaser

    def html_content(self):
        content = self.content
        content = ''.join(SUMMARY_DELIMETER.split(content,1))
        return content

    def is_not_end(self):
        if self.html_teaser() < self.html_content():
            return True
        return False

    def previous_article(self, published):
        previous_article = None
        try:
            previous_article = Article.all().filter('published =',
                    published).filter('time <',
                            self.time).order('-time').get(config=QUICK_LIMITED_EVENTUAL_CONSISTENCY_CONFIG)
            if previous_article:
                memcache.set_multi({
                    'get_previous_article:%s_%s' % (self.key().id(), published): previous_article,
                    'get_next_article:%s_%s' % (previous_article.key().id(), published): self,
                    'get_article_by_url:%s' % hash(previous_article.url): previous_article,
                    'get_article_by_id:%s' % previous_article.key().id(): previous_article
                    }, ARTICLE_CACHE_TIME)
                return previous_article
            memcache.set('get_previous_article:%s_%s' % (self.key().id(), published), ENTITY_NOT_FOUND, ARTICLE_CACHE_TIME)
            return ENTITY_NOT_FOUND
        except:
            return previous_article

    def next_article(self, published):
        next_article = None
        try:
            next_article = Article.all().filter('published =',
                    published).filter('time >',
                            self.time).order('time').get(config=QUICK_LIMITED_EVENTUAL_CONSISTENCY_CONFIG)
            if next_article:
                memcache.set_multi({
                    'get_next_article:%s_%s' % (self.key().id(), published): next_article,
                    'get_previous_article:%s_%s' % (next_article.key().id(), published): self,
                    'get_article_by_url:%s' % hash(next_article.url): next_article,
                    'get_article_by_id:%s' % next_article.key().id(): next_article
                    }, ARTICLE_CACHE_TIME)
                return next_article
            memcache.set('get_next_article:%s_%s' % (self.key().id(), published), ENTITY_NOT_FOUND, ARTICLE_CACHE_TIME)
            return ENTITY_NOT_FOUND
        except:
            return next_article

    def nearby_articles(self, published=True):
        key = '%s_%s' % (self.key().id(), published)
        previous_key = 'get_previous_article:' + key
        next_key = 'get_next_article:' + key
        nearby_articles = memcache.get_multi((next_key, previous_key))

        previous_article = nearby_articles.get(previous_key, None)
        if previous_article is None:
            previous_article = self.previous_article(published)

        next_article = nearby_articles.get(next_key, None)
        if next_article is None:
            next_article = self.next_article(published)
        return previous_article, next_article

    @staticmethod
    def calc_hits(key):
        article_id = int(key.split(':')[1])
        if article_id <= 0:
            return True
        def calc():
            try:
                hits = memcache.get(key)
                if hits:
                    hits = int(hits)
                    if hits:
                        article = Article.get_by_id(article_id)
                        if article:
                            article.hits += hits
                            article.put()
                            memcache.decr(key, hits)
                return True
            except:
                return False
        return db.run_in_transaction(calc)

    @classmethod
    def SearchableProperties(cls):
        return [['content','title']]

    @staticmethod
    @memcached('get_articles_for_feed', FEED_CACHE_TIME)
    def get_articles_for_feed(fetch_limit=ARTICLES_PER_PAGE):
        return Article.all().filter('published =', True).order('-mod_time').fetch(fetch_limit)

    @staticmethod
    @memcached('get_hot_articles_for_sidebar', HOT_ARTICLES_CACHE_TIME)
    def get_hot_articles_for_sidebar(fetch_limit=HOT_ARTICLES_LIMIT):
        articles = Article.all().filter('published =',
                True).order('-hits').order('-time').fetch(fetch_limit)
        cache_articles(articles)
        return articles

class Category(db.Model):
    path = db.StringProperty()

    _LIMIT = GAE_FETCH_LIMIT

    @staticmethod
    @memcached('get_all_categories', CATEGORY_CACHE_TIME)
    def get_all_categories(limit=_LIMIT):
        return Category.all().order('path').fetch(limit)

    @memcached('get_articles_in_category', ARTICLES_CACHE_TIME,
            lambda self, cursor=None, fetch_limit=ARTICLES_PER_PAGE:
            ('%s_%s' % (self.key().name(), cursor)) if cursor else self.key().name())
    def get_articles(self, cursor=None, fetch_limit=ARTICLES_PER_PAGE):
        path = self.path
        query = Article.all().filter('published =', True).filter('category >=',
                path).filter('category <', path + u'\ufffd').order('category').order('-time')
        articles, cursor = fetch_with_cursor(query_with_cursor(query, cursor), fetch_limit, config=EVENTUAL_CONSISTENCY_CONFIG)
        cache_articles(articles)
        return articles, cursor

    @staticmethod
    def path_to_name(path):
        if path:
            return path.rstrip(CATEGORY_PATH_DELIMETER).rsplit(CATEGORY_PATH_DELIMETER, 1)[-1]
        return ''

    @staticmethod
    def fill_pathes(path):
        if not path:
            return []
        names = path.split(CATEGORY_PATH_DELIMETER)
        each_path = ''
        all_pathes = []
        for name in names:
            name = name.strip()
            if not name:
                continue
            each_path += name + CATEGORY_PATH_DELIMETER
            all_pathes.append((each_path, name))
        return all_pathes

    def level(self):
        return len(self.path.split(CATEGORY_PATH_DELIMETER)) - 1

    def has_sub_categories(self):
        path = self.path
        return Category.all().filter('path >', path).filter('path <', path + u'\ufffd').count(1)

def delete_category(path):
    articles = Article.all().filter('category >=', path).filter('category <',
            path + u'\ufffd').fetch(100)
    if not articles:
        try:
            sub_cates = Category.all().filter('path >', path).filter('path <',
                    path+ u'\ufffd').fetch(GAE_FETCH_LIMIT)
            for sub_cate in sub_cates:
                db.delete(db.Key.from_path('Category',
                    Category.path_to_name(sub_cate.path)))

            db.delete(db.Key.from_path('Category', Category.path_to_name(path)))

            clear_categories_memcache()
            fragcache.store.delete('siderbar')
            mail.send_mail_to_admins(ADMIN_EMAIL, u'Delete Category Done, path: %s'
                    % path, u'Path：' + path)
            return
        except:
            mail.send_mail_to_admins(ADMIN_EMAIL, u'Delete Category Fail, path: %s'
                    % path, u'Path：%s\n Traceback %s' %(path, traceback.format_exc()))
            return
    for article in articles:
        article.category = None
        clear_article_memcache(id=article.key().id())
    try:
        db.put(articles)
        deferred.defer(delete_category, path)
    except:
        mail.send_mail_to_admins(ADMIN_EMAIL, u'Delete Category Fail, path: %s'
                % path, u'When update articles in category %s\n, Traceback %s' %(path, traceback.format_exc()))
        return

def rename_category(old_path, new_path, cursor=None):
    query = Article.all().filter('category >=', old_path).filter('category <', old_path + u'\ufffd')
    articles_in_old_cate = query_with_cursor(query, cursor).fetch(100)

    if not articles_in_old_cate:

        _LIMIT = GAE_FETCH_LIMIT
        try:
            sub_cate_of_old = Category.all().filter('path >', old_path).filter('path <', old_path + u'\ufffd').fetch(_LIMIT)

            for sub_cate in sub_cate_of_old:
                sub_cate.path = sub_cate.path.replace(old_path, new_path)

            db.put(sub_cate_of_old)

            db.delete(db.Key.from_path('Category', Category.path_to_name(old_path)))

            path_with_name = Category.fill_pathes(new_path)
            for each_path, name in path_with_name:
                if Category.all().filter('path =', each_path).count(1):
                    continue
                category = Category.get_by_key_name(name)
                if category and category.path != each_path:
                    logger.error((u'category name %s have existed in path %s' % (name, category.path)).encode('utf-8'))
                    mail.send_mail_to_admins(ADMIN_EMAIL, u'Rename Category Fail, from path: \
                        %s to path %s' %(old_path, new_path),(u'category name %s \
                        have existed in path %s' % (name, category.path)).encode('utf-8'))
                    break
                Category.get_or_insert(key_name=name, path=each_path)

            clear_categories_memcache()
            fragcache.store.delete('siderbar')

            mail.send_mail_to_admins(ADMIN_EMAIL, u'Rename Category Done, from path: \
                    %s to path %s' %(old_path, new_path), u'from path : %s to %s'
                    %(old_path, new_path))
            return
        except:
            mail.send_mail_to_admins(ADMIN_EMAIL, u'Rename Category Fail, from path: \
                    %s to path %s' %(old_path, new_path), u'from path : %s to %s\n Traceback: %s'
                    %(old_path, new_path, traceback.format_exc()))
            return

    for article in articles_in_old_cate:
        existed_cate = article.category
        article.category = existed_cate.replace(existed_cate, new_path)
        clear_article_memcache(id = article.key().id())
    try:
        db.put(articles_in_old_cate)
        deferred.defer(rename_category, old_path, new_path, query.cursor())
    except:
        mail.send_mail_to_admins(ADMIN_EMAIL, u'Rename Category Fail, from path: \
                %s to path %s' %(old_path, new_path), u'from path : %s to %s\n \
                When update the articles in existed category %s \n Traceback: %s'
                %(old_path, new_path, old_path, traceback.format_exc()))
        return

def sync_cate(categories=None, cursor=None):
    if not categories:
        categories = set()
    query = Article.all()
    articles = query_with_cursor(query, cursor).fetch(100)
    categories |= set([article.category for article in articles if (article.category and
        len(article.category) >= 2 and article.category[-1] ==
        CATEGORY_PATH_DELIMETER)])

    if len(articles) == 100:
        deferred.defer(sync_cate, categories, query.cursor())
    elif categories:
        category_pathes = []
        category_names = []
        for category in list(categories):
            if category:
                category_pathes.append(category)
                category_names.append(Category.path_to_name(category))
        if category_pathes:
            categories = Category.get_by_key_name(category_names)
            new_categories = [Category(key_name=category_name,
                path=category_path) for category_name, category_path, category
                in izip(category_names, category_pathes, categories) if not
                category]
            try:
                db.put(new_categories)
                clear_categories_memcache()
                mail.send_mail_to_admins(ADMIN_EMAIL, 'Sync Cate Done', 'Sync %s Cates Done' % len(new_categories))
                return
            except:
                mail.send_mail_to_admins(ADMIN_EMAIL, 'Sync Cate Fail', 'Sync \
                        %s Cates Fail\n Traceback: %s' %(len(new_categories),
                            traceback.format_exc()))
                return

class Tag(db.Model):
    count = db.IntegerProperty(default=0)

    _LIMIT = GAE_FETCH_LIMIT

    @staticmethod
    @memcached('get_all_tags', TAGS_CACHE_TIME)
    def get_all_tags(more_than=0, limit=_LIMIT):
        return Tag.all().filter('count >=', more_than).fetch(limit)

    @memcached('get_articles_in_tag', ARTICLES_CACHE_TIME,
            lambda self, cursor=None, fetch_limit=ARTICLES_PER_PAGE:
            ('%s_%s' % (self.key().name(), cursor)) if cursor else self.key().name())
    def get_articles(self, cursor=None, published=True, fetch_limit=ARTICLES_PER_PAGE):
        query = Article.all().filter('tags =',
                self.key().name()).filter('published =', published).order('-time')
        articles, cursor = fetch_with_cursor(query_with_cursor(query, cursor), fetch_limit, config=EVENTUAL_CONSISTENCY_CONFIG)
        cache_articles(articles)
        return articles, cursor

    def update_count(self):
        self.count = Article.all().filter('tags =', self.key().name()).filter('published =', True).count(None)
        self.put()
        return self.count

def sync_tag(tags=None, cursor=None):
    if not tags:
        tags = {}
	query = Article.all()
	articles = query_with_cursor(query, cursor).fetch(100)

    for article in articles:
        for tag_name in article.tags:
            if tag_name in tags:
                tags[tag_name] += 1
            else:
                tags[tag_name] = 1

    if len(articles) == 100:
        deferred.defer(sync_tag, tags, query.cursor())
    else:
        all_tag_names = list(tags)

        existed_tags = Tag.all().fetch(GAE_FETCH_LIMIT)
        existed_tag_names = [tag.key().name() for tag in existed_tags]

        new_tag_names = set(all_tag_names) - set(existed_tag_names)
        new_tag_names.discard('')
        new_tag_names = list(new_tag_names)
        new_tags = []
        for tag_name in new_tag_names:
            new_tag = Tag(key_name=tag_name)
            new_tag.count = tags[tag_name]
            new_tags.append(new_tag)

        need_to_update = set(all_tag_names).intersection(set(existed_tag_names))
        need_to_update.discard('')
        need_to_update = list(need_to_update)
        tags_need_to_update = []
        for tag_name in need_to_update:
            tag = Tag.get_by_key_name(tag_name)
            tag.count = tags[tag_name]
            tags_need_to_update.append(tag)

        need_to_reset = set(existed_tag_names) - set(all_tag_names)
        need_to_reset.discard('')
        need_to_reset = list(need_to_reset)
        tags_need_to_reset = []
        for tag_name in need_to_reset:
            tag = Tag.get_by_key_name(tag_name)
            tag.count = 0
            tags_need_to_reset.append(tag)

        all_need_to_sync = new_tags + tags_need_to_update + tags_need_to_reset

        try:
            db.put(all_need_to_sync)
            clear_tags_memcache()
            fragcache.store.delete('siderbar')
            mail.send_mail_to_admins(ADMIN_EMAIL, 'Sync Tag Done', 'Sync %s Tags Done' % len(all_need_to_sync))
            return
        except:
            mail.send_mail_to_admins(ADMIN_EMAIL, 'Sync Tag Fail', 'Sync %s \
                    Tags Fail\n Traceback: %s' % (len(all_need_to_sync),
                        traceback.format_exc()))
            return

def rename_tag(src_name, dest_name, cursor=None):
    query = Article.all().filter('tags =', src_name)
    articles = query_with_cursor(query, cursor).fetch(100)

    if not articles:
        try:
            src_tag = Tag.get_by_key_name(src_name)
            db.delete(src_tag)
            tags = Tag.all().fetch(GAE_FETCH_LIMIT)
            tag_names = [tag.key().name() for tag in tags]
            if dest_name not in tag_names:
                dest_tag = Tag(key_name = dest_name)
            else:
                dest_tag = Tag.get_by_key_name(dest_name)

            dest_tag.update_count()
            db.put(dest_tag)

            clear_tags_memcache()
            fragcache.store.delete('siderbar')

            mail.send_mail_to_admins(ADMIN_EMAIL, 'Rename Tag Done', 'from %s to %s' %(src_name, dest_name))
            return
        except:
            mail.send_mail_to_admins(ADMIN_EMAIL, 'Rename Tag Fail', 'from %s \
                    to %s\n Traceback: %s' %(src_name, dest_name,
                        traceback.format_exc()))
            return

    for article in articles:
        tags = article.tags
        while src_name in tags:
            tags.remove(src_name)
        if dest_name not in tags:
            tags.append(dest_name)
        clear_article_memcache(id=article.key().id())

    try:
        db.put(articles)
        deferred.defer(rename_tag, src_name, dest_name, query.cursor())
    except:
        mail.send_mail_to_admins(ADMIN_EMAIL, 'Rename Tag Fail', 'from %s \
                to %s\n When update articles in tags \n Traceback: %s' %(src_name, dest_name,
                    traceback.format_exc()))

        return

def delete_tag(tag_name, cursor=None):
    query = Article.all().filter('tags =', tag_name)
    articles = query_with_cursor(query, cursor).fetch(100)

    if not articles: # finish flag
        try:
            db.delete(db.Key.from_path('Tag', tag_name))
            clear_tags_memcache()
            clear_article_memcache()
            mail.send_mail_to_admins(ADMIN_EMAIL, 'Delete Tag %s Done' %tag_name ,
                    'delete tag %s done' %tag_name);
            return
        except:
            mail.send_mail_to_admins(ADMIN_EMAIL, 'Delete Tag %s Fail' %tag_name ,
                    'delete tag %s Fail\n Traceback: %s' %(tag_name,
                        traceback.format_exc()));

            return

    for article in articles:
        tags = article.tags
        while tag_name in tags:
            tags.remove(tag_name)
        article.tags = tags
        clear_article_memcache(id=article.key().id())
    try:
        db.put(articles)
        deferred.defer(delete_tag, tag_name, query.cursor())
    except:
        mail.send_mail_to_admins(ADMIN_EMAIL, 'Delete Tag %s Fail' %tag_name ,
                'delete tag %s Fail\n When update articles in tags\n Traceback: %s' %(tag_name,
                    traceback.format_exc()));
        return

class Sitemap(db.Model):
    # key id
    content = db.TextProperty()

    _LIMIT = GAE_FETCH_LIMIT

    @staticmethod
    def fill(id, cursor=None, fetch_limit=_LIMIT):
        query = Article.all().filter('published =', True)
        articles = query_with_cursor(query, cursor).fetch(fetch_limit)
        if articles:
            content = engine.render('sitemap.xml', {'articles': articles})
            Sitemap(key=db.Key.from_path('Sitemap', id), content=content).put()
            return len(articles), query.cursor()
        return 0, None

    @staticmethod
    @memcached('get_sitemap', SITEMAP_CACHE_TIME)
    def get_sitemap():
        sitemaps = Sitemap.all().fetch(Sitemap._LIMIT)
        xml = ['<?xml version="1.0" encoding="UTF-8"?><urlset xmlns="http://www.sitemaps.org/schemas/sitemap/0.9">']
        for sitemap in sitemaps:
            xml.append(sitemap.content)
        xml.append('</urlset>')
        return ''.join(xml)

class Feed(db.Model):
	content = db.TextProperty()
	cursor = db.StringProperty(indexed=False)
	time = db.DateTimeProperty(auto_now_add=True)

class Subscriber(db.Model):
    # key name: ua
    count = db.IntegerProperty(default=1, indexed=False)
    time = db.DateTimeProperty(auto_now=True)

def set_subscriber(user_agent, count):
    if len(user_agent) > 500:
        user_agent = user_agent[:500]
    key = 'get_subscriber:' + user_agent
    try:
        if memcache.get(key) != count:
            Subscriber(key_name=user_agent, count=count).put()
            memcache.set(key, count, SUBSCRIBER_CACHE_TIME)
    except:
        logger.error('when set_subscriber error occur, trackback msg: %s', traceback.format_exc())

@memcached('get_subscribers', SUBSCRIBERS_CACHE_TIME)
def get_subscribers():
    subscribers = Subscriber.all().fetch(GAE_FETCH_LIMIT)
    if subscribers:
        return reduce(lambda x, y: x + y.count, subscribers, 0)
    return 0
