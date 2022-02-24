#!/usr/bin/env python3
# https://leetcode.com/problems/product-of-array-except-self/

import unittest
from typing import List


class Solution:
    def productExceptSelf(self, nums: List[int]) -> List[int]:
        result = [0] * len(nums)
        result[0] = 1
        for i in range(1, len(nums)):
            result[i] = result[i - 1] * nums[i - 1]
        other = 1
        for i in range(len(nums) - 2, -1, -1):
            other *= nums[i + 1]
            result[i] *= other
        return result


class TestCode(unittest.TestCase):
    def test__1_2_3_4(self):
        nums = [1, 2, 3, 4]
        expected = [24, 12, 8, 6]
        actual = Solution().productExceptSelf(nums)
        self.assertListEqual(expected, actual)

    def test__9_0_Minus2(self):
        nums = [9, 0, -2]
        expected = [0, -18, 0]
        actual = Solution().productExceptSelf(nums)
        self.assertListEqual(expected, actual)
