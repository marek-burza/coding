#!/usr/bin/env python3
# https://leetcode.com/problems/power-of-four/

import math
import unittest


class Solution:
    def isPowerOfFour(self, num):
        if num <= 0:
            return False
        value = math.log(num) / math.log(4)
        return value == math.floor(value)


class TestCode(unittest.TestCase):
    def test_16(self):
        self.assertTrue(Solution().isPowerOfFour(16))

    def test_5(self):
        self.assertFalse(Solution().isPowerOfFour(5))

    def test_non_positive(self):
        self.assertFalse(Solution().isPowerOfFour(0))
        self.assertFalse(Solution().isPowerOfFour(-1))
