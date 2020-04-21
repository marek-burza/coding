#!/usr/bin/env python3
# https://www.hackerrank.com/challenges/funny-string

import os
import unittest


# pylint: disable=R0913
def funny_string(s: str) -> str:
    n = len(s)
    for i in range(1, n):
        forward = abs(ord(s[i]) - ord(s[i - 1]))
        backward = abs(ord(s[n - i - 1]) - ord(s[n - i]))
        if forward != backward:
            return 'Not Funny'
    return 'Funny'


class TestCode(unittest.TestCase):
    def runner(self, name):
        path = os.path.join(os.path.split(__file__)[0], f'input{name}.txt')
        with open(path, 'r') as handle:
            lines = handle.readlines()
            lines = [line.strip() for line in lines]
        count = int(lines[0])
        results = [None] * count
        for i in range(count):
            s = lines[1 + i]
            results[i] = funny_string(s)
        path = os.path.join(os.path.split(__file__)[0], f'output{name}.txt')
        with open(path, 'r') as handle:
            lines = handle.readlines()
        self.assertEqual(len(lines), len(results))
        for i in range(count):
            self.assertEqual(lines[i].strip(), results[i])

    def test_example(self):
        self.runner('_example')
