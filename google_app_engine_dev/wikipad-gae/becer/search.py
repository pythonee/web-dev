# -*- coding: utf-8 -*-

import re
import string
import sys
import os
from gbk import GBK

from google.appengine.api import datastore
from google.appengine.api import datastore_errors
from google.appengine.api import datastore_types
from google.appengine.ext import db
from google.appengine.datastore import datastore_query

import segment

ALL_PROPERTIES = []

class SearchableEntity(datastore.Entity):
  """A subclass of datastore.Entity that supports full text indexing.

  Automatically indexes all string and Text properties, using the datastore's
  built-in per-property indices. To search, use the SearchableQuery class and
  its Search() method.
  """



  _FULL_TEXT_INDEX_PROPERTY = '__searchable_text_index'



  _FULL_TEXT_MIN_LENGTH = 3







  _FULL_TEXT_STOP_WORDS = frozenset([
   'a', 'about', 'according', 'accordingly', 'affected', 'affecting', 'after',
   'again', 'against', 'all', 'almost', 'already', 'also', 'although',
   'always', 'am', 'among', 'an', 'and', 'any', 'anyone', 'apparently', 'are',
   'arise', 'as', 'aside', 'at', 'away', 'be', 'became', 'because', 'become',
   'becomes', 'been', 'before', 'being', 'between', 'both', 'briefly', 'but',
   'by', 'came', 'can', 'cannot', 'certain', 'certainly', 'could', 'did', 'do',
   'does', 'done', 'during', 'each', 'either', 'else', 'etc', 'ever', 'every',
   'following', 'for', 'found', 'from', 'further', 'gave', 'gets', 'give',
   'given', 'giving', 'gone', 'got', 'had', 'hardly', 'has', 'have', 'having',
   'here', 'how', 'however', 'i', 'if', 'in', 'into', 'is', 'it', 'itself',
   'just', 'keep', 'kept', 'knowledge', 'largely', 'like', 'made', 'mainly',
   'make', 'many', 'might', 'more', 'most', 'mostly', 'much', 'must', 'nearly',
   'necessarily', 'neither', 'next', 'no', 'none', 'nor', 'normally', 'not',
   'noted', 'now', 'obtain', 'obtained', 'of', 'often', 'on', 'only', 'or',
   'other', 'our', 'out', 'owing', 'particularly', 'past', 'perhaps', 'please',
   'poorly', 'possible', 'possibly', 'potentially', 'predominantly', 'present',
   'previously', 'primarily', 'probably', 'prompt', 'promptly', 'put',
   'quickly', 'quite', 'rather', 'readily', 'really', 'recently', 'regarding',
   'regardless', 'relatively', 'respectively', 'resulted', 'resulting',
   'results', 'said', 'same', 'seem', 'seen', 'several', 'shall', 'should',
   'show', 'showed', 'shown', 'shows', 'significantly', 'similar', 'similarly',
   'since', 'slightly', 'so', 'some', 'sometime', 'somewhat', 'soon',
   'specifically', 'state', 'states', 'strongly', 'substantially',
   'successfully', 'such', 'sufficiently', 'than', 'that', 'the', 'their',
   'theirs', 'them', 'then', 'there', 'therefore', 'these', 'they', 'this',
   'those', 'though', 'through', 'throughout', 'to', 'too', 'toward', 'under',
   'unless', 'until', 'up', 'upon', 'use', 'used', 'usefully', 'usefulness',
   'using', 'usually', 'various', 'very', 'was', 'we', 'were', 'what', 'when',
   'where', 'whether', 'which', 'while', 'who', 'whose', 'why', 'widely',
   'will', 'with', 'within', 'without', 'would', 'yet', 'you'])
  _FULL_TEXT_STOP_CN_WORDS = frozenset([u'\u662f', u'\u7684', u'\u5730',
    u'\u5f97', u'\u5c31', u'\u554a', u'\u5443', u'\u963f', u'\u548c',
    u'\u4e0a', u'\u5c31'])

  _word_delimiter_regex = re.compile('[' + re.escape(string.punctuation) + ']')
  MATCH_STR = re.compile('\w+')
  MATCH_UNICODE = re.compile(u'[\u0100-\uffff]+')
  MATCH_MARK = re.compile(u'[\uff00-\uffef]|[\u3000-\u303f]|[\u2000-\u2057]+')

  _searchable_properties = [ALL_PROPERTIES]

  def __init__(self, kind_or_entity, word_delimiter_regex=None, *args,
               **kwargs):
    """Constructor. May be called as a copy constructor.

    If kind_or_entity is a datastore.Entity, copies it into this Entity.
    datastore.Get() and Query() returns instances of datastore.Entity, so this
    is useful for converting them back to SearchableEntity so that they'll be
    indexed when they're stored back in the datastore.

    Otherwise, passes through the positional and keyword args to the
    datastore.Entity constructor.

    Args:
      kind_or_entity: string or datastore.Entity
      word_delimiter_regex: a regex matching characters that delimit words
    """
    self._word_delimiter_regex = word_delimiter_regex
    if isinstance(kind_or_entity, datastore.Entity):
      self._Entity__key = kind_or_entity._Entity__key
      self._Entity__unindexed_properties = frozenset(kind_or_entity.unindexed_properties())
      if isinstance(kind_or_entity, SearchableEntity):

        if getattr(kind_or_entity, '_searchable_properties', None) is not None:
          self._searchable_properties = kind_or_entity._searchable_properties
      self.update(kind_or_entity)
    else:
      super(SearchableEntity, self).__init__(kind_or_entity, *args, **kwargs)

  def _ToPb(self):
    """Rebuilds the full text index, then delegates to the superclass.

    Returns:
      entity_pb.Entity
    """
    for properties_to_index in self._searchable_properties:
      index_property_name = SearchableEntity.IndexPropertyName(properties_to_index)
      if index_property_name in self:
        del self[index_property_name]
      if not properties_to_index:
        properties_to_index = self.keys()
      index = set()
      for name in properties_to_index:
        if not self.has_key(name):
          continue
        values = self[name]
        if not isinstance(values, list):
          values = [values]
        if (isinstance(values[0], basestring) and
            not isinstance(values[0], datastore_types.Blob)):
          for value in values:
            index.update(SearchableEntity._FullTextIndex(
                value, self._word_delimiter_regex, self._searchable_language))
      index_list = list(index)
      if index_list:
        self[index_property_name] = index_list

    return super(SearchableEntity, self)._ToPb()
  
  @classmethod
  def _match_gbk(cls, s):
    rs = ''
    for x in s:
      lo = 0
      hi = 21748
      while lo < hi:
        mid = (lo+hi)//2
        if GBK[mid:mid+1] < x: lo = mid+1
        else: hi = mid
      if x == GBK[lo:lo+1]:
        rs += x
    return rs    

  @classmethod
  def _CnTextIndex(cls, alltext):
    set_cn = set()
    words = set()
    for p in cls.MATCH_UNICODE.findall(alltext):
      set_cn.update(cls.MATCH_MARK.split(p))
    for s in set_cn:
      words.update(segment.forward(cls._match_gbk(s).encode('gbk')))
    words -= cls._FULL_TEXT_STOP_CN_WORDS
    cls.words.update(words)

  @classmethod
  def _EnTextIndex(cls, alltext, word_delimiter_regex=None):
    text = ' '.join(cls.MATCH_STR.findall(alltext))
    if word_delimiter_regex is None:
      word_delimiter_regex = cls._word_delimiter_regex

    if text:
      datastore_types.ValidateString(text, 'text', max_len=sys.maxint)


      text = word_delimiter_regex.sub(' ', text)
      words = text.lower().split()


      words = set(unicode(w) for w in words)


      words -= cls._FULL_TEXT_STOP_WORDS
      for word in list(words):
        if len(word) < cls._FULL_TEXT_MIN_LENGTH:
          words.remove(word)

    else:
      words = set()
    cls.words.update(words)

  @classmethod
  def _FullTextIndex(cls, alltext, word_delimiter_regex=None, lang=['cn', 'en']):
    """Returns a set of keywords appropriate for full text indexing.

    See SearchableQuery.Search() for details.

    Args:
      text: string

    Returns:
      set of strings
    """

    cls.words = set()
    if 'cn' in lang:
      cls._CnTextIndex(alltext)
    if 'en' in lang:
      cls._EnTextIndex(alltext)
    return cls.words

  @classmethod
  def IndexPropertyName(cls, properties):
    """Given index definition, returns the name of the property to put it in."""
    name = SearchableEntity._FULL_TEXT_INDEX_PROPERTY

    if properties:
      name += '_' + '_'.join(properties)

    return name


class SearchableQuery(datastore.Query):
  """A subclass of datastore.Query that supports full text search.

  Only searches over entities that were created and stored using the
  SearchableEntity or SearchableModel classes.
  """

  def Search(self, search_query, word_delimiter_regex=None,
             properties=ALL_PROPERTIES):
    """Add a search query. This may be combined with filters.

    Note that keywords in the search query will be silently dropped if they
    are stop words or too short, ie if they wouldn't be indexed.

    Args:
     search_query: string

    Returns:
      # this query
      SearchableQuery
    """
    datastore_types.ValidateString(search_query, 'search query')
    self._search_query = search_query
    self._word_delimiter_regex = word_delimiter_regex
    self._properties = properties
    return self

  def GetFilterPredicate(self, *args, **kwds):
    """Adds filters for the search query, then delegates to the superclass.

    Mimics Query.GetFilterPredicate()'s signature. Raises BadFilterError if a
    filter on the index property already exists.

    Returns:
      datastore_query.FilterPredicate
    """



    properties = getattr(self, "_properties", ALL_PROPERTIES)

    index_property_name = SearchableEntity.IndexPropertyName(properties)
    if index_property_name in self:
      raise datastore_errors.BadFilterError(
        '%s is a reserved name.' % index_property_name)

    filter = super(SearchableQuery, self).GetFilterPredicate(*args, **kwds)

    if hasattr(self, '_search_query'):
      keywords = SearchableEntity._FullTextIndex(
          self._search_query, self._word_delimiter_regex)
      if keywords:
        search_filter = datastore_query.make_filter(
            index_property_name, '=', list(keywords))
        if filter:
          filter = datastore_query.CompositeFilter(
              datastore_query.CompositeFilter.AND,
              [filter, search_filter])
        else:
          filter = search_filter
    return filter


class SearchableMultiQuery(datastore.MultiQuery):
  """A multiquery that supports Search() by searching subqueries."""

  def Search(self, *args, **kwargs):
    """Add a search query, by trying to add it to all subqueries.

    Args:
      args: Passed to Search on each subquery.
      kwargs: Passed to Search on each subquery.

    Returns:
      self for consistency with SearchableQuery.
    """
    for q in self:
      q.Search(*args, **kwargs)
    return self


class SearchableModel(db.Model):
  """A subclass of db.Model that supports full text search and indexing.

  Automatically indexes all string-based properties. To search, use the all()
  method to get a SearchableModel.Query, then use its search() method.

  Override SearchableProperties() to define properties to index and/or multiple
  indexes (see the file's comment).
  """

  @classmethod
  def SearchableProperties(cls):
    return [ALL_PROPERTIES]

  @classmethod
  def SearchableLanguage(cls):
    return ['cn', 'en']

  class Query(db.Query):
    """A subclass of db.Query that supports full text search."""
    _search_query = None
    _properties = None

    def search(self, search_query, properties=ALL_PROPERTIES):
      """Adds a full text search to this query.

      Args:
        search_query, a string containing the full text search query.

      Returns:
        self
      """
      self._search_query = search_query
      self._properties = properties


      if self._properties not in getattr(self, '_searchable_properties', [ALL_PROPERTIES]):
        raise datastore_errors.BadFilterError(
          '%s does not have a corresponding index. Please add it to'
          'the SEARCHABLE_PROPERTIES list' % self._properties)

      return self

    def _get_query(self):
      """Wraps db.Query._get_query() and injects SearchableQuery."""
      query = db.Query._get_query(self,
                                  _query_class=SearchableQuery,
                                  _multi_query_class=SearchableMultiQuery)
      if self._search_query:
        query.Search(self._search_query, properties=self._properties)
      return query

  def _populate_internal_entity(self):
    """Wraps db.Model._populate_internal_entity() and injects
    SearchableEntity."""
    entity = db.Model._populate_internal_entity(self,
                                                _entity_class=SearchableEntity)
    entity._searchable_properties = self.SearchableProperties()
    entity._searchable_language = self.SearchableLanguage()
    return entity

  @classmethod
  def from_entity(cls, entity):
    """Wraps db.Model.from_entity() and injects SearchableEntity."""
    if not isinstance(entity, SearchableEntity):
      entity = SearchableEntity(entity)
    return super(SearchableModel, cls).from_entity(entity)

  @classmethod
  def all(cls):
    """Returns a SearchableModel.Query for this kind."""
    query = SearchableModel.Query(cls)
    query._searchable_properties = cls.SearchableProperties()
    return query
