#!/usr/bin/env python3
# https://leetcode.com/problems/unique-paths/

import unittest


class Solution:
    def __nck(self, n, k):
        if k > n:
            return 0
        if k * 2 > n:
            k = n - k
        if k == 0:
            return 1
        r = n
        for i in range(2, k + 1):
            r *= n - i + 1
            r //= i
        return r

    def uniquePaths(self, m: int, n: int) -> int:
        m -= 1
        return int(self.__nck(m + n - 1, m))


class TestCode(unittest.TestCase):
    def test_3_7(self):
        self.assertEqual(28, Solution().uniquePaths(3, 7))

    def test_59_5(self):
        self.assertEqual(557845, Solution().uniquePaths(59, 5))

    def test_1_10(self):
        self.assertEqual(1, Solution().uniquePaths(1, 10))

    def test_nothing(self):
        self.assertEqual(0, Solution().uniquePaths(1, 0))
