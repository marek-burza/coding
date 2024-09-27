#!/usr/bin/env python3
# https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/

import unittest
from typing import List


class Solution:
    def __bsInfimum(self, nums, target):
        a = 0
        z = len(nums) - 1
        while a < z:
            m = (a + z) >> 1
            if nums[m] < target:
                a = m + 1
            if nums[m] == target:
                z = m
            if nums[m] > target:
                z = m - 1
        if a == z and nums[a] == target:
            return a
        return -1

    def __bsSupremum(self, nums, target):
        a = 0
        z = len(nums) - 1
        while a < z:
            m = (1 + a + z) >> 1
            if nums[m] < target:
                a = m + 1
            if nums[m] == target:
                a = m
            if nums[m] > target:
                z = m - 1
        if a == z and nums[a] == target:
            return a
        return -1

    def searchRange(self, nums: List[int], target: int) -> List[int]:
        if nums is None or len(nums) == 0:
            return [-1, -1]
        infimum = self.__bsInfimum(nums, target)
        supremum = self.__bsSupremum(nums, target)
        return [infimum, supremum]


class TestCode(unittest.TestCase):
    def test_example_1(self):
        nums = [5, 7, 7, 8, 8, 10]
        expected = [3, 4]
        self.assertListEqual(expected, Solution().searchRange(nums, 8))

    def test_other(self):
        nums = [5, 7, 7, 8, 8, 10]
        expected = [-1, -1]
        self.assertListEqual(expected, Solution().searchRange(nums, 6))

    def test_another(self):
        nums = [2, 2]
        expected = [-1, -1]
        self.assertListEqual(expected, Solution().searchRange(nums, 3))

    def test_nothing(self):
        expected = [-1, -1]
        self.assertListEqual(expected, Solution().searchRange(None, 3))
        self.assertListEqual(expected, Solution().searchRange([], 3))
