#!/usr/bin/env python3
# https://www.hackerrank.com/challenges/cavity-map

import os
import unittest

from typing import List


def cavity_map(grid: List[str]) -> List[str]:
    deltas = [[-1, 0], [0, -1], [1, 0], [0, 1]]
    n = len(grid)
    cells = [list(line) for line in grid]
    for i in range(1, n - 1):
        for j in range(1, n - 1):
            deeper = True
            for delta in deltas:
                if cells[i + delta[0]][j + delta[1]] >= cells[i][j]:
                    deeper = False
            if deeper:
                cells[i][j] = 'X'
    return [''.join(line) for line in cells]


class TestCode(unittest.TestCase):
    def runner(self, name):
        io_lines = [[[]]] * 2
        for index, template in enumerate(['input%s.txt', 'output%s.txt']):
            path = template % name
            path = os.path.join(os.path.split(__file__)[0], path)
            with open(path, 'r') as handle:
                lines = handle.readlines()
            lines = [line.strip() for line in lines]
            io_lines[index] = lines
        grid = io_lines[0][1:]
        grid = cavity_map(grid)
        self.assertEqual(io_lines[1], grid)

    def test_example(self):
        self.runner('_example')
