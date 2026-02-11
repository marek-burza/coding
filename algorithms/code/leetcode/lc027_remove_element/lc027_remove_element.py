#!/usr/bin/env python3
# https://leetcode.com/problems/remove-element/

import unittest
from typing import List


class Solution:
    def removeElement(self, nums: List[int], val: int) -> int:
        index = 0
        for i, _ in enumerate(nums):
            nums[index] = nums[i]
            if nums[i] != val:
                index += 1
        return index


class TestCode(unittest.TestCase):
    def test_0_42_1_2_42_3_4__42(self):
        nums = [0, 42, 1, 2, 42, 3, 4]
        length = Solution().removeElement(nums, 42)
        self.assertEqual(length, 5)
        expected = [0, 1, 2, 3, 4]
        self.assertListEqual(expected, nums[:length])

    def test_nothing(self):
        length = Solution().removeElement([], 42)
        self.assertEqual(length, 0)
