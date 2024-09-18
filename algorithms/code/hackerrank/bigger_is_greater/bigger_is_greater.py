#!/usr/bin/env python3
# https://www.hackerrank.com/challenges/bigger-is-greater

import unittest


def bigger_is_greater(w):
    array = list(w)
    length = len(array)
    for i in range(length - 1, 0, -1):
        if array[i - 1] < array[i]:
            array[i:] = sorted(array[i:])
            for j in range(i, length):
                if array[i - 1] < array[j]:
                    array[i - 1], array[j] = array[j], array[i - 1]
                    array[i:] = sorted(array[i:])
                    return ''.join(array)
    return 'no answer'


class TestCode(unittest.TestCase):
    def test_ab(self):
        self.assertEqual('ba', bigger_is_greater('ab'))

    def test_bb(self):
        self.assertEqual('no answer', bigger_is_greater('bb'))

    def test_hefg(self):
        self.assertEqual('hegf', bigger_is_greater('hefg'))

    def test_dhck(self):
        self.assertEqual('dhkc', bigger_is_greater('dhck'))

    def test_dkhc(self):
        self.assertEqual('hcdk', bigger_is_greater('dkhc'))
