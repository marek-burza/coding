#!/usr/bin/env python3
# https://www.hackerrank.com/challenges/pairs

import os
import unittest

from typing import List


def binary_search(array, from_index, to_index, key):
    while from_index <= to_index:
        middle = (from_index + to_index) // 2
        if array[middle] < key:
            from_index = middle + 1
        elif array[middle] > key:
            to_index = middle - 1
        else:
            return middle
    return -1


def pairs(k: int, arr: List[int]) -> int:
    arr = sorted(arr)
    count = 0
    for i in range(0, len(arr) - 1):
        if binary_search(arr, i + 1, len(arr) - 1, arr[i] + k) >= 0:
            count += 1
    return count


class TestCode(unittest.TestCase):
    def runner(self, name):
        io_lines = [[[]]] * 2
        for index, template in enumerate(['input%s.txt', 'output%s.txt']):
            path = os.path.join(os.path.split(__file__)[0], template % name)
            with open(path, 'r') as handle:
                lines = handle.readlines()
            io_lines[index] = [line.strip().split(' ') for line in lines]
        k = int(io_lines[0][0][1])
        arr = [int(item) for item in io_lines[0][1]]
        result = pairs(k, arr)
        expected = int(io_lines[1][0][0])
        self.assertEqual(expected, result)

    def test_example(self):
        self.runner('_example')

    def test_other(self):
        expected = 4
        result = pairs(1, [1, 5, 3, 4, 2])
        self.assertEqual(expected, result)
