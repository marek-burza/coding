#!/usr/bin/env python3
# https://leetcode.com/problems/insert-interval/

import unittest
from typing import List


class Solution:
    def insert(
        self,
        intervals: List[List[int]],
        newInterval: List[int],
    ) -> List[List[int]]:
        start = newInterval[0]
        end = newInterval[1]
        before: List[List[int]] = []
        after: List[List[int]] = []
        for interval in intervals:
            interval_start = interval[0]
            interval_end = interval[1]
            if interval_start > end:
                after.append(interval)
            elif interval_end < start:
                before.append(interval)
            else:
                start = min(start, interval_start)
                end = max(end, interval_end)
        return before + [[start, end]] + after


class TestCode(unittest.TestCase):
    def test_example_1(self):
        self.assertListEqual(
            [[1, 5], [6, 9]],
            Solution().insert([[1, 3], [6, 9]], [2, 5]),
        )

    def test_example_2(self):
        self.assertListEqual(
            [[1, 2], [3, 10], [12, 16]],
            Solution().insert(
                [[1, 2], [3, 5], [6, 7], [8, 10], [12, 16]],
                [4, 8],
            ),
        )
