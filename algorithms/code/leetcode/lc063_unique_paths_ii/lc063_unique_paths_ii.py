#!/usr/bin/env python3
# https://leetcode.com/problems/unique-paths-ii/

import unittest
from typing import List


class Solution:
    def uniquePathsWithObstacles(self, obstacleGrid: List[List[int]]) -> int:
        grid = obstacleGrid
        max_m = len(grid)
        max_n = len(grid[0])
        cache = [[0] * max_n for _ in range(max_m)]
        if 1 in [grid[0][0], grid[max_m - 1][max_n - 1]]:
            return 0
        cache[max_m - 1][max_n - 1] = 1
        for m in range(max_m - 1, -1, -1):
            for n in range(max_n - 1, -1, -1):
                if grid[m][n] == 1:
                    cache[m][n] = 0
                else:
                    if m + 1 < max_m:
                        cache[m][n] += cache[m + 1][n]
                    if n + 1 < max_n:
                        cache[m][n] += cache[m][n + 1]
        return cache[0][0]


class TestCode(unittest.TestCase):
    def test_example_1(self):
        self.assertEqual(
            2,
            Solution().uniquePathsWithObstacles(
                [[0, 0, 0], [0, 1, 0], [0, 0, 0]]
            ),
        )

    def test_example_2(self):
        self.assertEqual(
            1,
            Solution().uniquePathsWithObstacles(
                [[0, 1], [0, 0]],
            ),
        )

    def test_other_1(self):
        self.assertEqual(
            0,
            Solution().uniquePathsWithObstacles(
                [[1, 0], [0, 0]],
            ),
        )

    def test_other_2(self):
        self.assertEqual(
            0,
            Solution().uniquePathsWithObstacles(
                [[0, 0], [0, 1]],
            ),
        )
