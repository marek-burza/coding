#!/usr/bin/env python3
# https://leetcode.com/problems/sort-colors/

import unittest

from typing import List


class Solution:
    def sortColors(self, nums: List[int]) -> None:
        counters = [0, 0, 0]
        for value in nums:
            counters[value] += 1
        i = 0
        j = 0
        while i < len(counters):
            k = 0
            while k < counters[i]:
                nums[j] = i
                k += 1
                j += 1
            i += 1


class TestCode(unittest.TestCase):
    def test_2(self):
        nums = [2]
        expected = [2]
        Solution().sortColors(nums)
        self.assertListEqual(expected, nums)

    def test_1_0(self):
        nums = [1, 0]
        expected = [0, 1]
        Solution().sortColors(nums)
        self.assertListEqual(expected, nums)
