#!/usr/bin/env python3
# https://www.hackerrank.com/challenges/manasa-and-stones

import os
import unittest

from typing import List


def stones(n: int, a: int, b: int) -> List[int]:
    result = []
    if a > b:
        a, b = (b, a)
    current = a * (n - 1)
    delta = b - a
    result.append(current)
    if a != b:
        for _ in range(n - 1):
            current += delta
            result.append(current)
    return result


class TestCode(unittest.TestCase):
    def runner(self, name):
        path = os.path.join(os.path.split(__file__)[0], f'input{name}.txt')
        with open(path, 'r') as handle:
            lines = handle.readlines()
            lines = [line.strip() for line in lines]
        count = int(lines[0])
        results = [None] * count
        for i in range(count):
            n = int(lines[1 + i * 3])
            a = int(lines[2 + i * 3])
            b = int(lines[3 + i * 3])
            results[i] = stones(n, a, b)
        path = os.path.join(os.path.split(__file__)[0], f'output{name}.txt')
        with open(path, 'r') as handle:
            lines = handle.readlines()
        self.assertEqual(len(lines), len(results))
        lines = [line.strip() for line in lines]
        expected = [[int(item) for item in line.split(' ')] for line in lines]
        for index, array in enumerate(expected):
            self.assertEqual(array, results[index])

    def test_example(self):
        self.runner('_example')

    def test_03(self):
        self.runner('03')
