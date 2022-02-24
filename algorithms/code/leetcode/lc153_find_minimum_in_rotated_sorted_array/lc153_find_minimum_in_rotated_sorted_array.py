#!/usr/bin/env python3
# https://leetcode.com/problems/find-minimum-in-rotated-sorted-array/

import unittest
from typing import List


class Solution:
    def findMin(self, nums: List[int]) -> int:
        a = 0
        z = len(nums) - 1
        while a != z:
            # For sorted array return the smallest
            if nums[a] < nums[z]:
                return nums[a]
            # For only two elements pick the smaller
            if z - a == 1:
                return min(nums[a], nums[z])
            # Otherwise halve the search space
            m = (a + z) // 2
            if nums[a] < nums[m]:
                a = m
            if nums[m] < nums[z]:
                z = m
        return nums[a]


class TestCode(unittest.TestCase):
    def test_0_1_2_4_5_6_7(self):
        nums = [0, 1, 2, 4, 5, 6, 7]
        self.assertEqual(0, Solution().findMin(nums))

    def test_4_5_6_7_0_1_2(self):
        nums = [4, 5, 6, 7, 0, 1, 2]
        self.assertEqual(0, Solution().findMin(nums))

    def test_1_2(self):
        nums = [1, 2]
        self.assertEqual(1, Solution().findMin(nums))

    def test_2_1(self):
        nums = [2, 1]
        self.assertEqual(1, Solution().findMin(nums))

    def test_1(self):
        nums = [1]
        self.assertEqual(1, Solution().findMin(nums))
