#!/usr/bin/env python3
# https://leetcode.com/problems/find-the-difference/

import unittest
from collections import Counter


class Solution:
    def findTheDifference(self, s: str, t: str) -> str:
        count_t = Counter(t)
        count_s = Counter(s)
        result = ''
        for key, count in count_t.items():
            if count_s[key] != count:
                result = key
                break
        return result


class TestCode(unittest.TestCase):
    def test_example_1(self):
        self.assertEqual('e', Solution().findTheDifference('abcd', 'abcde'))

    def test_example_2(self):
        self.assertEqual('y', Solution().findTheDifference('', 'y'))
