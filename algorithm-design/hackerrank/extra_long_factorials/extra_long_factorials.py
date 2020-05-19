#!/usr/bin/env python3
# https://www.hackerrank.com/challenges/extra-long-factorials

import os
import unittest


# pylint: disable=R0913
def extra_long_factorials(n: int) -> str:
    result = 1
    for i in range(2, n + 1):
        result *= i
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
        n = int(io_lines[0][0][0])
        result = extra_long_factorials(n)
        expected = int(io_lines[1][0][0])
        self.assertEqual(expected, result)

    def test_example(self):
        self.runner('_example')
