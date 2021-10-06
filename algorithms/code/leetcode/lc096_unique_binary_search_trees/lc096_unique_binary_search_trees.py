#!/usr/bin/env python3
# https://leetcode.com/problems/unique-binary-search-trees/

import unittest


class Solution:
    def numTrees(self, n: int) -> int:
        cache = [0] * (n + 1)
        cache[0] = 1
        cache[1] = 1
        for i in range(2, n + 1):
            for j in range(i):
                cache[i] += cache[j] * cache[i - j - 1]
        return cache[n]


class TestCode(unittest.TestCase):
    def test_2(self):
        self.assertEqual(Solution().numTrees(2), 2)

    def test_3(self):
        self.assertEqual(Solution().numTrees(3), 5)

    def test_19(self):
        self.assertEqual(Solution().numTrees(19), 1767263190)
