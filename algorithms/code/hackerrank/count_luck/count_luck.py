#!/usr/bin/env python3
# https://www.hackerrank.com/challenges/count-luck

import collections
import os
import unittest
from typing import List

DELTAS = [(0, 1), (0, -1), (1, 0), (-1, 0)]


def look_around(forest: List[List[str]], at: tuple) -> List[tuple]:
    ways: List[tuple] = []
    for delta in DELTAS:
        dr = at[0] + delta[0]
        dc = at[1] + delta[1]
        if dr < 0 or len(forest) <= dr:
            continue
        if dc < 0 or len(forest[dr]) <= dc:
            continue
        if forest[dr][dc] == 'X':
            continue
        ways.append((dr, dc))
    return ways


def count_luck(matrix: List[str], k: int) -> str:
    queue: collections.deque = collections.deque()
    counts: collections.deque = collections.deque()
    counts.append(0)
    forest = [list(row) for row in matrix]
    for r, forest_r in enumerate(forest):
        for c, forest_r_c in enumerate(forest_r):
            if forest_r_c == 'M':
                queue.append((r, c))
    while queue:
        at = queue.pop()
        count = counts.pop()
        if forest[at[0]][at[1]] == '*':
            return 'Impressed' if count == k else 'Oops!'
        forest[at[0]][at[1]] = 'X'
        ways = look_around(forest, at)
        for way in ways:
            queue.append(way)
            counts.append(count + (1 if len(ways) > 1 else 0))
    return 'Oops!'


class TestCode(unittest.TestCase):
    # pylint: disable=R0914
    def runner(self, name):
        io_lines = [[[]]] * 2
        for index, template in enumerate(['input%s.txt', 'output%s.txt']):
            path = os.path.join(os.path.split(__file__)[0], template % name)
            with open(path, 'r', encoding='utf-8') as handle:
                lines = handle.readlines()
            io_lines[index] = [line.strip().split(' ') for line in lines]
        tests = int(io_lines[0][0][0])
        offset = 1
        for t in range(tests):
            n = int(io_lines[0][offset][0])
            matrix = io_lines[0][offset + 1 : offset + n + 1]
            matrix = [line[0] for line in matrix]
            k = int(io_lines[0][offset + 1 + n][0])
            offset += n + 2
            result = count_luck(matrix, k)
            expected = io_lines[1][t][0]
            self.assertEqual(expected, result)

    def test_example(self):
        self.runner('_example')

    def test_empty(self):
        result = count_luck([], 0)
        expected = 'Oops!'
        self.assertEqual(expected, result)
