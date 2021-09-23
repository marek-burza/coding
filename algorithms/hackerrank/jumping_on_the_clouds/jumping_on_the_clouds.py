#!/usr/bin/env python3
# https://www.hackerrank.com/challenges/jumping-on-the-clouds

import os
import unittest

from typing import List


def jumping_on_clouds(c: List[int]) -> int:
    n = len(c)
    count = 0
    i = 0
    while i < n - 1:
        if i + 2 > n - 1:
            count += 1
            break
        count += 1 + c[i + 2]
        i += 2 + c[i + 2]
    return count


class TestCode(unittest.TestCase):
    def runner(self, name):
        io_lines = [[[]]] * 2
        for index, template in enumerate(['input%s.txt', 'output%s.txt']):
            path = os.path.join(os.path.split(__file__)[0], template % name)
            with open(path, 'r') as handle:
                lines = handle.readlines()
            io_lines[index] = [line.strip().split(' ') for line in lines]
        c = [int(item) for item in io_lines[0][1]]
        result = jumping_on_clouds(c)
        self.assertEqual(int(io_lines[1][0][0]), result)

    def test_example_0(self):
        self.runner('_example_0')

    def test_example_1(self):
        self.runner('_example_1')

    def test_missing_example(self):
        self.assertEqual(1, jumping_on_clouds([0, 0]))
