#!/usr/bin/env python3
# https://leetcode.com/problems/count-numbers-with-unique-digits/

import unittest


class Solution:
    def __count(self, prefix, n):
        if len(prefix) == n:
            return 1
        summed = 1
        digits = ['0', '1', '2', '3', '4', '5', '6', '7', '8', '9']
        first = 1 if len(prefix) == 0 else 0
        for i in range(first, len(digits)):
            if not digits[i] in prefix:
                summed += self.__count(prefix + digits[i], n)
        return summed

    def countNumbersWithUniqueDigits(self, n: int) -> int:
        return self.__count('', n)


class TestCode(unittest.TestCase):
    def test_example(self):
        self.assertEqual(91, Solution().countNumbersWithUniqueDigits(2))

    def test_0(self):
        self.assertEqual(1, Solution().countNumbersWithUniqueDigits(0))

    def test_1(self):
        self.assertEqual(10, Solution().countNumbersWithUniqueDigits(1))
