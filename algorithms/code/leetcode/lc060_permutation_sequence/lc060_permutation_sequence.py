#!/usr/bin/env python3
# https://leetcode.com/problems/permutation-sequence/

import unittest


class Solution:
    def getPermutation(self, n: int, k: int) -> str:
        if n < 0 or k < 0:
            return None
        result = ''
        remaining = []
        factorials = []
        factorials.append(0)
        factorial = 1
        for i in range(1, n + 1):
            factorial *= i
            factorials.append(factorial)
            remaining.append(i)
        for i in range(1, n):
            block = factorials[n - i]
            index = (k - 1) // block
            result += str(remaining.pop(index))
            k -= index * block
        result += str(remaining.pop(0))
        return result


class TestCode(unittest.TestCase):
    def test_2_1(self):
        self.assertEqual('12', Solution().getPermutation(2, 1))

    def test_3_2(self):
        self.assertEqual('132', Solution().getPermutation(3, 2))

    def test_nothing(self):
        self.assertIsNone(Solution().getPermutation(1, -1))
        self.assertIsNone(Solution().getPermutation(-1, 1))
