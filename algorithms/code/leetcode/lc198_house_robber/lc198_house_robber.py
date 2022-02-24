#!/usr/bin/env python3
# https://leetcode.com/problems/house-robber/

import unittest
from typing import List


class Solution:
    def __rob(self, nums, offset, maxed):
        if len(nums) <= offset:
            return 0
        if offset in maxed:
            return maxed[offset]
        result = nums[offset] + self.__rob(nums, offset + 2, maxed)
        if offset + 1 < len(nums):
            other = nums[offset + 1] + self.__rob(nums, offset + 3, maxed)
            result = max(result, other)
        maxed[offset] = result
        return result

    def rob(self, nums: List[int]) -> int:
        return self.__rob(nums, 0, {})


class TestCode(unittest.TestCase):
    def test_6_6_4_8_4_3_3_10(self):
        nums = [6, 6, 4, 8, 4, 3, 3, 10]
        self.assertEqual(27, Solution().rob(nums))
