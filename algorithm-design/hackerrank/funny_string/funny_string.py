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
        io_lines = [[[]]] * 2
        for index, template in enumerate(['input%s.txt', 'output%s.txt']):
            path = template % name
            path = os.path.join(os.path.split(__file__)[0], path)
            with open(path, 'r') as handle:
                lines = handle.readlines()
            lines = [line.strip() for line in lines]
            lines = [line.split(' ') for line in lines]
            io_lines[index] = lines
        count = int(io_lines[0][0][0])
        for i in range(count):
            s = io_lines[0][1 + i][0]
            result = funny_string(s)
            expected = ' '.join(io_lines[1][i])
            self.assertEqual(expected, result)

    def test_example(self):
        self.runner('_example')
