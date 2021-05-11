#!/usr/bin/env python3
# https://leetcode.com/problems/reverse-integer/

import unittest


class Solution:
    def reverse(self, x: int) -> int:
        if x == -2147483648:
            return 0
        negative = x < 0
        if negative:
            x = -x
        digits = []
        while x > 0:
            digits.append(x % 10)
            x //= 10
        limits = [2, 1, 4, 7, 4, 8, 3, 6, 4, 7]
        length = len(limits)
        padding = length - len(digits)
        prefix = padding * [0]
        digits = prefix + digits
        for i in range(length):
            if digits[i] > limits[i]:
                return 0
            if digits[i] < limits[i]:
                break
        result = 0
        for digit in digits:
            result = result * 10 + digit
        if negative:
            result = -result
        return result


class TestCode(unittest.TestCase):
    def test_minus2000000002(self):
        self.assertEqual(Solution().reverse(2000000002), 2000000002)

    def test_minus2147483648(self):
        self.assertEqual(Solution().reverse(-2147483648), 0)

    def test_1000000003(self):
        self.assertEqual(Solution().reverse(1000000003), 0)

    def test_1534236469(self):
        self.assertEqual(Solution().reverse(1534236469), 0)

    def test_minus321(self):
        self.assertEqual(Solution().reverse(-321), -123)
