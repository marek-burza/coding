#!/usr/bin/env python3
# https://leetcode.com/problems/find-peak-element/

import unittest
from typing import List


class Solution:
    def findPeakElement(self, nums: List[int]) -> int:
        for i in range(1, len(nums) + 1):
            postFalling = None
            postFalling = i == len(nums) or nums[i - 1] > nums[i]
            if postFalling:
                return i - 1
        return -1


class TestCode(unittest.TestCase):
    def test_1_2_3_1(self):
        self.assertEqual(2, Solution().findPeakElement([1, 2, 3, 1]))

    def test_1_2_3_4(self):
        self.assertEqual(3, Solution().findPeakElement([1, 2, 3, 4]))

    def test_nothing(self):
        self.assertEqual(-1, Solution().findPeakElement([]))
