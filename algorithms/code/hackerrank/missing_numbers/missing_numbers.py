#!/usr/bin/env python3
# https://www.hackerrank.com/challenges/missing-numbers

import os
import unittest


def missing_numbers(arr: list[int], brr: list[int]) -> list[int]:
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
    def runner(self, name: str) -> None:
        io_lines: list[list[list[str]]] = [[[]]] * 2
        for index, template in enumerate(['input%s.txt', 'output%s.txt']):
            path = os.path.join(os.path.split(__file__)[0], template % name)
            with open(path, 'r', encoding='utf-8') as handle:
                lines = handle.readlines()
            io_lines[index] = [line.strip().split(' ') for line in lines]
        arr = [int(item) for item in io_lines[0][1]]
        brr = [int(item) for item in io_lines[0][3]]
        result = missing_numbers(arr, brr)
        expected = [int(item) for item in io_lines[1][0]]
        self.assertEqual(expected, result)

    def test_example(self) -> None:
        self.runner('_example')

    def test_01(self) -> None:
        self.runner('01')

    def test_03(self) -> None:
        self.runner('03')
