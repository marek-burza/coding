#!/usr/bin/env python3
# https://leetcode.com/problems/reverse-string/

import unittest

from typing import List


class Solution:
    def reverseString(self, s: List[str]) -> None:
        for i in range(round(len(s) / 2)):
            exchange = s[i]
            s[i] = s[len(s) - 1 - i]
            s[len(s) - 1 - i] = exchange


class TestCode(unittest.TestCase):
    def test_example(self):
        s = list('hello')
        Solution().reverseString(s)
        self.assertListEqual(list('olleh'), s)
