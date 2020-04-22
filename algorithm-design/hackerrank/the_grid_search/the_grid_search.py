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
            if everything[offset:offset + len(chunk)] != chunk:
                ok = False
                break
            offset += len(G[0])
        if ok:
            return 'YES'


class TestCode(unittest.TestCase):
    def runner(self, name):
        path = os.path.join(os.path.split(__file__)[0], f'input{name}.txt')
        with open(path, 'r') as handle:
            lines = handle.readlines()
            lines = [line.strip() for line in lines]
        tests = int(lines[0])
        results = [None] * tests
        offset = 1
        for test in range(tests):
            r_g = int(lines[offset].split(' ')[0])
            g = lines[offset + 1:offset + 1 + r_g]
            r_p = int(lines[offset + 1 + r_g].split(' ')[0])
            p = lines[offset + 1 + r_g + 1:offset + 1 + r_g + 1 + r_p]
            offset += r_g + r_p + 2
            results[test] = grid_search(g, p)
        path = os.path.join(os.path.split(__file__)[0], f'output{name}.txt')
        with open(path, 'r') as handle:
            lines = handle.readlines()
            expected = [line.strip() for line in lines]
        self.assertEqual(expected, results)

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
