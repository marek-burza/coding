#!/usr/bin/env python3
# https://www.hackerrank.com/challenges/icecream-parlor

import os
import unittest

from typing import List


def icecream_parlor(m: int, arr: List[int]) -> List[int]:
    n = len(arr)
    mapped = {}
    for i in range(0, n):
        mapped[arr[i]] = i
    for i in range(0, n):
        first = i
        value = m - arr[i]
        if value in mapped:
            second = mapped[value]
            if first == second:
                continue
            return [first + 1, second + 1]
    return [0, 0]


class TestCode(unittest.TestCase):
    def runner(self, name):
        path = os.path.join(os.path.split(__file__)[0], f'input{name}.txt')
        with open(path, 'r') as handle:
            lines = handle.readlines()
            lines = [line.strip() for line in lines]
        count = int(lines[0])
        results = [None] * count
        for i in range(count):
            m = int(lines[1 + i * 3])
            arr = [int(item) for item in lines[3 + i * 3].split(' ')]
            results[i] = icecream_parlor(m, arr)
        path = os.path.join(os.path.split(__file__)[0], f'output{name}.txt')
        with open(path, 'r') as handle:
            lines = handle.readlines()
        expected = [[int(item) for item in line.split(' ')] for line in lines]
        self.assertEqual(expected, results)

    def test_example(self):
        self.runner('_example')

    def test_02(self):
        self.runner('02')

    def test_same(self):
        expected = [3, 4]
        result = icecream_parlor(6, [3, 1, 2, 4])
        self.assertEqual(expected, result)

    def test_none(self):
        expected = [0, 0]
        result = icecream_parlor(10, [3, 1, 2, 4])
        self.assertEqual(expected, result)
