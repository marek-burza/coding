#!/usr/bin/env python3
# https://leetcode.com/problems/maximum-subarray/

import unittest


class Solution:
    def maxSubArray(self, nums):
        summed = 0
        minimum = 0
        maximum = float('-inf')
        i = 0
        while i < len(nums):
            minimum = summed if summed < minimum else minimum
            summed += nums[i]
            delta = summed - minimum
            maximum = delta if delta > maximum else maximum
            i += 1
        return maximum


class TestCode(unittest.TestCase):
    def test_example_1(self):
        self.assertEqual(
            6,
            Solution().maxSubArray(
                [-2, 1, -3, 4, -1, 2, 1, -5, 4]))

    def test_example_2(self):
        self.assertEqual(1, Solution().maxSubArray([1]))

    def test_example_3(self):
        self.assertEqual(23, Solution().maxSubArray([5, 4, -1, 7, 8]))

    def test_Minus2_1(self):
        self.assertEqual(1, Solution().maxSubArray([-2, 1]))

    def test_Minus2_Minus1(self):
        self.assertEqual(-1, Solution().maxSubArray([-2, -1]))
