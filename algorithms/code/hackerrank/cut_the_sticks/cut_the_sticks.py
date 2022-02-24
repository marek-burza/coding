#!/usr/bin/env python3
# https://www.hackerrank.com/challenges/cut-the-sticks

import os
import unittest
from typing import List


def cut_the_sticks(arr: List[int]) -> List[int]:
    arr = sorted(arr)
    cuts = []
    n = len(arr)
    i = 0
    while i < n:
        cuts.append(n - i)
        cut = arr[i]
        while i < n and arr[i] - cut <= 0:
            i += 1
    return cuts


class TestCode(unittest.TestCase):
    def runner(self, name):
        io_lines = [[[]]] * 2
        for index, template in enumerate(['input%s.txt', 'output%s.txt']):
            path = os.path.join(os.path.split(__file__)[0], template % name)
            with open(path, 'r') as handle:
                lines = handle.readlines()
            io_lines[index] = [line.strip().split(' ') for line in lines]
        arguments = [int(item) for item in io_lines[0][1]]
        result = cut_the_sticks(arguments)
        expected = [int(line[0]) for line in io_lines[1]]
        self.assertEqual(expected, result)

    def test_example_0(self):
        self.runner('_example_0')

    def test_example_1(self):
        self.runner('_example_1')
