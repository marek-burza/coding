#!/usr/bin/env python3
# https://www.hackerrank.com/challenges/cut-the-sticks

import os
import unittest

from typing import List


def cut_the_sticks(arr: List[int]) -> List[int]:
    arr = sorted(arr)
    cuts = []
    n = len(arr)
    i = 0
    while i < n:
        cuts.append(n - i)
        cut = arr[i]
        while i < n and arr[i] - cut <= 0:
            i += 1
    return cuts


class TestCode(unittest.TestCase):
    def runner(self, name):
        path = os.path.join(os.path.split(__file__)[0], f'input{name}.txt')
        with open(path, 'r') as handle:
            lines = handle.readlines()
            lines = [line.strip() for line in lines]
        arguments = lines[1].split(' ')
        arguments = [int(item) for item in arguments]
        result = cut_the_sticks(arguments)
        path = os.path.join(os.path.split(__file__)[0], f'output{name}.txt')
        with open(path, 'r') as handle:
            lines = handle.readlines()
            self.assertEqual(len(lines), len(result))
            for index, line in enumerate(lines):
                self.assertEqual(int(line), result[index])

    def test_example_0(self):
        self.runner('_example_0')

    def test_example_1(self):
        self.runner('_example_1')
