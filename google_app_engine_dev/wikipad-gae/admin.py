# -*- coding: utf-8 -*-

from common import *
import admin_handlers

application = yui.WsgiApplication([

    (BLOG_ADMIN_RELATIVE_PATH, admin_handlers.AdminHomeHandler),

	(BLOG_ADMIN_RELATIVE_PATH + 'article/new/', admin_handlers.AddArticleHandler),
	(BLOG_ADMIN_RELATIVE_PATH + 'article/edit/(.+)', admin_handlers.EditArticleHandler),
	(BLOG_ADMIN_RELATIVE_PATH + 'article/delete/(.+)', admin_handlers.DeleteArticleHandler),
	(BLOG_ADMIN_RELATIVE_PATH + 'articles/delete/', admin_handlers.DeleteArticlesHandler),

    (BLOG_ADMIN_RELATIVE_PATH + 'articles/', admin_handlers.ArticlesManageHandler),

    (BLOG_ADMIN_RELATIVE_PATH + 'tags/', admin_handlers.TagsManageHandler),
    (BLOG_ADMIN_RELATIVE_PATH + 'tags/add/', admin_handlers.AddTagsHandler),
    (BLOG_ADMIN_RELATIVE_PATH + 'tags/delete/', admin_handlers.DeleteTagsHandler),
    (BLOG_ADMIN_RELATIVE_PATH + 'tag/rename/', admin_handlers.RenameTagHandler),
    (BLOG_ADMIN_RELATIVE_PATH + 'tag/sync/', admin_handlers.SyncTagHandler, yui.Response),

    (BLOG_ADMIN_RELATIVE_PATH + 'categories/', admin_handlers.CategoriesManageHandler),
    (BLOG_ADMIN_RELATIVE_PATH + 'categories/add/', admin_handlers.AddCategoriesHandler),
    (BLOG_ADMIN_RELATIVE_PATH + 'category/delete/', admin_handlers.DeleteCategoryHandler),
    (BLOG_ADMIN_RELATIVE_PATH + 'category/rename/', admin_handlers.RenameCategoryHandler),
    (BLOG_ADMIN_RELATIVE_PATH + 'category/sync/', admin_handlers.SyncCategoryHandler, yui.Response),

    (BLOG_ADMIN_RELATIVE_PATH + 'unpublished/', admin_handlers.UnpublishedHandler, yui.Response),
    (BLOG_ADMIN_RELATIVE_PATH + 'purge-cache/', admin_handlers.PurgeCacheHandler, yui.Response),
	(BLOG_ADMIN_RELATIVE_PATH + 'article-counter/', admin_handlers.ArticleCounterHandler, yui.Response),

	(BLOG_ADMIN_RELATIVE_PATH + 'generate-sitemap/', admin_handlers.GenerateSitemapHandler, yui.Response),
	(BLOG_ADMIN_RELATIVE_PATH + 'generate-feed/', admin_handlers.GenerateFeedHandler, yui.Response),

	(BLOG_ADMIN_RELATIVE_PATH + 'post-json', admin_handlers.PostJsonHandler, yui.Response),

	(BLOG_ADMIN_RELATIVE_PATH + 'calendar-token', admin_handlers.CalendarTokenHandler),
	(BLOG_ADMIN_RELATIVE_PATH + 'send-whether-sms', admin_handlers.SMSHandler),

], quote_path=False)

application = log_request(application)
