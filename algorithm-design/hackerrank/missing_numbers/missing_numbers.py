#!/usr/bin/env python3
# https://www.hackerrank.com/challenges/missing-numbers

import os
import unittest

from typing import List


def missing_numbers(arr: List[int], brr: List[int]) -> List[int]:
    missing = set()
    arr = sorted(arr)
    brr = sorted(brr)
    n = len(arr)
    m = len(brr)
    i = 0
    for j in range(m):
        if i < n:
            if arr[i] == brr[j]:
                i += 1
            else:
                missing.add(brr[j])
        else:
            missing.add(brr[j])
    return sorted(list(missing))


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
        arr = [int(item) for item in io_lines[0][1]]
        brr = [int(item) for item in io_lines[0][3]]
        result = missing_numbers(arr, brr)
        expected = [int(item) for item in io_lines[1][0]]
        self.assertEqual(expected, result)

    def test_example(self):
        self.runner('_example')

    def test_01(self):
        self.runner('01')

    def test_03(self):
        self.runner('03')
