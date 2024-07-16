#!/usr/bin/env python3
# https://leetcode.com/problems/find-all-duplicates-in-an-array/

import unittest
from typing import List


class Solution:
    def dailyTemperatures(self, temperatures: List[int]) -> List[int]:
        stack: List[int] = []
        result = [0] * len(temperatures)
        for index, temperature in enumerate(temperatures):
            while stack and temperature > temperatures[stack[-1]]:
                earlier_index = stack.pop()
                result[earlier_index] = index - earlier_index
            stack.append(index)
        return result


class TestCode(unittest.TestCase):
    def test_example_1(self):
        self.assertListEqual(
            [1, 1, 4, 2, 1, 1, 0, 0],
            Solution().dailyTemperatures([73, 74, 75, 71, 69, 72, 76, 73])
        )

    def test_example_2(self):
        self.assertListEqual(
            [1, 1, 1, 0],
            Solution().dailyTemperatures([30, 40, 50, 60])
        )

    def test_example_3(self):
        self.assertListEqual(
            [1, 1, 0],
            Solution().dailyTemperatures([30, 60, 90])
        )
