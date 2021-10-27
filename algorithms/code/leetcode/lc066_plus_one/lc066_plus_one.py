#!/usr/bin/env python3
# https://leetcode.com/problems/plus-one/

import unittest


class Solution:
    def plusOne(self, digits):
        carry = 1
        for i in range(len(digits) - 1, -1, -1):
            digits[i] += carry
            carry = digits[i] // 10
            digits[i] = digits[i] % 10
        if carry > 0:
            bigger = digits.copy()
            bigger.insert(0, carry)
            return bigger
        return digits


class TestCode(unittest.TestCase):
    def test_1_9(self):
        expected = [2, 0]
        self.assertListEqual(expected, Solution().plusOne([1, 9]))

    def test_9_9(self):
        expected = [1, 0, 0]
        self.assertListEqual(expected, Solution().plusOne([9, 9]))
