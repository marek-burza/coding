#!/usr/bin/env python3
# https://www.hackerrank.com/challenges/sherlock-and-array

import os
import unittest

from typing import List


def balanced_sums(arr: List[int]) -> str:
    left = [0] * len(arr)
    right = [0] * len(arr)
    for i in range(1, len(arr)):
        left[i] = left[i - 1] + arr[i - 1]
        right[len(arr) - 1 - i] = right[len(arr) - i] + arr[len(arr) - i]
    for i in range(len(arr)):
        if left[i] == right[i]:
            return 'YES'
    return 'NO'


class TestCode(unittest.TestCase):
    def runner(self, name):
        path = os.path.join(os.path.split(__file__)[0], f'input{name}.txt')
        with open(path, 'r') as handle:
            lines = handle.readlines()
            lines = [line.strip() for line in lines]
        count = int(lines[0])
        results = [0] * count
        for i in range(count):
            arguments = lines[2 + i * 2].split(' ')
            arguments = [int(item) for item in arguments]
            results[i] = balanced_sums(arguments)
        path = os.path.join(os.path.split(__file__)[0], f'output{name}.txt')
        with open(path, 'r') as handle:
            lines = handle.readlines()
            self.assertEqual(len(lines), len(results))
        for index, line in enumerate(lines):
            self.assertEqual(line.strip(), results[index])

    def test_example(self):
        self.runner('_example')

    def test_00(self):
        self.runner('00')
