#!/usr/bin/env python3
# https://leetcode.com/problems/sqrtx/

import math
import unittest


class Solution:
    def mySqrt(self, x: int) -> int:
        a = 0
        z = x
        while a + 1 < z:
            m = math.trunc((a + z) / float(2))
            mm = m * m
            if mm == x:
                return int(m)
            if mm < x:
                a = m
            else:
                z = m - 1
        if z * z <= x:
            return int(z)
        return int(a)


class TestCode(unittest.TestCase):
    def test_example_1(self):
        self.assertEqual(2, Solution().mySqrt(4))

    def test_example_2(self):
        self.assertEqual(2, Solution().mySqrt(8))

    def test_64(self):
        self.assertEqual(8, Solution().mySqrt(64))

    def test_2(self):
        self.assertEqual(1, Solution().mySqrt(2))

    def test_1(self):
        self.assertEqual(1, Solution().mySqrt(1))
