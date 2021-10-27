#!/usr/bin/env python3
# https://leetcode.com/problems/search-insert-position/

import unittest


class Solution:
    def searchInsert(self, nums, target):
        a = 0
        z = len(nums)
        while a != z:
            m = (a + z) >> 1
            if nums[m] < target:
                a = m + 1
            else:
                z = m
        return z


class TestCode(unittest.TestCase):
    def test__empty__5(self):
        nums = []
        self.assertEqual(0, Solution().searchInsert(nums, 5))

    def test__1_3_5_6__5(self):
        nums = [1, 3, 5, 6]
        self.assertEqual(2, Solution().searchInsert(nums, 5))

    def test__1_3_5_6__2(self):
        nums = [1, 3, 5, 6]
        self.assertEqual(1, Solution().searchInsert(nums, 2))

    def test__1_3_5_6__7(self):
        nums = [1, 3, 5, 6]
        self.assertEqual(4, Solution().searchInsert(nums, 7))

    def test__1_3_5_6__0(self):
        nums = [1, 3, 5, 6]
        self.assertEqual(0, Solution().searchInsert(nums, 0))

    def test__1_3_5_6__1(self):
        nums = [1, 3, 5, 6]
        self.assertEqual(0, Solution().searchInsert(nums, 1))
