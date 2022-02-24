#!/usr/bin/env python3
# https://leetcode.com/problems/rotate-array/

import unittest
from typing import List


class Solution:
    def __reverse(self, nums, a, b):
        while a < b:
            nums[a], nums[b] = nums[b], nums[a]
            a += 1
            b -= 1

    def rotate(self, nums: List[int], k: int) -> None:
        self.__reverse(nums, 0, len(nums) - 1)
        self.__reverse(nums, 0, k - 1)
        self.__reverse(nums, k, len(nums) - 1)


class TestCode(unittest.TestCase):
    def test_1_2_3_4_5_6_7__3(self):
        nums = [1, 2, 3, 4, 5, 6, 7]
        Solution().rotate(nums, 3)
        expected = [5, 6, 7, 1, 2, 3, 4]
        self.assertListEqual(expected, nums)

    def test_1_2_3_4_5_6__2(self):
        nums = [1, 2, 3, 4, 5, 6]
        Solution().rotate(nums, 2)
        expected = [5, 6, 1, 2, 3, 4]
        self.assertListEqual(expected, nums)

    def test_1__2(self):
        nums = [1]
        Solution().rotate(nums, 1)
        expected = [1]
        self.assertListEqual(expected, nums)
