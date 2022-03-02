#!/usr/bin/env python3
# https://leetcode.com/problems/best-time-to-buy-and-sell-stock/

import unittest
from typing import List


class Solution:
    def maxProfit(self, prices: List[int]) -> int:
        if prices is None or len(prices) <= 1:
            return 0
        mins = [0 for _ in range(len(prices))]
        mins[0] = prices[0]
        i = 1
        while i < len(prices):
            mins[i] = prices[i] if prices[i] < mins[i - 1] else mins[i - 1]
            i += 1
        profit = min(prices) - max(mins)  # Instead of -inf
        maximum = prices[len(prices) - 1]
        for i in range(len(prices) - 1, -1, -1):
            maximum = prices[i] if prices[i] > maximum else maximum
            delta = maximum - mins[i]
            profit = delta if delta > profit else profit
        return profit


class TestCode(unittest.TestCase):
    def test_empty(self):
        self.assertEqual(0, Solution().maxProfit(None))
        self.assertEqual(0, Solution().maxProfit([]))

    def test_1(self):
        self.assertEqual(0, Solution().maxProfit([1]))

    def test_example_1(self):
        self.assertEqual(5, Solution().maxProfit([7, 1, 5, 3, 6, 4]))

    def test_example_2(self):
        self.assertEqual(0, Solution().maxProfit([7, 6, 4, 3, 1]))
