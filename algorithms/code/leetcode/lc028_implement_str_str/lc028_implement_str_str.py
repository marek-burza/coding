#!/usr/bin/env python3
# https://leetcode.com/problems/implement-strstr/

import unittest


class Solution:
    def strStr(self, haystack: str, needle: str) -> int:
        if len(haystack) < len(needle):
            return -1
        for i in range(1 + len(haystack) - len(needle)):
            if haystack[i : i + len(needle)] == needle:
                return i
        return -1


class TestCode(unittest.TestCase):
    def test_empty(self):
        self.assertEqual(Solution().strStr('', ''), 0)

    def test_mississippi_a(self):
        self.assertEqual(Solution().strStr('mississippi', 'a'), -1)

    def test_mississippi_si(self):
        self.assertEqual(Solution().strStr('mississippi', 'si'), 3)

    def test_bigger_in_smaller(self):
        self.assertEqual(Solution().strStr('', 'si'), -1)
