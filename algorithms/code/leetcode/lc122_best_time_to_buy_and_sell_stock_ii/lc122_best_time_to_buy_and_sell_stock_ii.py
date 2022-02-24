#!/usr/bin/env python3
# https://leetcode.com/problems/best-time-to-buy-and-sell-stock-ii/

import unittest
from typing import List


class Solution:
    def maxProfit(self, prices: List[int]) -> int:
        if len(prices) == 0:
            return 0
        profit = 0
        previous = prices[0]
        for value in prices:
            if value > previous:
                profit += value - previous
            previous = value
        return profit


class TestCode(unittest.TestCase):
    def test_empty(self):
        self.assertEqual(0, Solution().maxProfit([]))

    def test_example(self):
        self.assertEqual(16, Solution().maxProfit(
            [1, 2, 1, 3, 2, 5, 0, 10, 9, 6, 3]))
