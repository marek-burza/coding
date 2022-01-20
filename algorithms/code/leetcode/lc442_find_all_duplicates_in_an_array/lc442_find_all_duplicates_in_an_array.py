#!/usr/bin/env python3
# https://leetcode.com/problems/find-all-duplicates-in-an-array/
# #google

import unittest

from typing import List


class Solution:
    def findDuplicates(self, nums: List[int]) -> List[int]:
        result = set()
        for i, _ in enumerate(nums):
            num = abs(nums[i])
            if nums[num - 1] < 0:
                result.add(num)
            else:
                nums[num - 1] = -nums[num - 1]
        return list(result)


class TestCode(unittest.TestCase):
    def test_example_1(self):
        self.assertListEqual(
            [2, 3], Solution().findDuplicates([4, 3, 2, 7, 8, 2, 3, 1]))

    def test_example_2(self):
        self.assertListEqual(
            [1], Solution().findDuplicates([1, 1, 2]))

    def test_example_3(self):
        self.assertListEqual(
            [], Solution().findDuplicates([1]))
