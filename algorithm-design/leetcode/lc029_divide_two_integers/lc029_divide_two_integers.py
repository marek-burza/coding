#!/usr/bin/env python3
# https://leetcode.com/problems/divide-two-integers/

import unittest


class Solution:
    MAX_VALUE = 2147483647

    def divide(self, dividend: int, divisor: int) -> int:
        if divisor == 0:
            return Solution.MAX_VALUE
        if dividend == 0:
            return 0
        sign = (dividend / abs(dividend)) * (divisor / abs(divisor))
        dividend = abs(dividend)
        divisor = abs(divisor)
        counter = 0
        while dividend >= divisor:
            subtractor = divisor
            incrementor = 1
            while dividend - subtractor - subtractor >= 0:
                subtractor += subtractor
                incrementor += incrementor
            dividend -= subtractor
            counter += incrementor
        if sign * counter > Solution.MAX_VALUE:
            return Solution.MAX_VALUE
        return sign * counter


class TestCode(unittest.TestCase):
    def test_Minus1010369383_Minus2147483648(self):
        self.assertEqual(
            Solution().divide(-1010369383, -2147483648),
            -1010369383 / -2147483648)

    def test_Minus2147483648_Minus1(self):
        self.assertEqual(
            Solution().divide(-2147483648, -1),
            Solution.MAX_VALUE)

    def test_divisor_0(self):
        self.assertEqual(
            Solution().divide(1, 0),
            Solution.MAX_VALUE)

    def test_dividend_0(self):
        self.assertEqual(
            Solution().divide(0, 1),
            0)
