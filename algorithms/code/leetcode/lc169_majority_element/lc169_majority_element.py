#!/usr/bin/env python3
# https://leetcode.com/problems/majority-element/

import collections
import unittest
from typing import List


class Solution:
    def majorityElement(self, nums: List[int]) -> int:
        frequencies: collections.Counter = collections.Counter()
        for value in nums:
            frequencies[value] += 1
        result = 0  # Instead of -inf
        count = 0  # Instead of -inf
        for value, frequencies_value in frequencies.items():
            other = frequencies_value
            if count <= other:
                result = value
                count = other
        return result


class TestCode(unittest.TestCase):
    def test_1_2_3_1_5_1_6_1(self):
        nums = [1, 2, 3, 1, 5, 1, 6, 1]
        self.assertEqual(1, Solution().majorityElement(nums))
