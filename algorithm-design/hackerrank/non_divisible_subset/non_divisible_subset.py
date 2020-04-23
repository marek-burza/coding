#!/usr/bin/env python3
# https://www.hackerrank.com/challenges/non-divisible-subset

import os
import unittest

from typing import List


def non_divisible_subset(k: int, s: List[int]) -> int:
    counted = {}
    for value in s:
        rest = value % k
        count = counted.get(rest)
        if count is None:
            counted[rest] = 1
        else:
            counted[rest] += 1
    ok = set()
    for a in counted:
        b = k - a
        if a in [0, b]:
            continue
        if b not in counted:
            ok.add(a)
        else:
            countA = counted[a]
            countB = counted[b]
            ok.add(a if countA > countB else b)
    total = 0
    for a in ok:
        total += counted[a]
    if 0 in counted:
        total += 1
    if (k & 1) == 0 and (k >> 1) in counted:
        total += 1
    return total


class TestCode(unittest.TestCase):
    def runner(self, name):
        io_lines = [[[]]] * 2
        for index, template in enumerate(['input%s.txt', 'output%s.txt']):
            path = template % name
            path = os.path.join(os.path.split(__file__)[0], path)
            with open(path, 'r') as handle:
                lines = handle.readlines()
            lines = [line.strip() for line in lines]
            lines = [line.split(' ') for line in lines]
            io_lines[index] = lines
        k = int(io_lines[0][0][1])
        s = [int(item) for item in io_lines[0][1]]
        result = non_divisible_subset(k, s)
        expected = int(io_lines[1][0][0])
        self.assertEqual(expected, result)

    def test_example(self):
        self.runner('_example')

    def test_06(self):
        self.runner('06')

    def test_07(self):
        self.runner('07')

    def test_16(self):
        self.runner('16')
