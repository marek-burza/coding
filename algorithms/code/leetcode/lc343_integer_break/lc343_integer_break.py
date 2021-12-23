#!/usr/bin/env python3
# https://leetcode.com/problems/integer-break/

import unittest


class Solution:
    def integerBreak(self, n: int) -> int:
        if n == 2:
            return 1
        if n == 3:
            return 2
        if n == 5:
            return 6
        threes = n // 3
        rest = n - 3 * (threes - 1)
        rest = 6 if rest == 5 else rest
        return (3 ** (threes - 1)) * rest
        # product = 1
        # while n > 4:
        #     product *= 3
        #     n -= 3
        # return product * n


class TestCode(unittest.TestCase):
    def test_2(self):
        self.assertEqual(1, Solution().integerBreak(2))

    def test_3(self):
        self.assertEqual(2, Solution().integerBreak(3))

    def test_4(self):
        self.assertEqual(4, Solution().integerBreak(4))

    def test_5(self):
        self.assertEqual(6, Solution().integerBreak(5))

    def test_6(self):
        self.assertEqual(9, Solution().integerBreak(6))

    def test_7(self):
        self.assertEqual(12, Solution().integerBreak(7))

    def test_8(self):
        self.assertEqual(18, Solution().integerBreak(8))

    def test_9(self):
        self.assertEqual(27, Solution().integerBreak(9))

    def test_10(self):
        self.assertEqual(36, Solution().integerBreak(10))
