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
        io_lines = [[[]]] * 2
        for index, template in enumerate(['input%s.txt', 'output%s.txt']):
            path = template % name
            path = os.path.join(os.path.split(__file__)[0], path)
            with open(path, 'r') as handle:
                lines = handle.readlines()
            lines = [line.strip() for line in lines]
            lines = [line.split(' ') for line in lines]
            io_lines[index] = lines
        t = int(io_lines[0][0][0])
        for index in range(t):
            b_w = io_lines[0][1 + index * 2]
            bc_wc_z = io_lines[0][2 + index * 2]
            arguments = [*b_w, *bc_wc_z]
            arguments = [int(item) for item in arguments]
            result = taum_bday(*arguments)
            self.assertEqual(int(io_lines[1][index][0]), result)

    def test_example(self):
        self.runner('_example')

    def test_05(self):
        self.runner('05')
