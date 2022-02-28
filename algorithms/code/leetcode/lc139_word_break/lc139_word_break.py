#!/usr/bin/env python3
# https://leetcode.com/problems/word-break/

import unittest
from typing import List


class Solution:
    # pylint: disable=R0913
    def __wordBreak(self, s, wordDict, at, length, checked):
        if checked[at]:
            return False
        limit = min(len(s), at + length)
        for i in range(at + 1, limit + 1):
            if s[at:i] in wordDict:
                if i == len(s):
                    return True
                if self.__wordBreak(s, wordDict, i, length, checked):
                    return True
        checked[at] = True
        return False

    def wordBreak(self, s: str, wordDict: List[str]) -> bool:
        length = 0
        for word in wordDict:
            length = max(len(word), length)
        checked = [False] * len(s)
        return self.__wordBreak(s, set(wordDict), 0, length, checked)


class TestCode(unittest.TestCase):
    def __prepare(self, words):
        a_set = set(words)
        return a_set

    def test_a__a(self):
        self.assertTrue(Solution().wordBreak('a', self.__prepare(['a'])))

    def test_other(self):
        self.assertFalse(
            Solution().wordBreak(
                'catsandog',
                self.__prepare(['cats', 'dog', 'sand', 'and', 'cat']),
            )
        )

    def test_another(self):
        self.assertTrue(
            Solution().wordBreak('leetcode', self.__prepare(['leet', 'code']))
        )
