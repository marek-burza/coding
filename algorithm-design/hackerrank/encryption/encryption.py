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
        path = os.path.join(os.path.split(__file__)[0], f'input{name}.txt')
        with open(path, 'r') as handle:
            lines = handle.readlines()
            lines = [line.strip() for line in lines]
        plain = lines[0]
        result = encryption(plain)
        path = os.path.join(os.path.split(__file__)[0], f'output{name}.txt')
        with open(path, 'r') as handle:
            lines = handle.readlines()
            lines = [line.strip() for line in lines]
        expected = lines[0]
        self.assertEqual(expected, result)

    def test_example_0(self):
        self.runner('_example_0')

    def test_example_1(self):
        self.runner('_example_1')

    def test_example_2(self):
        self.runner('_example_2')

    def test_example_3(self):
        self.runner('_example_3')
