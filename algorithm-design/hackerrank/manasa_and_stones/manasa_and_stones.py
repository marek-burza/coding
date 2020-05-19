#!/usr/bin/env python3
# https://www.hackerrank.com/challenges/manasa-and-stones

import os
import unittest

from typing import List


def stones(n: int, a: int, b: int) -> List[int]:
    result = []
    if a > b:
        a, b = (b, a)
    current = a * (n - 1)
    delta = b - a
    result.append(current)
    if a != b:
        for _ in range(n - 1):
            current += delta
            result.append(current)
    return result


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
        count = int(io_lines[0][0][0])
        for i in range(count):
            n = int(io_lines[0][1 + i * 3][0])
            a = int(io_lines[0][2 + i * 3][0])
            b = int(io_lines[0][3 + i * 3][0])
            result = stones(n, a, b)
            expected = [int(item) for item in io_lines[1][i]]
            self.assertEqual(expected, result)

    def test_example(self):
        self.runner('_example')

    def test_03(self):
        self.runner('03')
