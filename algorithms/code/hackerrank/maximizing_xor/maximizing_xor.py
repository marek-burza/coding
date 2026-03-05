# https://www.hackerrank.com/challenges/maximizing-xor

import os
import unittest


def maximizing_xor(var_l: int, var_r: int) -> int:
    maxed = var_l ^ var_r
    maxed |= maxed >> 1
    maxed |= maxed >> 2
    maxed |= maxed >> 4
    maxed |= maxed >> 8
    return maxed


class TestCode(unittest.TestCase):
    def runner(self, name: str) -> None:
        io_lines: list[list[list[str]]] = [[[]]] * 2
        for index, template in enumerate(['input%s.txt', 'output%s.txt']):
            path = os.path.join(os.path.split(__file__)[0], template % name)
            with open(path, 'r', encoding='utf-8') as handle:
                lines = handle.readlines()
            io_lines[index] = [line.strip().split(' ') for line in lines]
        var_l = int(io_lines[0][0][0])
        var_r = int(io_lines[0][1][0])
        result = maximizing_xor(var_l, var_r)
        expected = int(io_lines[1][0][0])
        assert expected == result

    def test_00(self) -> None:
        self.runner('00')

    def test_01(self) -> None:
        self.runner('01')

    def test_02(self) -> None:
        self.runner('02')
