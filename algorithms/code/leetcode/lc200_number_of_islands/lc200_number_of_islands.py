#!/usr/bin/env python3
# https://leetcode.com/problems/number-of-islands/

import unittest
from typing import List


class Solution:
    __DELTAS = [[0, 1], [1, 0], [0, -1], [-1, 0]]

    def __land(self, grid, x, y):
        if not 0 <= x < len(grid):
            return False
        if not 0 <= y < len(grid[x]):
            return False
        return grid[x][y] == '1'

    def __traverse(self, grid, x, y):
        items = []
        items.append((x, y))
        land = False
        while items:
            x, y = items.pop(0)
            check = self.__land(grid, x, y)
            if check:
                land = True
                grid[x][y] = '0'
                for delta in self.__DELTAS:
                    xx = x + delta[0]
                    yy = y + delta[1]
                    items.append((xx, yy))
        return land

    def numIslands(self, grid: List[List[str]]) -> int:
        if grid is None or len(grid) == 0:
            return 0
        count = 0
        for x, grid_x in enumerate(grid):
            for y, _ in enumerate(grid_x):
                if self.__traverse(grid, x, y):
                    count += 1
        return count


class TestCode(unittest.TestCase):
    def test_example(self):
        grid = [['1']]
        self.assertEqual(1, Solution().numIslands(grid))

    def test_other(self):
        grid = [
            ['1', '1', '0', '0', '0'],
            ['1', '1', '0', '0', '0'],
            ['0', '0', '1', '0', '0'],
            ['0', '0', '0', '1', '1'],
        ]
        self.assertEqual(3, Solution().numIslands(grid))

    def test_nothing(self):
        self.assertEqual(0, Solution().numIslands([]))
        self.assertEqual(0, Solution().numIslands([[]]))
