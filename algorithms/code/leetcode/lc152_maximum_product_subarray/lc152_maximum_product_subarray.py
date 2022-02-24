#!/usr/bin/env python3
# https://leetcode.com/problems/maximum-product-subarray/

import unittest
from typing import List


class Solution:
    def maxProduct(self, nums: List[int]) -> int:
        maximum = float('-inf')
        cmin = 0
        cmax = 0
        for num in nums:
            tmin = num
            tmax = num
            if cmin != 0:
                tmin *= cmin
            if cmax != 0:
                tmax *= cmax
            cmin = tmin if tmin < tmax else tmax
            cmin = num if num < cmin else cmin
            cmax = tmax if tmin < tmax else tmin
            cmax = cmax if num < cmax else num
            if cmax > maximum:
                maximum = cmax
        return maximum


class TestCode(unittest.TestCase):
    def test_2_3_Minus2_4(self):
        nums = [2, 3, -2, 4]
        self.assertEqual(6, Solution().maxProduct(nums))
