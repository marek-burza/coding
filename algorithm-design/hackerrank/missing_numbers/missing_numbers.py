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
        path = os.path.join(os.path.split(__file__)[0], f'input{name}.txt')
        with open(path, 'r') as handle:
            lines = handle.readlines()
            lines = [line.strip() for line in lines]
        arr = [int(item) for item in lines[1].split(' ')]
        brr = [int(item) for item in lines[3].split(' ')]
        result = missing_numbers(arr, brr)
        path = os.path.join(os.path.split(__file__)[0], f'output{name}.txt')
        with open(path, 'r') as handle:
            lines = handle.readlines()
            lines = [line.strip() for line in lines]
        expected = [int(item) for item in lines[0].split(' ')]
        self.assertEqual(expected, result)

    def test_example(self):
        self.runner('_example')

    def test_01(self):
        self.runner('01')

    def test_03(self):
        self.runner('03')
