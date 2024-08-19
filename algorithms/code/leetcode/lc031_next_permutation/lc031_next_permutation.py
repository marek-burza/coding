#!/usr/bin/env python3
# https://leetcode.com/problems/next-permutation/

import unittest
from typing import List


class Solution:
    def nextPermutation(self, nums: List[int]) -> None:
        index1 = -1
        length = len(nums)
        for i in range(length - 2, -1, -1):
            if nums[i] < nums[i + 1]:
                index1 = i
                break
        if index1 == -1:
            nums.reverse()
        else:
            index2 = -1
            for i in range(length - 1, -1, -1):
                if nums[i] > nums[index1]:
                    index2 = i
                    break
            exchange = nums[index1]
            nums[index1] = nums[index2]
            nums[index2] = exchange
            nums[index1 + 1:] = nums[index1 + 1:][::-1]


class TestCode(unittest.TestCase):
    def test_example_1(self):
        nums = [1, 2, 3]
        Solution().nextPermutation(nums)
        self.assertListEqual([1, 3, 2], nums)

    def test_example_2(self):
        nums = [3, 2, 1]
        Solution().nextPermutation(nums)
        self.assertListEqual([1, 2, 3], nums)

    def test_example_3(self):
        nums = [1, 1, 5]
        Solution().nextPermutation(nums)
        self.assertListEqual([1, 5, 1], nums)
