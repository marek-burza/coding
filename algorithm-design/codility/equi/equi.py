#!/usr/bin/env python3
# http://blog.codility.com/2011/03/solutions-for-task-equi.html

import unittest


def find_aquilibrium_index(array):
    n = len(array)
    if n == 0:
        return -1
    after = n * [0]
    after[n - 1] = 0
    for i in range(1, n):
        after[n - 1 - i] = after[n - i] + array[n - i]
    summed = 0
    for i in range(0, n):
        if summed == after[i]:
            return i
        summed += array[i]
    return -1


class TestCode(unittest.TestCase):
    def test_example(self):
        array = [-1, 3, -4, 5, 1, -6, 2, 1]
        result = find_aquilibrium_index(array)
        self.assertEqual(result, 1)

    def test_empty(self):
        array = []
        result = find_aquilibrium_index(array)
        self.assertEqual(result, -1)

    def test_invalid(self):
        array = [0, 1, 2, 3, 4]
        result = find_aquilibrium_index(array)
        self.assertEqual(result, -1)
