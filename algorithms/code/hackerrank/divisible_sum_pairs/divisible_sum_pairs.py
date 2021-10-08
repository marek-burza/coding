#!/usr/bin/env python3
# https://www.hackerrank.com/challenges/divisible-sum-pairs

import os
import unittest

from typing import List


def n_choose_k(N: int, K: int) -> int:
    result = 1
    for k in range(K):
        result = result * (N - k) / (k + 1)
    return result


# pylint: disable=W0613
def divisible_sum_pairs(n: int, k: int, arr: List[int]) -> int:
    counted = {}
    for value in arr:
        rest = value % k
        count = counted.get(rest)
        if count is None:
            counted[rest] = 1
        else:
            counted[rest] = count + 1
    total = 0
    covered = set()
    for a in counted:
        if a in covered:
            continue
        if a == 0:
            total += n_choose_k(counted[a], 2)
            covered.add(a)
        else:
            b = k - a
            if b == a:
                total += n_choose_k(counted[a], 2)
                covered.add(a)
            else:
                if b in counted:
                    total += counted[a] * counted[b]
                    covered.add(a)
                    covered.add(b)
    return total


class TestCode(unittest.TestCase):
    def runner(self, name):
        io_lines = [[[]]] * 2
        for index, template in enumerate(['input%s.txt', 'output%s.txt']):
            path = os.path.join(os.path.split(__file__)[0], template % name)
            with open(path, 'r') as handle:
                lines = handle.readlines()
            io_lines[index] = [line.strip().split(' ') for line in lines]
        n = int(io_lines[0][0][0])
        k = int(io_lines[0][0][1])
        arr = [int(item) for item in io_lines[0][1]]
        result = divisible_sum_pairs(n, k, arr)
        expected = int(io_lines[1][0][0])
        self.assertEqual(expected, result)

    def test_example(self):
        self.runner('_example')

    def test_02(self):
        self.runner('02')

    def test_06(self):
        self.runner('06')
