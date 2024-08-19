#!/usr/bin/env python3
# https://leetcode.com/problems/3sum-closest/

import unittest
from typing import List


class Solution:
    def threeSumClosest(self, nums: List[int], target: int) -> int:
        nums.sort()
        result = sum(nums[:3])
        length = len(nums)
        for i in range(length - 2):
            j, k = i + 1, length - 1
            while j < k:
                summed = nums[i] + nums[j] + nums[k]
                if summed == target:
                    return summed
                if abs(summed - target) < abs(result - target):
                    result = summed
                if summed < target:
                    j += 1
                else:
                    k -= 1
        return result


class TestCode(unittest.TestCase):
    def test_example_1(self):
        nums = [-1, 2, 1, -4]
        expected = 2
        self.assertEqual(expected, Solution().threeSumClosest(nums, 1))

    def test_example_2(self):
        nums = [0, 0, 0]
        expected = 0
        self.assertEqual(expected, Solution().threeSumClosest(nums, 1))

    def test_other_1(self):
        nums = [0, 1, 2, 3]
        expected = 3
        self.assertEqual(expected, Solution().threeSumClosest(nums, 3))
