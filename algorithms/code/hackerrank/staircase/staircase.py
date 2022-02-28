#!/usr/bin/env python3
# https://www.hackerrank.com/challenges/staircase

import unittest


def staircase(n):
    result = []
    for index in range(n):
        line = ''
        for i in range(n):
            line += ' ' if i < n - 1 - index else '#'
        result.append(line)
    return result


class TestCode(unittest.TestCase):
    def test_example(self):
        expected = ['     #', '    ##', '   ###', '  ####', ' #####', '######']
        self.assertEqual(expected, staircase(6))
