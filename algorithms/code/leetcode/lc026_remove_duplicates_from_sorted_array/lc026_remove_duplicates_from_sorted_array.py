#!/usr/bin/env python3
# https://leetcode.com/problems/remove-duplicates-from-sorted-array/

import unittest
from typing import List


class Solution:
    def removeDuplicates(self, nums: List[int]) -> int:
        counter = 0
        for i in range(1, len(nums)):
            spot = counter
            if nums[i] == nums[i - 1 - spot]:
                counter += 1
            nums[i - spot] = nums[i]
        return len(nums) - counter


class TestCode(unittest.TestCase):
    def test_1_2_2_3_4_4_7(self):
        nums1 = [1, 2, 2, 3, 4, 4, 7]
        length = Solution().removeDuplicates(nums1)
        self.assertEqual(length, 5)
        expected = [1, 2, 3, 4, 7]
        self.assertListEqual(expected, nums1[0:length])
