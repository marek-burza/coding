#!/usr/bin/env python3
# https://leetcode.com/problems/length-of-last-word/

import unittest


class Solution:
    def lengthOfLastWord(self, s):
        if not s or len(s) == 0:
            return 0
        n = len(s)
        while n > 0 and s[n - 1] == ' ':
            n -= 1
        for i in range(n - 1, -1, -1):
            if s[i] == ' ':
                return n - i - 1
        return n


class TestCode(unittest.TestCase):
    def test_hello_horld(self):
        self.assertEqual(5, Solution().lengthOfLastWord('Hello World'))

    def test_nothing(self):
        self.assertEqual(0, Solution().lengthOfLastWord(None))
        self.assertEqual(0, Solution().lengthOfLastWord(''))

    def test_almost_nothing(self):
        self.assertEqual(0, Solution().lengthOfLastWord(' '))

    def test_trailing_space(self):
        self.assertEqual(5, Solution().lengthOfLastWord('Hello World  '))

    def test_single_word(self):
        self.assertEqual(10, Solution().lengthOfLastWord('HelloWorld'))
