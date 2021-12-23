#!/usr/bin/env python3
# https://leetcode.com/problems/remove-duplicates-from-sorted-array-ii/

import unittest

from typing import List


class Solution:
    def removeDuplicates(self, nums: List[int]) -> int:
        i = 0
        for n in nums:
            if i < 2 or n > nums[i - 2]:
                nums[i] = n
                i += 1
        return i


class TestCode(unittest.TestCase):
    def test_example(self):
        nums = [1, 1, 1, 2, 2, 3]
        expected = [1, 1, 2, 2, 3]
        self.assertEqual(5, Solution().removeDuplicates(nums))
        self.assertListEqual(expected, nums[:len(expected)])

    def test_1_1_1_1_3_3(self):
        nums = [1, 1, 1, 1, 3, 3]
        expected = [1, 1, 3, 3]
        self.assertEqual(4, Solution().removeDuplicates(nums))
        self.assertListEqual(expected, nums[:len(expected)])

    def test_1_1(self):
        nums = [1, 1]
        expected = [1, 1]
        self.assertEqual(2, Solution().removeDuplicates(nums))
        self.assertListEqual(expected, nums[:len(expected)])

    def test_1_2_2(self):
        nums = [1, 2, 2]
        expected = [1, 2, 2]
        self.assertEqual(3, Solution().removeDuplicates(nums))
        self.assertListEqual(expected, nums[:len(expected)])
