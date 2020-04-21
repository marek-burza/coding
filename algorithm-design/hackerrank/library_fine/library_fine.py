#!/usr/bin/env python3
# https://www.hackerrank.com/challenges/library-fine

import os
import unittest


# pylint: disable=R0913
def library_fine(d1: int, m1: int, y1: int, d2: int, m2: int, y2: int) -> int:
    if y1 > y2:
        return 10000
    if y1 == y2:
        if m1 > m2:
            return (m1 - m2) * 500
        if m1 == m2 and d1 > d2:
            return (d1 - d2) * 15
    return 0


class TestCode(unittest.TestCase):
    def runner(self, name):
        path = os.path.join(os.path.split(__file__)[0], f'input{name}.txt')
        with open(path, 'r') as handle:
            lines = handle.readlines()
            lines = [line.strip() for line in lines]
        d1, m1, y1 = [int(item) for item in lines[0].split(' ')]
        d2, m2, y2 = [int(item) for item in lines[1].split(' ')]
        result = library_fine(d1, m1, y1, d2, m2, y2)
        path = os.path.join(os.path.split(__file__)[0], f'output{name}.txt')
        with open(path, 'r') as handle:
            lines = handle.readlines()
        expected = int(lines[0].strip())
        self.assertEqual(expected, result)

    def test_example(self):
        self.runner('_example')

    def test_00(self):
        self.runner('00')

    def test_01(self):
        self.runner('01')

    def test_02(self):
        self.runner('02')

    def test_03(self):
        self.runner('03')

    def test_14(self):
        self.runner('14')

    def test_15(self):
        self.runner('15')

    def test_16(self):
        self.runner('16')
