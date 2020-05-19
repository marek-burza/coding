#!/usr/bin/env python3
# https://www.hackerrank.com/challenges/maximum-perimeter-triangle

import os
import unittest

from typing import List


def maximum_perimeter_triangle(sticks: List[int]) -> List[int]:
    sticks = sorted(sticks, reverse=True)
    for i in range(2, len(sticks)):
        for j in range(0, i - 1):
            for k in range(j + 1, i):
                a = sticks[i]
                b = sticks[k]
                c = sticks[j]
                if not (a + b <= c or a + c <= b):
                    return [a, b, c]
    return [-1]


class TestCode(unittest.TestCase):
    def runner(self, name):
        io_lines = [[[]]] * 2
        for index, template in enumerate(['input%s.txt', 'output%s.txt']):
            path = os.path.join(os.path.split(__file__)[0], template % name)
            with open(path, 'r') as handle:
                lines = handle.readlines()
            io_lines[index] = [line.strip().split(' ') for line in lines]
        sticks = [int(item) for item in io_lines[0][1]]
        result = maximum_perimeter_triangle(sticks)
        expected = [int(item) for item in io_lines[1][0]]
        self.assertEqual(expected, result)

    def test_example(self):
        self.runner('_example')

    def test_02(self):
        self.runner('02')

    def test_degenerate(self):
        expected = [-1]
        result = maximum_perimeter_triangle([])
        self.assertEqual(expected, result)

    def test_ACB(self):
        expected = [-1]
        result = maximum_perimeter_triangle([0, 1, 1])
        self.assertEqual(expected, result)
