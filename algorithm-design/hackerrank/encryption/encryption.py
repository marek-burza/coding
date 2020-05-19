#!/usr/bin/env python3
# https://www.hackerrank.com/challenges/encryption

import math
import os
import unittest


def encryption(plain: str) -> str:
    length = len(plain)
    floor = int(math.floor(math.sqrt(length)))
    ceil = int(math.ceil(math.sqrt(length)))
    rows = cols = -1
    for c in range(ceil, floor - 1, -1):
        r = (length // c) + (1 if (length % c) > 0 else 0)
        if r * c >= length:
            rows = r
            cols = c
            break
    result = ''
    for c in range(cols):
        if c != 0:
            result += ' '
        for r in range(rows):
            index = r * cols + c
            if index < length:
                result += plain[index]
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
        plain = io_lines[0][0][0]
        result = encryption(plain)
        expected = ' '.join(io_lines[1][0])
        self.assertEqual(expected, result)

    def test_example_0(self):
        self.runner('_example_0')

    def test_example_1(self):
        self.runner('_example_1')

    def test_example_2(self):
        self.runner('_example_2')

    def test_example_3(self):
        self.runner('_example_3')
