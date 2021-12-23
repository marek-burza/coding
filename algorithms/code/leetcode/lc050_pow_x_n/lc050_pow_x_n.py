#!/usr/bin/env python3
# https://leetcode.com/problems/powx-n/

import unittest


class Solution:
    def myPow(self, x: float, n: int) -> float:
        if n == 0:
            return 1.0
        count = -int(n) if n < 0 else int(n)
        result = x
        power = 1
        powers = []
        while (power << 1) <= count:
            powers.append(result)
            result *= result
            power <<= 1
        previous = power >> 1
        for i in range(len(powers) - 1, -1, -1):
            repeat = (count - power) // previous
            value = powers[i]
            for _ in range(repeat):
                result *= value
            power += repeat * previous
            previous >>= 1
        result = 1.0 / result if n < 0 else result
        return result


class TestCode(unittest.TestCase):
    def test_smaller(self):
        x = 34.00515
        n = -3
        expected = x ** n
        self.assertEqual(expected, Solution().myPow(x, n), 0)

    def test_bigger(self):
        x = 0.00001
        n = 2147483647
        expected = x ** n
        self.assertEqual(expected, Solution().myPow(x, n), 0)

    def test_0(self):
        self.assertEqual(1.0, Solution().myPow(0, 0), 0)
