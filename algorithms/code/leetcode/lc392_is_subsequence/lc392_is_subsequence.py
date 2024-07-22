#!/usr/bin/env python3
# https://leetcode.com/problems/is-subsequence/

import unittest


class Solution:
    def isSubsequence(self, s: str, t: str) -> bool:
        n = len(s)
        if n == 0:
            return True
        if n == 1:
            return s in t
        character = s[0]
        at = t.find(character)
        if at != -1:
            return self.isSubsequence(s[1:], t[at + 1 :])
        return False


class TestCode(unittest.TestCase):
    def test_example_1(self):
        self.assertTrue(Solution().isSubsequence('abc', 'ahbgdc'))

    def test_example_2(self):
        self.assertFalse(Solution().isSubsequence('axc', 'ahbgdc'))

    def test_other_1(self):
        self.assertTrue(Solution().isSubsequence('', 'ahbgdc'))

    def test_other_2(self):
        self.assertFalse(Solution().isSubsequence('aaaaaa', 'eeaaa'))
