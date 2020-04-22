#!/usr/bin/env python3
# https://www.hackerrank.com/challenges/maximizing-xor

import os
import unittest


def maximizing_xor(l: int, r: int) -> int:
    maxed = l ^ r
    maxed |= maxed >> 1
    maxed |= maxed >> 2
    maxed |= maxed >> 4
    maxed |= maxed >> 8
    return maxed


class TestCode(unittest.TestCase):
    def runner(self, name):
        path = os.path.join(os.path.split(__file__)[0], f'input{name}.txt')
        with open(path, 'r') as handle:
            lines = handle.readlines()
            lines = [line.strip() for line in lines]
        var_l = int(lines[0])
        var_r = int(lines[1])
        result = maximizing_xor(var_l, var_r)
        path = os.path.join(os.path.split(__file__)[0], f'output{name}.txt')
        with open(path, 'r') as handle:
            lines = handle.readlines()
        expected = int(lines[0])
        self.assertEqual(expected, result)

    def test_00(self):
        self.runner('00')

    def test_01(self):
        self.runner('01')

    def test_02(self):
        self.runner('02')
