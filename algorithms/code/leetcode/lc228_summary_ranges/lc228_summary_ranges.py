#!/usr/bin/env python3
# https://leetcode.com/problems/summary-ranges/

import unittest
from typing import List


class Solution:
    def summaryRanges(self, nums: List[int]) -> List[str]:
        n = len(nums)
        if n == 0:
            return []
        if n == 1:
            return [str(nums[0])]
        result = []
        begin, end = 0, 1
        while end < n:
            if nums[end] - nums[end - 1] != 1:
                if begin == end - 1:
                    result.append(str(nums[begin]))
                else:
                    result.append(f'{nums[begin]}->{nums[end - 1]}')
                begin = end
            end += 1
        if begin == end - 1:
            result.append(str(nums[begin]))
        else:
            result.append(f'{nums[begin]}->{nums[end - 1]}')
        return result


class TestCode(unittest.TestCase):
    def test_example_1(self):
        self.assertListEqual(
            ['0->2', '4->5', '7'],
            Solution().summaryRanges([0, 1, 2, 4, 5, 7]),
        )

    def test_example_2(self):
        self.assertListEqual(
            ['0', '2->4', '6', '8->9'],
            Solution().summaryRanges([0, 2, 3, 4, 6, 8, 9]),
        )

    def test_empty(self):
        self.assertListEqual([], Solution().summaryRanges([]))

    def test_single(self):
        self.assertListEqual(['0'], Solution().summaryRanges([0]))
