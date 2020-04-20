#!/usr/bin/env python3
# https://www.hackerrank.com/challenges/taum-and-bday

import os
import unittest


def taum_bday(b: int, w: int, bc: int, wc: int, z: int) -> int:
    x_adjusted = bc if bc <= wc + z else wc + z
    y_adjusted = wc if wc <= bc + z else bc + z
    return b * x_adjusted + w * y_adjusted


class TestCode(unittest.TestCase):
    def runner(self, name):
        path = os.path.join(os.path.split(__file__)[0], f'input{name}.txt')
        with open(path, 'r') as handle:
            lines = handle.readlines()
            lines = [line.strip() for line in lines]
        t = int(lines[0])
        results = [0] * t
        for index in range(t):
            b_w = lines[1 + index * 2].split(' ')
            bc_wc_z = lines[2 + index * 2].split(' ')
            arguments = [*b_w, *bc_wc_z]
            arguments = [int(item) for item in arguments]
            results[index] = taum_bday(*arguments)
        path = os.path.join(os.path.split(__file__)[0], f'output{name}.txt')
        with open(path, 'r') as handle:
            lines = handle.readlines()
            self.assertEqual(len(lines), len(results))
            for index, line in enumerate(lines):
                self.assertEqual(int(line), results[index])

    def test_example(self):
        self.runner('_example')

    def test_05(self):
        self.runner('05')
