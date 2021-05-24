#!/usr/bin/env python3
# https://leetcode.com/problems/palindrome-number/

import unittest


class Solution:
    def isPalindrome(self, x: int) -> bool:
        if x < 0:
            return False
        xa = x
        xb = 0
        while x > 0:
            xb = xb * 10 + x % 10
            x //= 10
        return xa == xb


class TestCode(unittest.TestCase):
    def test_213(self):
        self.assertEqual(Solution().isPalindrome(213), False)

    def test_456(self):
        self.assertEqual(Solution().isPalindrome(456), False)

    def test_454(self):
        self.assertEqual(Solution().isPalindrome(454), True)

    def test_99(self):
        self.assertEqual(Solution().isPalindrome(99), True)

    def test_1(self):
        self.assertEqual(Solution().isPalindrome(1), True)

    def test_10(self):
        self.assertEqual(Solution().isPalindrome(10), False)

    def test_minus1(self):
        self.assertEqual(Solution().isPalindrome(-1), False)

    def test_0(self):
        self.assertEqual(Solution().isPalindrome(0), True)
