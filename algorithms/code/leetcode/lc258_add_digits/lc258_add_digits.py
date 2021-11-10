#!/usr/bin/env python3
# https://leetcode.com/problems/add-digits/

import unittest


class Solution:
    def addDigits(self, num: int) -> int:
        while num >= 10:
            summed = 0
            while num > 0:
                digit = num % 10
                summed += digit
                num = num // 10
            num = summed
        return num
        # return num - 9 * (num - 1) // 9


class TestCode(unittest.TestCase):
    def test_example(self):
        self.assertEqual(2, Solution().addDigits(38))
