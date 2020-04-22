#!/usr/bin/env python3
# https://www.hackerrank.com/challenges/count-luck

import collections
import os
import unittest

from typing import List


DELTAS = [(0, 1), (0, -1), (1, 0), (-1, 0)]


def look_around(forest: List[List[str]], at: tuple) -> List[tuple]:
    ways = []
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
    queue = collections.deque()
    counts = collections.deque()
    counts.append(0)
    forest = [list(row) for row in matrix]
    for r, _ in enumerate(forest):
        for c, _ in enumerate(forest[r]):
            if forest[r][c] == 'M':
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
    def runner(self, name):
        path = os.path.join(os.path.split(__file__)[0], f'input{name}.txt')
        with open(path, 'r') as handle:
            lines = handle.readlines()
            lines = [line.strip() for line in lines]
        tests = int(lines[0])
        results = [None] * tests
        offset = 1
        for test in range(tests):
            n = int(lines[offset].split(' ')[0])
            matrix = lines[offset + 1:offset + n + 1]
            k = int(lines[offset + 1 + n].split(' ')[0])
            offset += n+2
            results[test] = count_luck(matrix, k)
        path = os.path.join(os.path.split(__file__)[0], f'output{name}.txt')
        with open(path, 'r') as handle:
            lines = handle.readlines()
            expected = [line.strip() for line in lines]
        self.assertEqual(expected, results)

    def test_example(self):
        self.runner('_example')

    def test_empty(self):
        result = count_luck([], 0)
        expected = 'Oops!'
        self.assertEqual(expected, result)
