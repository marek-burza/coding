#!/usr/bin/env python3
# https://www.hackerrank.com/challenges/kaprekar-numbers

import math
import os
import unittest

from typing import List


def kaprekar_numbers(p: int, q: int) -> List[str]:
    found = []
    for n in range(p, q + 1):
        digits_count = 1 + int(math.log10(n))
        nn = n * n
        splitter = int(math.pow(10, digits_count))
        rv = nn // splitter
        lv = nn % splitter
        if n == rv + lv:
            found.append(str(n))
    if not found:
        return ['INVALID', 'RANGE']
    return found


class TestCode(unittest.TestCase):
    def runner(self, name):
        io_lines = [[[]]] * 2
        for index, template in enumerate(['input%s.txt', 'output%s.txt']):
            path = os.path.join(os.path.split(__file__)[0], template % name)
            with open(path, 'r') as handle:
                lines = handle.readlines()
            io_lines[index] = [line.strip().split(' ') for line in lines]
        var_p = int(io_lines[0][0][0])
        var_q = int(io_lines[0][1][0])
        result = kaprekar_numbers(var_p, var_q)
        expected = io_lines[1][0]
        self.assertEqual(expected, result)

    def test_example(self):
        self.runner('_example')

    def test_06(self):
        self.runner('06')

    def test_6(self):
        self.runner('6')
