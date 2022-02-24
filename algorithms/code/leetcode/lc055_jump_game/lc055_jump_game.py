#!/usr/bin/env python3
# https://leetcode.com/problems/jump-game/

import unittest
from typing import List


class Solution:
    def canJump(self, nums: List[int]) -> bool:
        if nums is None or len(nums) == 0:
            return False
        if len(nums) == 1:
            return True
        front = 0
        i = 0
        while True:  # i <= front:
            if front >= len(nums) - 1:
                return True
            if i == front and nums[front] == 0:
                return False
            if front < i + nums[i]:
                front = i + nums[i]
            i += 1
        # return False


class TestCode(unittest.TestCase):
    def test_25002_integers(self):
        nums1 = [0] * 25003
        for i, _ in enumerate(nums1):
            nums1[i] = 25000 - i
        nums1[25000] = 1
        nums1[25001] = 0
        nums1[25002] = 0
        self.assertFalse(Solution().canJump(nums1))

    def test_1_2_3(self):
        nums2 = [1, 2, 3]
        self.assertTrue(Solution().canJump(nums2))

    def test_nothing(self):
        self.assertFalse(Solution().canJump(None))
        self.assertFalse(Solution().canJump([]))
        self.assertTrue(Solution().canJump([0]))
