# -*- coding: utf-8 -*-

from common import *
from model import *
from gdata_for_gae import send_whether_sms

class AdminHomeHandler(BaseHandler):
    def get(self):
        self.echo('admin_index.html',
                {
                    'title':'administrator page',
                    'page' : 'admin_homepage'
                })

class AddArticleHandler(BaseHandler):
    def get(self):
        tags = Tag.all().fetch(GAE_FETCH_LIMIT)
        tags = [tag.key().name() for tag in tags]
        categories = Category.all().order('path').fetch(GAE_FETCH_LIMIT)
        self.echo('new_article.html',
                {
                    'tags' : tags,
                    'categories': categories,
                    'title':'post new article',
                    'page' : 'admin_new_article'
                })

    def post(self):
        POST = self.POST
        title = strip(POST['title']);
        time = parse_time(POST['time']) or datetime.utcnow()
        mod_time = parse_time(POST['mod_time']) or time
        content = POST['content']
        url = strip(POST['url'])
        published = POST['published'] == 'on'
        tags = POST['tags']
        category = POST['category'] or None

        if not title:
            self.write('title field is required')
            return

        if not url:
            url = formatted_date_for_url(time) + replace_special_chars_for_url(title).lower()

        if not ARTICLE_URL_PATTERN.match(url):
            self.write('the format of url not valid')
            return

        if Article.all().filter('url =' , url).count(1):
            self.write('published fail, the same url have existed ')
            return

        if tags:
            tags = tags.split(',')
            tag_set = set([tag.strip() for tag in tags])
            tag_set.discard('')
            tags = list(tag_set)
        else:
            tags = []

        try:
            article = Article(
               title = title
               ,time = time
               ,mod_time = mod_time
               ,content = content
               ,url = url
               ,published = published
               ,tags = tags
               ,category = category
               )

            article.put()

            if tags:
                old_tags = Tag.get_by_key_name(tags)
                old_tag_set = set(old_tags)
                old_tag_set.discard(None)
                old_tags = list(old_tag_set)

                for old_tag in old_tags:
                    old_tag.count += 1
                db.put(old_tags)

                new_tags_name = set(tags) - set([old_tag.key().name() for old_tag in old_tags])
                new_tags_name = list(new_tags_name)
                new_tags = [Tag(key_name=tag_name, count=1) for tag_name in new_tags_name]
                db.put(new_tags)

            self.write('<span>Your blog post published successfully! </span> \
                        <span><a href="%s%s">view post</a> </span> \
                        <span class="divider">|</span> \
                        <span><a href="%s%s"> edit post</a> </span>' \
                    %(BLOG_HOME_RELATIVE_PATH, quoted_url(article.url),
                     BLOG_ADMIN_RELATIVE_PATH+'article/edit/', article.key().id()
                     ))
            if published:

                article_url = quoted_url(article.url)
                full_url = MAJOR_HOST_URL + BLOG_HOME_RELATIVE_PATH + article_url

                deferred.defer(ping_xml_rpc, full_url)
                deferred.defer(ping_hubs, BLOG_FEED_URL)

                clear_article_memcache()
                clear_tags_memcache()
                fragcache.store.delete('siderbar')

        except:
            self.write('publish fail, may cause by database busy')

class DeleteArticleHandler(BaseHandler):
    def get(self, id):
        article = Article.get_by_id(int(id))
        if article:
            self.echo('delete_article.html',
                    {
                        'article':article,
                        'title': 'delete %s' % article.title,
                        'page' : 'admin_delete_article'
                    })
        else:
            self.echo('error.html',
                    {
                        'title' : 'System error',
                        'h3' : 'Delete operation fail',
                        'msg' : 'I cant found this article. It had been remove',
                        'page' : 'error'
                    })

    def post(self,id):
        try:
            article = Article.get_by_id(int(id))
            tag_names = article.tags
            tag_names = set(tag_names)
            tag_names.discard('')
            tag_names = list(tag_names)

            for tag_name in tag_names:
                tag = Tag.get_by_key_name(tag_name)
                if tag:
                    tag.count -= 1
                    if tag.count < 0:
                        tag.count = 0
                    db.put(tag)

            if Article.remove(int(id)):
                self.write('Operation successfully')
                clear_article_memcache(int(id))
                clear_tags_memcache()
                fragcache.store.delete('siderbar')
            else:
                self.write('This article has been remove, not need to delete again')
        except:
            self.write('Operation fail. retry later')

class DeleteArticlesHandler(BaseHandler):
    def post(self):
        request = self.request
        post_ids = request.get_all('article-ids');

        tags_need_to_update = []

        for post_id in post_ids:
            try:
                tag_names_in_article = Article.get_by_id(int(post_id)).tags
                tags_need_to_update += tag_names_in_article
                Article.remove(int(post_id))
            except:
                self.write('delete article fail')

        tags_need_to_update = list(set(tags_need_to_update).discard(''))

        for tag_name in tags_need_to_update:
            tag = Tag.get_by_key_name(tag_name)
            tag.count -= 1
            if tag.count < 0:
                tag.count = 0
            db.put(tag)

        clear_article_memcache(int(post_id))
        clear_tags_memcache()
        fragcache.store.delete('siderbar')

        self.write('delete article done')

class EditArticleHandler(BaseHandler):
    def get(self, id):
        article = Article.get_by_id(int(id))
        tags = Tag.all().fetch(GAE_FETCH_LIMIT)
        tags = [tag.key().name() for tag in tags]
        categories = Category.all().order('path').fetch(GAE_FETCH_LIMIT)
        if article:
            self.echo('edit_article.html',
                    {
                        'article':article,
                        'tags' : tags,
                        'categories' : categories,
                        'title': 'Edit %s' % article.title,
                        'page' : 'admin_edit_article'
                        })
        else:
            self.echo('error.html',
                    {
                        'h3' : 'Article not existed',
                        'msg' : 'cant edit a not existed article',
                        'title' : 'System error',
                        'page' : 'error'
                        })

    def post(self, id):
        article = Article.get_by_id(int(id))

        if not article:
            self.write('Article not existed')

        POST = self.POST

        title = strip(POST['title']);
        time = parse_time(POST['time']) or datetime.utcnow()
        mod_time = parse_time(POST['mod_time']) or time
        content = POST['content']
        url = strip(POST['url'])
        published = POST['published'] == 'on'
        tags = POST['tags']
        category = POST['category'] or None

        if tags:
            tags = tags.split(',')
            tag_set = set([tag.strip() for tag in tags])
            tag_set.discard('')
            tags = list(tag_set)
        else:
            tags = []

        if not title:
            self.write('title field is required')
            return

        if not url:
            url = formatted_date_for_url(time) + replace_special_chars_for_url(title).lower()

        if not ARTICLE_URL_PATTERN.match(url):
            self.write('the format of url not valid')
            return

        original_url = article.url
        old_tags = set(article.tags)

        if original_url != url and Article.all().filter('url =', url).count(1):
            self.write('Edit fail! Article with same url have existed')
            return

        def update(article_key):
            article = Article.get(article_key)

            if article:
                article.title = title
                article.time = time
                article.mod_time = mod_time
                article.content = content
                article.url = url
                article.published = published
                article.tags = tags
                article.category = category or None

                article.put()

                return article
            else:
                return None

        article = db.run_in_transaction(update,article.key())

        if article:
            new_tags = set(article.tags)

            removed_tag_names = old_tags - new_tags
            removed_tag_names = set([tag.strip() for tag in removed_tag_names])
            removed_tag_names.discard('')

            added_tag_names = new_tags - old_tags
            added_tag_names = set([tag.strip() for tag in added_tag_names])
            added_tag_names.discard('')

            if removed_tag_names:
                removed_tags = set(Tag.get_by_key_name(removed_tag_names))
                removed_tags.discard(None)
                for removed_tag in removed_tags:
                    removed_tag.count -= 1
                    if removed_tag.count < 0:
                        removed_tag.count = 0
                db.put(removed_tags)

            if added_tag_names:
                existed_tags = set(Tag.get_by_key_name(added_tag_names))
                existed_tags.discard(None)
                for existed_tag in existed_tags:
                    existed_tag.count += 1
                db.put(existed_tags)

                new_tag_names = added_tag_names - set([existed_tag.key().name()
                    for existed_tag in existed_tags])

                new_tags = [Tag(key_name=tag_name, count=1) for tag_name in
                        new_tag_names]
                db.put(new_tags)

            if published:

                memcache_client.set_multi_async({'get_article_by_id:%s' % id: article,
                    'get_article_by_url:%s' % hash(article.url): article}, ARTICLE_CACHE_TIME)

                article_url = quoted_url(article.url)
                full_url = MAJOR_HOST_URL + BLOG_HOME_RELATIVE_PATH + article_url

                deferred.defer(ping_hubs, BLOG_FEED_URL)
                deferred.defer(ping_xml_rpc, full_url)

                clear_article_memcache(id, original_url)
                clear_tags_memcache()
                fragcache.store.delete('siderbar')
            self.write('update successfully! <a href="%s%s">view post</a>'
                    %(BLOG_HOME_RELATIVE_PATH,quoted_url(article.url)))

class ArticlesManageHandler(BaseHandler):
    def get(self):
        cursor = unquoted_cursor(self.GET['cursor'])
        _PAGE_SIZE = 10
        query = Article.all().filter('published =', True).order('-time')
        articles, next_cursor = fetch_with_cursor(query_with_cursor(query, cursor),
                                            fetch_limit=_PAGE_SIZE,
                                            config=EVENTUAL_CONSISTENCY_CONFIG)
        if next_cursor:
            next_cursor = quoted_cursor(next_cursor)

        self.echo('admin_articles.html',
               {
                   'title' : 'Articles Manager Page',
                   'page'  : 'admin_articles_manage',
                   'articles'  : articles,
                   'next_cursor' : next_cursor,
                })

class PostJsonHandler(BaseHandler):
    def get(self):
        to_cursor = self.GET['to']
        from_cursor = self.GET['from']

        if to_cursor:
            articles, next_to_cursor= Article.backward_query(to_cursor)
            next_from_cursor = reverse(to_cursor)

        if from_cursor:
            articles, next_from_cursor = Article.forward_query(from_cursor)
            next_to_cursor = reverse(from_cursor)

        json_dict ={'previous_cursor' : next_to_cursor, 'next_cursor': next_from_cursor}
        article_dicts = []

        for article in articles:
            article_dict = {'id': article.key().id(),
                            'title': article.title,
                            'url': BLOG_HOME_RELATIVE_PATH+quoted_url(article.url),
                            'time' : formatted_time(article.time),
                            'category' : article.category_name(),
                            'tags' : ','.join(article.tags),
                            }
            article_dicts.append(article_dict)
        json_dict['articles'] = article_dicts

        self.set_content_type('json')
        self.write(json.dumps(json_dict))

class UnpublishedHandler(BaseHandler):
    def get(self):
        articles,  next_cursor = Article.get_unpublished_articles()
        self.echo('admin_unpublished.html',
               {
                   'title' : 'Articles Manager Page',
                   'page'  : 'admin_articles_manage',
                   'articles'  : articles
                })

class TagsManageHandler(BaseHandler):
    def get(self):

       tags = Tag.all().fetch(GAE_FETCH_LIMIT)

       self.echo('admin_tags.html',
               {
                   'title' : 'Tags Manager Page',
                   'page'  : 'admin_tags_manage',
                   'tags'  : tags
                })

class AddTagsHandler(BaseHandler):
    def post(self):
        POST = self.POST
        tag_name = POST['tag_name']

        tag_names = []
        if tag_name:
            tag_names = tag_name.split(',')
            tag_name_set = set([tag.strip() for tag in tag_names])
            tag_name_set.discard('')
            tag_names = list(tag_name_set)

        if tag_names:
            existed_tags = Tag.get_by_key_name(tag_names)
            existed_tags_set = set(existed_tags)
            existed_tags_set.discard(None)
            existed_tags = list(existed_tags_set)

            existed_tag_names = [exsited_tag.key().name() for exsited_tag in existed_tags]

            new_tag_names = set(tag_names) - set(existed_tag_names)
            new_tag_names = list(new_tag_names)
            new_tags = [Tag(key_name=tag_name) for tag_name in new_tag_names]
            try:
                db.put(new_tags)
                new_tag_names = [{k:k} for k in new_tag_names]
                self.set_content_type('json')
                self.write(json.dumps(new_tag_names))
            except:
                self.write('database busy')

        clear_tags_memcache()
        fragcache.store.delete('siderbar')

class RenameTagHandler(BaseHandler):
    def post(self):
        POST = self.POST
        src_name = POST['original_name']

        dest_name = POST['dest_name']
        if dest_name:
            dest_name = strip(dest_name)
        else:
            self.write('tag name not blank')

        rename_tag(src_name, dest_name)

class DeleteTagsHandler(BaseHandler):
    def post(self):
        request = self.request
        post_tags = request.get_all('tag');

        for tag_name in post_tags:
            delete_tag(tag_name)

class SyncTagHandler(yui.RequestHandler):
    def get(self):
        sync_tag()

class CategoriesManageHandler(BaseHandler):
    def get(self):
       categories = Category.all().order('path').fetch(GAE_FETCH_LIMIT)
       self.echo('admin_categories.html',
               {
                   'title' : 'Categories Manager Page',
                   'page'  : 'admin_categories_manage',
                   'categories' : categories
                })

class AddCategoriesHandler(BaseHandler):
    def post(self):
        path = self.POST['path']

        if not path:
            self.write('please fill the categroy field and submit again')
            return

        pathes = path.split(',')
        for path in pathes:
            path_with_name = Category.fill_pathes(path)
            try:
                for each_path, name in path_with_name:
                    if Category.all().filter('path =', each_path).count(1):
                        continue
                    category = Category.get_by_key_name(name)
                    if category and category.path != each_path:
                        self.write((u'category name %s have existed in path %s' % (name, category.path)).encode('utf-8'))
                        return
                    Category.get_or_insert(key_name=name, path=each_path)
                clear_categories_memcache()
                fragcache.store.delete('siderbar')
            except:
                self.error(500)
                self.write('fatal error or db busy when add category!')

        clear_categories_memcache()
        fragcache.store.delete('siderbar')
        self.write('add categories done')

class DeleteCategoryHandler(BaseHandler):
    def post(self):
        path_with_name = Category.fill_pathes(self.POST['path'])
        if not path_with_name:
            self.write('Delete Fail, Please choose one category to delete')
            return

        path, name = path_with_name[-1]
        category = Category.all().filter('path =', path).get()
        if not category:
            self.write('Delete Fail, Category have been remove')
            return
        if category.has_sub_categories():
            _LIMIT = GAE_FETCH_LIMIT
            sub_cates = Category.all().filter('path >', path).filter('path <', path+ u'\ufffd').fetch(_LIMIT)
            for sub_cate in sub_cates:
                deferred.defer(delete_category, sub_cate.path)

        deferred.defer(delete_category, path)
        self.write('Have add delete category task to task queue. dont modify articles in this category')

class RenameCategoryHandler(BaseHandler):
    def post(self):
        POST = self.POST
        old_path = POST['old-path']
        new_path = POST['new-path']

        if not old_path:
            self.write('Please choose one path to modify')
            return

        old_cate = db.Key.from_path('Category', Category.path_to_name(old_path))

        if not old_cate:
            self.write('Delete Fail, Category have been remove')
            return

        if not new_path:
            self.write('the new category name is required')
            return

        if new_path[-1] != CATEGORY_PATH_DELIMETER:
            new_path += CATEGORY_PATH_DELIMETER

        deferred.defer(rename_category, old_path, new_path)
        self.write('Have add rename category task to task queue. Wait for the done email')

class SyncCategoryHandler(yui.RequestHandler):
    def get(self):
        sync_cate()
        self.write('Have put this request to task queue.')

class PurgeCacheHandler(yui.RequestHandler):
    def get(self):
        yui.flush_all_server_cache()
        memcache.flush_all()
        logger.debug('flush all cache, include yui and memcache')
        self.write('flush cache done');

class ArticleCounterHandler(yui.RequestHandler):
    def post(self):
        key = self.POST['key']
        retry_limit = 10
        while retry_limit > 0 and not Article.calc_hits(key):
            sleep(5)
            retry_limit -= 1

class GenerateSitemapHandler(yui.RequestHandler):
	def get(self):
		id = self.GET['id']
		id = int(id) if id else 1
		num, next_cursor = Sitemap.fill(id, unquoted_cursor(self.GET['cursor']))
		if num == Sitemap._LIMIT:
			taskqueue.add(queue_name='generate-sitemap', method='GET',
            url=BLOG_ADMIN_RELATIVE_PATH + 'generate-sitemap?id=%d&cursor=%s' % (id + 1, next_cursor))
			self.write('Have submit the generate sitemap task to task queue')
		else:
			self.write('Finish generating sitemap')
			memcache.delete('get_sitemap')

class GenerateFeedHandler(BaseHandler):
    def get(self):
        limit = 100
        query = Article.all().filter('published =', True)
        articles, cursor = fetch_with_cursor(query_with_cursor(query, self.GET['cursor']), limit)
        if articles:
            last_updated = iso_time_format(articles[0].mod_time)
            content = self.render('feed.xml', {'articles': articles, 'last_updated': last_updated})
            Feed(content=content.decode('utf-8'), cursor=cursor).put()
            deferred.defer(ping_hubs, BLOG_FEED_URL)
            if len(articles) < limit:
                self.write('Feed have generated, No need to generate again')
            else:
                self.write('Feed generate done')
        else:
            self.write('Feed have generated, No need to generate again')

class CalendarTokenHandler(BaseHandler):
    def get(self):
        import gdata.auth
        import gdata.calendar
        import gdata.calendar.service
        import gdata_for_gae

        context = {
                'title': 'SMS Notify',
                'page': 'admin_calendar_token',
                'msg': '',
                'token': ''
                }

        scope = 'http://www.google.com/calendar/feeds/'
        feed = self.GET['feed']
        if feed:
            auth_token = gdata.auth.extract_auth_sub_token_from_url(self.request.uri)
            if auth_token:
                try:
                    calendar_client = gdata.calendar.service.CalendarService()
                    gdata_for_gae.run_on_appengine(calendar_client, user=ADMIN_EMAIL, url=feed)
                    calendar_client.UpgradeToSessionToken(auth_token)
                    context['msg'] = 'token verify success. have saved to datastore'
                    context['token'] = gdata_for_gae.load_auth_token(ADMIN_EMAIL)
                except:
                    context['msg'] = 'Cant verify this token'
            else:
                self.redirect(str(gdata.auth.generate_auth_sub_url(self.request.uri, (scope,))))
                return
        else:
            calendar_client = gdata.calendar.service.CalendarService()
            gdata_for_gae.run_on_appengine(calendar_client, user=ADMIN_EMAIL)
            if not isinstance(calendar_client.token_store.find_token(scope), gdata.auth.AuthSubToken):
                context['msg'] = 'Have not valid token yet'
            else:
                context['msg'] = 'You have had a valid token'
                context['token'] = gdata_for_gae.load_auth_token(ADMIN_EMAIL)
        self.echo('calendar_token.html', context)

    def post(self):
        import gdata_for_gae
        gdata_for_gae.del_auth_token(ADMIN_EMAIL)

class SMSHandler(BaseHandler):
    def get(self):
        google_result = pywapi.get_weather_from_google('dublin')

        whether = "Google forecast weather in " + google_result['forecast_information']['city'] + "\n" + \
        "It is " + string.lower(google_result['current_conditions']['condition']) + "\n" + \
        "Feels like: "+google_result['current_conditions']['temp_c'] + "C." + "\n" + \
        google_result['current_conditions']['wind_condition'] + "\n"

        send_whether_sms(title='Weather', msg=whether)

