#!/usr/bin/env python3
# https://leetcode.com/problems/merge-intervals/

import unittest
from typing import List


class Solution:
    def merge(self, intervals: List[List[int]]) -> List[List[int]]:
        spots = {}
        for interval in intervals:
            begin = interval[0]
            if begin not in spots:
                spots[begin] = [1, 0]
            else:
                spots[begin][0] += 1
            end = interval[1]
            if end not in spots:
                spots[end] = [0, 1]
            else:
                spots[end][1] += 1
        depth = 0
        merged: List[List[int]] = []
        for key in sorted(spots.keys()):
            if spots[key][0] != 0:
                if depth == 0:
                    merged.append([key, key])
                depth += spots[key][0]
            if spots[key][1] != 0:
                depth -= spots[key][1]
                if depth == 0:
                    merged[-1][1] = key
        return merged


class TestCode(unittest.TestCase):
    def test_example_1(self):
        self.assertListEqual(
            [[1, 6], [8, 10], [15, 18]],
            Solution().merge([[1, 3], [2, 6], [8, 10], [15, 18]]),
        )

    def test_example_2(self):
        self.assertListEqual(
            [[1, 5]],
            Solution().merge(
                [[1, 4], [4, 5]],
            ),
        )

    def test_other_1(self):
        self.assertListEqual(
            [[1, 5]],
            Solution().merge(
                [[1, 4], [1, 5]],
            ),
        )

    def test_other_2(self):
        self.assertListEqual(
            [[1, 5]],
            Solution().merge(
                [[1, 4], [4, 5]],
            ),
        )

    def test_other_3(self):
        self.assertListEqual(
            [[0, 0], [1, 2]],
            Solution().merge(
                [[1, 2], [0, 0]],
            ),
        )

    def test_other_4(self):
        self.assertListEqual(
            [[2, 4], [5, 5]],
            Solution().merge(
                [[2, 3], [5, 5], [2, 2], [3, 4], [3, 4]],
            ),
        )
