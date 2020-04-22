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
        path = os.path.join(os.path.split(__file__)[0], f'input{name}.txt')
        with open(path, 'r') as handle:
            lines = handle.readlines()
            lines = [line.strip() for line in lines]
        var_p = int(lines[0])
        var_q = int(lines[1])
        result = kaprekar_numbers(var_p, var_q)
        path = os.path.join(os.path.split(__file__)[0], f'output{name}.txt')
        with open(path, 'r') as handle:
            lines = handle.readlines()
        expected = lines[0]
        result = ' '.join(result)
        self.assertEqual(expected, result)

    def test_example(self):
        self.runner('_example')

    def test_06(self):
        self.runner('06')

    def test_6(self):
        self.runner('6')
