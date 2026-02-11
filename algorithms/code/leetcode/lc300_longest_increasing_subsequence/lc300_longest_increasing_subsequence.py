#!/usr/bin/env python3
# https://leetcode.com/problems/longest-increasing-subsequence/

import unittest
from typing import List


class Solution:
    def lengthOfLIS(self, nums: List[int]) -> int:
        if len(nums) == 0:
            return 0
        lis = [1] * len(nums)
        for i in range(1, len(nums)):
            for j in range(i):
                if nums[i] > nums[j]:
                    lis[i] = max(lis[i], lis[j] + 1)
        maximum = max(lis)
        return maximum


class TestCode(unittest.TestCase):
    def test_example(self):
        nums = [10, 9, 2, 5, 3, 7, 101, 18]
        self.assertEqual(4, Solution().lengthOfLIS(nums))

    def test_nothing(self):
        self.assertEqual(0, Solution().lengthOfLIS([]))
