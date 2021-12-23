#!/usr/bin/env python3
# https://leetcode.com/problems/jump-game-ii/

import unittest

from typing import List


class Solution:
    def jump(self, nums: List[int]) -> int:
        if len(nums) == 1:
            return 0
        horizon = nums[0]
        i = 0
        count = 1
        while horizon < len(nums) - 1:
            replacement = horizon
            while i <= horizon:
                if i + nums[i] > replacement:
                    replacement = i + nums[i]
                i += 1
            i -= 1
            horizon = replacement
            count += 1
        return count


class TestCode(unittest.TestCase):
    def test_example_1(self):
        nums = [2, 3, 1, 1, 4]
        self.assertEqual(2, Solution().jump(nums))

    def test_nothing(self):
        nums = [0]
        self.assertEqual(0, Solution().jump(nums))
