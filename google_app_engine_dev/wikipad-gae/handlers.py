# -*- coding: utf-8 -*-

from common import *
from model import *

class HomePageHandler(BaseHandler):
    def get(self):
        cursor = unquoted_cursor(self.GET['cursor'])

        articles, next_cursor= Article.get_articles_for_homepage(cursor)

        if next_cursor:
            next_cursor = quoted_cursor(next_cursor)

        self.echo('index.html',
                {
                    'title' : "Pythonee's Wikipad",
                    'page' : 'home_page',
                    'articles' : articles,
                    'cursor' : cursor,
                    'next_cursor' : next_cursor
                })

class LoginoutHandler(yui.RequestHandler):
    def get(self):
        if self.request.user:
            self.redirect(users.create_logout_url(
                    self.request.referer
                    or
                    BLOG_HOME_RELATIVE_PATH
                ))
        else:
            self.redirect(users.create_login_url(
                    self.request.referer
                    or
                    BLOG_HOME_RELATIVE_PATH
                ))
        fragcache.store.delete('siderbar')

class NotFoundHandler(BaseHandler):
    @yui.client_cache(600,'public')
    def get(self):
        self.error(404)
        self.echo('error.html',
                {
                    'title' : '404 page not found',
                    'h3' : '404 page not Found page',
                    'msg' : 'this page has been moved',
                    'page' : 'error'
                })

class SearchHandler(BaseHandler):
    def get(self):
        query_str = strip(self.request.GET['q'])
        cursor = unquoted_cursor(self.GET['cursor'])

        if not cursor:
            cursor = None

        query = Article.all().search(query_str,['content','title']).order('-time')
        result, next_cursor = fetch_with_cursor(query_with_cursor(query, cursor),
                                            10,
                                            config=EVENTUAL_CONSISTENCY_CONFIG)

        cache_articles(result)
        if next_cursor:
            next_cursor = quoted_cursor(next_cursor)

        self.echo('search.html',
                {
                    'title' : 'Search Result',
                    'page' : 'search_result',
                    'articles' : result,
                    'keywords' : query_str,
                    'cursor' : cursor,
                    'next_cursor' : next_cursor
                })

class ArticlePageHandler(BaseHandler):
    def get(self,url):
        article = Article.get_by_url(unquoted_url(url))

        if article:
            if self.is_spider():
                self.response.set_last_modified(article.mod_time)
            self.echo('single-article.html',
                    {
                        'article': article,
                        'title' : article.title,
                        'page' : 'single_article'
                    })
            incr_counter('article_counter:%s' % article.key().id(), BLOG_ADMIN_RELATIVE_PATH + 'article-counter/')
        else:
            self.echo('error.html',
                    {
                        'title' : '404 article not found',
                        'h3' : '404 article not found',
                        'msg' : 'article not found',
                        'page' : 'error'
                    })

class ArticlesInTagHandler(BaseHandler):
    def get(self, tag_name):
        tag_name = unquoted_url(tag_name)
        memcache_key = 'get_tag_by_name:' + tag_name
        tag = memcache.get(memcache_key)

        if tag is None:
            tag = Tag.get_by_key_name(tag_name) or ENTITY_NOT_FOUND

        memcache.set(memcache_key, tag, TAGS_CACHE_TIME)

        if tag:
            cursor = unquoted_cursor(self.GET['cursor'])
            articles, next_cursor = tag.get_articles(cursor)

            if next_cursor:
                next_cursor = quoted_cursor(next_cursor)

            self.echo('tag.html', {
                'title': u'tag %s' % tag_name,
                'tag_name': tag_name,
                'articles': articles,
                'next_cursor': next_cursor,
                'cursor': cursor,
                'page': 'articles_in_tag'
                })
        else:
            self.error(404)
            self.echo('error.html', {
                'title' : '404 tag not found',
                'h3' : '404 tag not found',
                'msg' : 'tag not found',
                'page' : 'error'
                })

class ArticlesInCategoryHandler(BaseHandler):
    def get(self, name):
        #todo
        name = unquoted_url(name)
        memcache_key = 'get_category_by_name:' + name
        category = memcache.get(memcache_key)
        if category is None:
            category = Category.get_by_key_name(name) or ENTITY_NOT_FOUND
        memcache.set(memcache_key, category, CATEGORY_CACHE_TIME)
        if category:
            cursor = unquoted_cursor(self.GET['cursor'])
            articles, next_cursor = category.get_articles(cursor)
            self.echo('category.html', {
                'title': u'Category %s' % name,
                'category_name': name,
                'articles': articles,
                'next_cursor': next_cursor,
                'cursor': cursor,
                'page': 'articles_in_category'
                })
        else:
            self.error(404)
            self.echo('error.html', {
                'page': 'error',
                'title': 'category name not existed',
                'h3': 'Something missing',
                'msg': 'No articles found'
                })

class FeedHandler(BaseHandler):
    def get(self):
        self.set_content_type('atom')
        user_agent = self.request.user_agent.replace(',gzip(gfe)', '')
        subscribers = get_subscribers_from_ua(user_agent)
        if subscribers:
            if 'Feedfetcher-Google' in user_agent:
                user_agent = user_agent.replace(' %s subscribers;' % subscribers, '')
        else:
            user_agent = '%s:%s' % (self.request.client_ip, user_agent)
            subscribers = 1
        set_subscriber(user_agent, subscribers)

        articles = Article.get_articles_for_feed()
        if articles:
            last_modified = articles[0].mod_time
            last_updated = iso_time_format(last_modified)
        else:
            last_modified = datetime.utcnow()
            last_updated = iso_time_now()
        self.response.set_last_modified(last_modified)
        self.echo('feed.xml', {'articles': articles, 'last_updated': last_updated})

class SitemapHandler(yui.RequestHandler):
    @yui.client_cache(SITEMAP_CACHE_TIME, 'public')
    def get(self):
        self.set_content_type('text/xml')
        self.write(Sitemap.get_sitemap())

class ToolsHandler(BaseHandler):
    def get(self):
        self.echo('tools.html',

                {
                    'title' : 'Useful tools collection',
                    'msg' : 'coming soon',
                    'page' : 'tools'
                })

class ContactHandler(BaseHandler):
    def get(self):
        self.echo('contact.html',
                {
                    'title' : 'Contact me',
                    'page' : 'contact'
                })

    def post(self):
        POST = self.POST

        email = POST['from']
        if not email:
            self.write('email field is required')
            return
        else:
            if not EMAIL_PATTER.match(email):
                self.write('the format of email is not valid')
                return

        subject = POST['subject']
        if not subject:
            self.write('subject field is required')
            return

        content = POST['content']
        if not content:
            self.write('content field is required')
            return
        try:
            mail.send_mail(sender=email,
                    to=ADMIN_EMAIL,
                    subject=subject,
                    body=content)
            self.write('your email have send')
        except:
            self.error(500)

class WarmupHandler(yui.RequestHandler):
    def get(self):
        Category.get_all_categories()
        Tag.get_all_tags(more_than=1)
        Article.get_articles_for_homepage()

class RobotsHandler(BaseHandler):
	@yui.client_cache(3600, 'public')
	def get(self):
		self.set_content_type('text/plain')
		self.echo('robots.txt')
