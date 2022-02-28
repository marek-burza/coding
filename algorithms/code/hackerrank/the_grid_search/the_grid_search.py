#!/usr/bin/env python3
# https://www.hackerrank.com/challenges/the-grid-search

import os
import unittest
from typing import List


def grid_search(G: List[str], P: List[str]) -> str:
    everything = ''.join(G)
    at = -1
    while True:
        at = everything.find(P[0], at + 1)
        if at == -1:
            return 'NO'
        if (at % len(G[0])) + len(P[0]) > len(G[0]):
            continue
        offset = at
        ok = True
        for chunk in P:
            if everything[offset : offset + len(chunk)] != chunk:
                ok = False
                break
            offset += len(G[0])
        if ok:
            return 'YES'


class TestCode(unittest.TestCase):
    # pylint: disable=R0914
    def runner(self, name):
        io_lines = [[[]]] * 2
        for index, template in enumerate(['input%s.txt', 'output%s.txt']):
            path = os.path.join(os.path.split(__file__)[0], template % name)
            with open(path, 'r') as handle:
                lines = handle.readlines()
            io_lines[index] = [line.strip().split(' ') for line in lines]
        tests = int(io_lines[0][0][0])
        offset = 1
        for test in range(tests):
            r_g = int(io_lines[0][offset][0])
            g = io_lines[0][offset + 1 : offset + 1 + r_g]
            g = [row[0] for row in g]
            r_p = int(io_lines[0][offset + 1 + r_g][0])
            p = io_lines[0][offset + 1 + r_g + 1 : offset + 1 + r_g + 1 + r_p]
            p = [row[0] for row in p]
            offset += r_g + r_p + 2
            result = grid_search(g, p)
            expected = io_lines[1][test][0]
            self.assertEqual(expected, result)

    def test_example(self):
        self.runner('_example')

    def test_05(self):
        self.runner('05')

    def test_07(self):
        self.runner('07')

    def test_08(self):
        self.runner('08')

    def test_09(self):
        self.runner('09')

    def test_15(self):
        self.runner('15')
