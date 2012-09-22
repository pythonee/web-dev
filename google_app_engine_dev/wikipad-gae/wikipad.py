# -*- coding: utf-8 -*-

from common import *
import handlers

application = yui.WsgiApplication([

    (BLOG_HOME_RELATIVE_PATH, handlers.HomePageHandler),

    (BLOG_HOME_RELATIVE_PATH + 'login', handlers.LoginoutHandler,yui.Response),
    (BLOG_HOME_RELATIVE_PATH + 'search', handlers.SearchHandler),

	(BLOG_HOME_RELATIVE_PATH + 'tag/(.+)', handlers.ArticlesInTagHandler),
	(BLOG_HOME_RELATIVE_PATH + 'category/(.+)', handlers.ArticlesInCategoryHandler),

    (BLOG_HOME_RELATIVE_PATH + r'(\d{4}/\d{2}/\d{2}/.+)', handlers.ArticlePageHandler),

	(BLOG_HOME_RELATIVE_PATH + r'sitemap\.xml', handlers.SitemapHandler, yui.Response),

	(BLOG_HOME_RELATIVE_PATH + 'feed', handlers.FeedHandler),
	(BLOG_HOME_RELATIVE_PATH + 'contact', handlers.ContactHandler),
	(BLOG_HOME_RELATIVE_PATH + 'tools', handlers.ToolsHandler),

	('/_ah/warmup/', handlers.WarmupHandler, yui.Response),
	(r'/robots\.txt', handlers.RobotsHandler),

    ('.*',handlers.NotFoundHandler),
    ]
    , quote_path=False
    )

if not APPID.startswith('dev~'):
    if ONLY_USE_MAJOR_DOMAIN:
        application = yui.redirect_to_major_domain(application,MAJOR_DOMAIN)

# appstats
#from google.appengine.ext.appstats import recording
#application = recording.appstats_wsgi_middleware(application)

application = log_request(application)
