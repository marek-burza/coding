#!/usr/bin/env python3
# https://leetcode.com/problems/search-in-rotated-sorted-array/

import unittest
from typing import List


class Solution:
    def search(self, nums: List[int], target: int) -> int:
        a = 0
        z = len(nums) - 1
        while a <= z:
            m = (a + z) >> 1
            if nums[m] == target:
                return m
            if nums[m] < target:
                if target <= nums[z] or nums[a] < nums[m]:
                    a = m + 1
                else:
                    z = m - 1
            else:
                if nums[a] <= target or nums[m] < nums[z]:
                    z = m - 1
                else:
                    a = m + 1
        return -1


class TestCode(unittest.TestCase):
    def test_example(self):
        nums = [4, 5, 6, 7, 0, 1, 2]
        self.assertEqual(5, Solution().search(nums, 1))

    def test_single(self):
        nums = [1]
        self.assertEqual(0, Solution().search(nums, 1))

    def test_other(self):
        nums = [4, 5, 6, 7, 0, 1, 2]
        self.assertEqual(-1, Solution().search(nums, 3))

    def test_another(self):
        nums = [4, 5, 6, 7, 0, 1, 2]
        self.assertEqual(4, Solution().search(nums, 0))

    def test_1_3__3(self):
        nums = [1, 3]
        self.assertEqual(1, Solution().search(nums, 3))

    def test_3_5_1__3(self):
        nums = [3, 5, 1]
        self.assertEqual(0, Solution().search(nums, 3))

    def test_5_1_2_3_4__1(self):
        nums = [5, 1, 2, 3, 4]
        self.assertEqual(1, Solution().search(nums, 1))
