# https://leetcode.com/problems/gas-station/

import unittest


class Solution:
    def canCompleteCircuit(self, gas: list[int], cost: list[int]) -> int:
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
    def test_example(self) -> None:
        gas = [99, 99, 99, 104]
        cost = [100, 100, 100, 100]
        assert Solution().canCompleteCircuit(gas, cost) == 3

    def test_other(self) -> None:
        gas = [1, 2, 3, 4, 5]
        cost = [3, 4, 5, 1, 2]
        assert Solution().canCompleteCircuit(gas, cost) == 3

    def test_another(self) -> None:
        gas = [1, 2, 3]
        cost = [3, 4, 3]
        assert Solution().canCompleteCircuit(gas, cost) == -1
