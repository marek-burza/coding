#!/usr/bin/env python3
# https://leetcode.com/problems/triangle/

import unittest
from typing import List


class Solution:
    def minimumTotal(self, triangle: List[List[int]]) -> int:
        if triangle is None or len(triangle) == 0 or len(triangle[0]) == 0:
            return 0
        height = len(triangle)
        sums = [0] * len(triangle[height - 1])
        sums[0] = triangle[0][0]
        for i in range(1, height):
            line = triangle[i]
            n = len(line)
            for j in range(n - 1, -1, -1):
                left = float('inf') if j == 0 else sums[j - 1]
                right = float('inf') if j == n - 1 else sums[j]
                sums[j] = min(left, right) + line[j]
        minimum = sums[0]
        for i, _ in enumerate(sums):
            if sums[i] < minimum:
                minimum = sums[i]
        return minimum


class TestCode(unittest.TestCase):
    def __construct(self, compact):
        triangle = []
        for array in compact:
            line = []
            triangle.append(line)
            for value in array:
                line.append(value)
        return triangle

    def test_example(self):
        triangle = [[2], [3, 4], [6, 5, 7], [4, 1, 8, 3]]
        self.assertEqual(
            11, Solution().minimumTotal(self.__construct(triangle))
        )

    def test_nothing(self):
        self.assertEqual(0, Solution().minimumTotal(None))
        self.assertEqual(0, Solution().minimumTotal([]))
        self.assertEqual(0, Solution().minimumTotal([[]]))
