#!/usr/bin/env python3
# https://leetcode.com/problems/subsets/

import unittest
from typing import List


class Solution:
    def __subsets(self, nums, offset, current, listed):
        listed.append(current.copy())
        for i in range(offset, len(nums)):
            current.append(nums[i])
            self.__subsets(nums, i + 1, current, listed)
            current.pop()

    def subsets(self, nums: List[int]) -> List[List[int]]:
        nums.sort()
        listed: List[List[int]] = []
        self.__subsets(nums, 0, [], listed)
        return listed


class TestCode(unittest.TestCase):
    def __test(self, expected, result):
        result = sorted(result)
        expected = sorted(expected)
        self.assertEqual(len(expected), len(result))
        for i, expected_i in enumerate(expected):
            self.assertEqual(len(expected_i), len(result[i]))
            result_i = sorted(result[i])
            expected_i = sorted(expected_i)
            self.assertListEqual(expected_i, result_i)

    def test_1_2_3(self):
        listed = Solution().subsets([1, 2, 3])
        expected = [[], [1], [2], [3], [1, 2], [1, 3], [2, 3], [1, 2, 3]]
        self.__test(expected, listed)
