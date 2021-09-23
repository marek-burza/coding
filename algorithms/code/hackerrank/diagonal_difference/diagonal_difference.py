#!/usr/bin/env python3
# https://www.hackerrank.com/challenges/diagonal-difference

import unittest


def diagonal_difference(arr):
    result = 0
    n = len(arr)
    for i in range(n):
        result += arr[i][i] - arr[n - 1 - i][i]
    return abs(result)


class TestCode(unittest.TestCase):
    def test_example(self):
        arr = [
            [11, 2, 4],
            [4, 5, 6],
            [10, 8, -12]
        ]
        self.assertEqual(15, diagonal_difference(arr))
