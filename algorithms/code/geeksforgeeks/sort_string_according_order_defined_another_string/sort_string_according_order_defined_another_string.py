#!/usr/bin/env python3
# https://www.geeksforgeeks.org/sort-string-according-order-defined-another-string/
# #google

import unittest


class Solution:
    def sortByPattern(self, string, pat):
        # Build a decorating hash table
        lut = {}
        for i, key in enumerate(pat):
            lut[key] = i
        # Reorder
        chars = sorted(string, key=lambda character: lut.get(character, -1))
        return ''.join(chars)


class TestCode(unittest.TestCase):
    def test_example_1(self):
        self.assertEqual('bca', Solution().sortByPattern('abc', 'bca'))

    def test_example_2(self):
        self.assertEqual(
            'bbbcccaaa', Solution().sortByPattern('abcabcabc', 'bxyzca')
        )

    def test_example_3(self):
        self.assertEqual(
            'codijak',
            Solution().sortByPattern('jcdokai', 'wcyuogmlrdfphitxjakqvzbnes'),
        )
