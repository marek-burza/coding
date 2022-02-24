#!/usr/bin/env python3
# https://leetcode.com/problems/two-sum/

import unittest

from typing import List, Dict


class Solution:
    def twoSum(self, nums: List[int], target: int) -> List[int]:
        if nums is None:
            return None
        mapped: Dict[int, int] = {}
        for i, num in enumerate(nums):
            expected = target - num
            if expected in mapped:
                found = mapped[expected]
                return [found, i]
            mapped[num] = i
        return None


class TestCode(unittest.TestCase):
    def test_0(self):
        self.assertEqual(Solution().twoSum(None, 0), None)

    def test_00(self):
        self.assertEqual(Solution().twoSum([], 0), None)

    def test_1(self):
        self.assertEqual(Solution().twoSum([2, 7, 11, 15], 9), [0, 1])

    def test_2(self):
        self.assertEqual(Solution().twoSum([3, 2, 4], 6), [1, 2])

    def test_3(self):
        self.assertEqual(Solution().twoSum([3, 3], 6), [0, 1])
