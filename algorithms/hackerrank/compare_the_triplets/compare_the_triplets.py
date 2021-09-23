#!/usr/bin/env python3
# https://www.hackerrank.com/challenges/compare-the-triplets

import unittest


def compare_triplets(a, b):
    result = [0, 0]
    for i in range(3):
        result[0] += 1 if a[i] > b[i] else 0
        result[1] += 1 if a[i] < b[i] else 0
    return result


class TestCode(unittest.TestCase):
    def test_example(self):
        self.assertEqual([1, 1], compare_triplets([5, 6, 7], [3, 6, 10]))
