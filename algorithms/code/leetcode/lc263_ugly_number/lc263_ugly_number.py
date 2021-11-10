#!/usr/bin/env python3
# https://leetcode.com/problems/ugly-number/

import unittest


class Solution:
    def isUgly(self, num: int) -> bool:
        if num <= 0:
            return False
        if num == 1:
            return True
        original = num
        while num % 2 == 0:
            num //= 2
        while num % 3 == 0:
            num //= 3
        while num % 5 == 0:
            num //= 5
        return num != original and num == 1


class TestCode(unittest.TestCase):
    def test_minus(self):
        self.assertFalse(Solution().isUgly(-1))

    def test_0(self):
        self.assertFalse(Solution().isUgly(0))

    def test_1(self):
        self.assertTrue(Solution().isUgly(1))

    def test_2(self):
        self.assertTrue(Solution().isUgly(2))

    def test_3(self):
        self.assertTrue(Solution().isUgly(3))

    def test_7(self):
        self.assertFalse(Solution().isUgly(7))

    def test_11(self):
        self.assertFalse(Solution().isUgly(11))

    def test_14(self):
        self.assertFalse(Solution().isUgly(14))

    def test_16(self):
        self.assertTrue(Solution().isUgly(16))

    def test_27(self):
        self.assertTrue(Solution().isUgly(27))

    def test_937351770(self):
        self.assertFalse(Solution().isUgly(937351770))

    def test_905391974(self):
        self.assertFalse(Solution().isUgly(905391974))
