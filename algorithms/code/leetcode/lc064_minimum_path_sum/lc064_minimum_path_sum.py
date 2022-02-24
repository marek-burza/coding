#!/usr/bin/env python3
# https://leetcode.com/problems/minimum-path-sum/

import unittest
from typing import List


class Solution:
    def minPathSum(self, grid: List[List[int]]) -> int:
        visited = [[False] * len(grid[0]) for _ in enumerate(grid)]
        sums = [[float('inf')] * len(grid[0]) for _ in enumerate(grid)]
        sums[0][0] = grid[0][0]
        queue = []
        queue.append([0, 0])
        while queue:
            at = queue.pop(0)
            if not visited[at[0]][at[1]]:
                visited[at[0]][at[1]] = True
                if at[0] + 1 < len(grid):
                    right = [at[0] + 1, at[1]]
                    queue.append(right)
                    summed = sums[at[0]][at[1]] + grid[right[0]][right[1]]
                    if summed < sums[right[0]][right[1]]:
                        sums[right[0]][right[1]] = summed
                if at[1] + 1 < len(grid[0]):
                    down = [at[0], at[1] + 1]
                    queue.append(down)
                    summed = sums[at[0]][at[1]] + grid[down[0]][down[1]]
                    # if summed < sums[down[0]][down[1]]:
                    sums[down[0]][down[1]] = summed
        return sums[-1][-1]


class TestCode(unittest.TestCase):
    def test_example(self):
        grid = [[1, 1, 2, 2], [2, 1, 2, 2], [2, 1, 1, 2], [2, 2, 1, 1]]
        self.assertEqual(7, Solution().minPathSum(grid))
