#!/usr/bin/env python3
# https://www.hackerrank.com/challenges/plus-minus

import unittest


def plus_minus(arr):
    n = len(arr)
    counts = [0] * 3
    for value in arr:
        index = 0 if value > 0 else 1 if value < 0 else 2
        counts[index] += 1
    return [v / n for v in counts]


class TestCode(unittest.TestCase):
    def test_example(self):
        self.assertEqual([1.0 / 2.0, 1.0 / 3.0, 1.0 / 6.0], plus_minus([-4, 3, -9, 0, 4, 1]))
