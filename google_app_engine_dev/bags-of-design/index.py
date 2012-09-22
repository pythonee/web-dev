
from google.appengine.ext import webapp
from google.appengine.ext.webapp.util import run_wsgi_app

import os
from google.appengine.ext.webapp import template

class MainPage(webapp.RequestHandler):
    def get(self):
        path = os.path.join(os.path.dirname(__file__), 'static/template/index.html')
        self.response.out.write(template.render(path,{}))

class SignUp(webapp.RequestHandler):
    def get(self):
        path = os.path.join(os.path.dirname(__file__), 'static/template/signup.html')
        self.response.out.write(template.render(path,{}))

class Sort(webapp.RequestHandler):
    def get(self):
        path = os.path.join(os.path.dirname(__file__), 'static/template/sort.html')
        self.response.out.write(template.render(path,{}))

application = webapp.WSGIApplication(
                                     [('/', MainPage),
                                      ('/signup',SignUp),
                                      ('/sort',Sort)
                                         ],
                                     debug=True)

def main():
    run_wsgi_app(application)

if __name__ == "__main__":
    main()
