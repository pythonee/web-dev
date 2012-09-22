# -*- coding: utf-8 -*-

import sys
import os
import zipfile



_ZIPFILE = zipfile.ZipFile('becer/db.zip')

CHAR2 = _ZIPFILE.read('CHAR2.txt')
CHAR3 = _ZIPFILE.read('CHAR3.txt')
CHAR4 = _ZIPFILE.read('CHAR4.txt')
CHAR5 = _ZIPFILE.read('CHAR5.txt')

_ZIPFILE.close()

LEN_CHAR2 = 100190
LEN_CHAR3 = 44179
LEN_CHAR4 = 52503
LEN_CHAR5 = 6243

DEFAULT_CODE = lambda x: x.decode('gbk')

def str_bisect(text, word, le, lo=0, hi=None):
    if hi is None: hi = len(text)/le
    mid_ = 0
    while lo < hi:
        mid = (lo+hi)//2
        mid_ = mid*le
        if text[mid_:(mid_+le)] < word: lo = mid + 1
        elif text[mid_:(mid_+le)] > word: hi = mid
        else: return True
    return text[lo*le:(lo*le+le)] == word

def _word(word, le=None):
    if le is None: le = len(word)
    if le == 4: return str_bisect(CHAR2, word, le, hi=LEN_CHAR2)
    elif le == 6: return str_bisect(CHAR3, word, le, hi=LEN_CHAR3)
    elif le == 8: return str_bisect(CHAR4, word, le, hi=LEN_CHAR4)
    else: return str_bisect(CHAR5, word, le, hi=LEN_CHAR5)

def forward(sen, code=DEFAULT_CODE):
    le = len(sen)
    words = []
    start = 0
    end = 10 if le >= 10 else le
    while start < le:
        word = sen[start:end]
        le_ = end - start
        if le_ <= 2 or _word(word, le_):
            start = end
            end += 10
            words.append(code(word))
        else:end -= 2
    return words

def reverse(sen, code=DEFAULT_CODE):
    le = len(sen)
    words = []
    start = le - 10 if le >= 10 else 0
    end = le
    while end >= 0:
        word = sen[start:end]
        le_ = end - start
        if le_ <= 2 or _word(word, le_):
            end = start
            start -= 10
            words.insert(0, code(word))
        else:start += 2
    return words


