#!/usr/bin/env python3
# https://leetcode.com/problems/increasing-triplet-subsequence/

import unittest
from typing import List


class Solution:
    def increasingTriplet(self, nums: List[int]) -> bool:
        if len(nums) < 3:
            return False
        minBefore = [0] * len(nums)
        maxAfter = [0] * len(nums)
        minBefore[0] = nums[0]
        maxAfter[-1] = nums[-1]
        for i in range(1, len(nums) - 1):
            minBefore[i] = min(minBefore[i - 1], nums[i - 1])
            maxAfter[len(nums) - 1 - i] = max(
                maxAfter[len(nums) - i], nums[len(nums) - i]
            )
        for i in range(1, len(nums) - 1):
            if minBefore[i] < nums[i] and nums[i] < maxAfter[i]:
                return True
        return False


class TestCode(unittest.TestCase):
    def test_empty(self):
        self.assertFalse(Solution().increasingTriplet([]))

    def test_example_1(self):
        self.assertTrue(Solution().increasingTriplet([1, 2, 3, 4, 5]))

    def test_example_2(self):
        self.assertFalse(Solution().increasingTriplet([5, 4, 3, 2, 1]))

    def test_other(self):
        self.assertTrue(Solution().increasingTriplet([1, 2, 3, 1, 2, 1]))

    def test_nothing(self):
        self.assertFalse(Solution().increasingTriplet([]))
        self.assertFalse(Solution().increasingTriplet([0, 1]))

    def test_516(self):
        self.assertFalse(Solution().increasingTriplet([5, 1, 6]))

    def test_2_4_minus2_minus3(self):
        self.assertFalse(Solution().increasingTriplet([2, 4, -2, -3]))
