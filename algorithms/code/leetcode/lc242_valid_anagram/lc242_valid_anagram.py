#!/usr/bin/env python3
# https://leetcode.com/problems/valid-anagram/
# #google

import unittest


class Solution:
    def isAnagram(self, s: str, t: str) -> bool:
        if len(s) != len(t):
            return False
        s_set = set(s)
        t_set = set(t)
        return s_set == t_set


class TestCode(unittest.TestCase):
    def test_aa_a(self):
        self.assertFalse(Solution().isAnagram('aa', 'a'))

    def test_a_b(self):
        self.assertFalse(Solution().isAnagram('a', 'b'))

    def test_anagram_nagaram(self):
        self.assertTrue(Solution().isAnagram('anagram', 'nagaram'))

    def test_rat_car(self):
        self.assertFalse(Solution().isAnagram('rat', 'car'))
