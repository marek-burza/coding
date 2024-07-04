#!/usr/bin/env python3
# https://leetcode.com/problems/gas-station/

import unittest
from typing import List


class Solution:
    def canCompleteCircuit(self, gas: List[int], cost: List[int]) -> int:
        minimum = float('inf')
        gauge = 0
        index = -1
        for i, _ in enumerate(gas):
            index = i % len(gas)
            gauge += gas[index] - cost[index]
            minimum = min(minimum, gauge)
        i = 0
        while minimum < 0 and i < len(gas):
            index = len(gas) - i - 1
            minimum += gas[index] - cost[index]
            i += 1
        return index if minimum >= 0 else -1  # minimum >= 0 or i < len(gas)


class TestCode(unittest.TestCase):
    def test_example(self):
        gas = [99, 99, 99, 104]
        cost = [100, 100, 100, 100]
        self.assertEqual(3, Solution().canCompleteCircuit(gas, cost))

    def test_other(self):
        gas = [1, 2, 3, 4, 5]
        cost = [3, 4, 5, 1, 2]
        self.assertEqual(3, Solution().canCompleteCircuit(gas, cost))

    def test_another(self):
        gas = [1, 2, 3]
        cost = [3, 4, 3]
        self.assertEqual(-1, Solution().canCompleteCircuit(gas, cost))
