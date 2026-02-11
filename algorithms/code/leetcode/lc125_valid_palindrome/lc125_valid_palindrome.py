#!/usr/bin/env python3
# https://leetcode.com/problems/valid-palindrome/

import unittest


class Solution:
    def isPalindrome(self, s: str) -> bool:
        if len(s) == 0:
            return True
        i = 0
        j = len(s) - 1
        while i <= j:
            a = s[i]
            if not a.isalnum():
                i += 1
                continue
            b = s[j]
            if not b.isalnum():
                j -= 1
                continue
            if a.upper() != b.upper():
                return False
            i += 1
            j -= 1
        return True


class TestCode(unittest.TestCase):
    def test_a_man_a_plan_a_canal_Panama(self):
        self.assertTrue(
            Solution().isPalindrome('A man, a plan, a canal: Panama')
        )

    def test_race_a_car(self):
        self.assertFalse(Solution().isPalindrome('race a car'))

    def test_ava(self):
        self.assertTrue(Solution().isPalindrome('Ava'))

    def test_burger(self):
        self.assertFalse(Solution().isPalindrome('burger'))

    def test_nothing(self):
        self.assertTrue(Solution().isPalindrome(''))
