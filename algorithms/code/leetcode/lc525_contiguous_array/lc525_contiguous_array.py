#!/usr/bin/env python3
# https://leetcode.com/problems/contiguous-array/

import unittest
from typing import List


class Solution:
    def findMaxLength(self, nums: List[int]) -> int:
        sums_before = {0: 0}  # Sum 0 was at index 0
        maximum = running_sum = 0
        for i, value in enumerate(nums):
            running_sum += 1 if value == 1 else -1
            if running_sum in sums_before:
                maximum = max(maximum, i + 1 - sums_before[running_sum])
            else:
                sums_before[running_sum] = i + 1
        return maximum


class TestCode(unittest.TestCase):
    def test_example_1(self):
        self.assertEqual(2, Solution().findMaxLength([0, 1]))

    def test_example_2(self):
        self.assertEqual(2, Solution().findMaxLength([0, 1, 0]))

    def test_other_1(self):
        self.assertEqual(
            6, Solution().findMaxLength([0, 0, 1, 0, 0, 0, 1, 1])
        )
