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
        path = os.path.join(os.path.split(__file__)[0], f'input{name}.txt')
        with open(path, 'r') as handle:
            lines = handle.readlines()
            lines = [line.strip() for line in lines]
        n = int(lines[0])
        result = extra_long_factorials(n)
        path = os.path.join(os.path.split(__file__)[0], f'output{name}.txt')
        with open(path, 'r') as handle:
            lines = handle.readlines()
        expected = int(lines[0].strip())
        self.assertEqual(expected, result)

    def test_example(self):
        self.runner('_example')
