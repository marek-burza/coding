#!/usr/bin/env python3
# https://leetcode.com/problems/missing-number/

import unittest
from typing import List


class Solution:
    def missingNumber(self, nums: List[int]) -> int:
        expected = len(nums) * (len(nums) + 1) // 2
        summed = 0
        for value in nums:
            summed += value
        return int(expected - summed)


class TestCode(unittest.TestCase):
    def test_example(self):
        self.assertEqual(2, Solution().missingNumber([0, 1, 3]))
