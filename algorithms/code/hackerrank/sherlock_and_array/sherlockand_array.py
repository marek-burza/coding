#!/usr/bin/env python3
# https://www.hackerrank.com/challenges/sherlock-and-array

import os
import unittest
from typing import List


def balanced_sums(arr: List[int]) -> str:
    left = [0] * len(arr)
    right = [0] * len(arr)
    for i in range(1, len(arr)):
        left[i] = left[i - 1] + arr[i - 1]
        right[len(arr) - 1 - i] = right[len(arr) - i] + arr[len(arr) - i]
    for i in range(len(arr)):
        if left[i] == right[i]:
            return 'YES'
    return 'NO'


class TestCode(unittest.TestCase):
    def runner(self, name):
        io_lines = [[[]]] * 2
        for index, template in enumerate(['input%s.txt', 'output%s.txt']):
            path = os.path.join(os.path.split(__file__)[0], template % name)
            with open(path, 'r') as handle:
                lines = handle.readlines()
            io_lines[index] = [line.strip().split(' ') for line in lines]
        count = int(io_lines[0][0][0])
        for i in range(count):
            arguments = io_lines[0][2 + i * 2]
            arguments = [int(item) for item in arguments]
            result = balanced_sums(arguments)
            self.assertEqual(io_lines[1][i][0], result)

    def test_example(self):
        self.runner('_example')

    def test_00(self):
        self.runner('00')
