#!/usr/bin/env python3
# https://www.hackerrank.com/challenges/extra-long-factorials

import os
import unittest


# pylint: disable=R0913
def extra_long_factorials(n: int) -> str:
    result = 1
    for i in range(2, n + 1):
        result *= i
    return str(result)


class TestCode(unittest.TestCase):
    def runner(self, name):
        io_lines = [[[]]] * 2
        for index, template in enumerate(['input%s.txt', 'output%s.txt']):
            path = os.path.join(os.path.split(__file__)[0], template % name)
            with open(path, 'r', encoding='utf-8') as handle:
                lines = handle.readlines()
            io_lines[index] = [line.strip().split(' ') for line in lines]
        n = int(io_lines[0][0][0])
        result = int(extra_long_factorials(n))
        expected = int(io_lines[1][0][0])
        self.assertEqual(expected, result)

    def test_example(self):
        self.runner('_example')
