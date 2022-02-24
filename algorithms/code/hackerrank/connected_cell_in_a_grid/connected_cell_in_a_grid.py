#!/usr/bin/env python3
# https://www.hackerrank.com/challenges/connected-cell-in-a-grid

import collections
import os
import unittest
from typing import List

DELTAS = [
    (-1, -1), (0, -1), (1, -1), (-1, 0), (1, 0), (-1, 1), (0, 1), (1, 1)
]


def check(matrix: List[List[int]], r: int, c: int) -> bool:
    return 0 <= r < len(matrix) and 0 <= c < len(matrix[r])


def traverse(matrix: List[List[int]], r: int, c: int) -> int:
    size = 0
    queue: collections.deque = collections.deque()
    queue.append((r, c))
    while queue:
        r, c = queue.pop()
        if matrix[r][c] == 1:
            matrix[r][c] = -1
            size += 1
            for rd, cd in DELTAS:
                rn, cn = (r + rd, c + cd)
                if check(matrix, rn, cn):
                    queue.append((rn, cn))
    return size


def connected_cell(matrix: List[List[int]]) -> int:
    result = 0
    for r, _ in enumerate(matrix):
        for c, _ in enumerate(matrix[r]):
            result = max(traverse(matrix, r, c), result)
    return result


class TestCode(unittest.TestCase):
    def runner(self, name):
        io_lines = [[[]]] * 2
        for index, template in enumerate(['input%s.txt', 'output%s.txt']):
            path = os.path.join(os.path.split(__file__)[0], template % name)
            with open(path, 'r') as handle:
                lines = handle.readlines()
            io_lines[index] = [line.strip().split(' ') for line in lines]
        matrix = io_lines[0][2:]
        matrix = [[int(item) for item in row] for row in matrix]
        result = connected_cell(matrix)
        expected = int(io_lines[1][0][0])
        self.assertEqual(expected, result)

    def test_example(self):
        self.runner('_example')
