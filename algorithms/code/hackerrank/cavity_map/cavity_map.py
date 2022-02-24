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
            path = os.path.join(os.path.split(__file__)[0], template % name)
            with open(path, 'r') as handle:
                lines = handle.readlines()
            io_lines[index] = [line.strip().split(' ') for line in lines]
        grid = [item[0] for item in io_lines[0][1:]]
        result = cavity_map(grid)
        expected = [item[0] for item in io_lines[1]]
        self.assertEqual(expected, result)

    def test_example(self):
        self.runner('_example')
