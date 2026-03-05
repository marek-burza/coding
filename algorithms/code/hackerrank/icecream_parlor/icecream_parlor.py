# https://www.hackerrank.com/challenges/icecream-parlor

import os
import unittest


def icecream_parlor(m: int, arr: list[int]) -> list[int]:
    n = len(arr)
    mapped: dict[int, int] = {}
    for i in range(0, n):
        mapped[arr[i]] = i
    for i in range(0, n):
        first = i
        value = m - arr[i]
        if value in mapped:
            second = mapped[value]
            if first == second:
                continue
            return [first + 1, second + 1]
    return [0, 0]


class TestCode(unittest.TestCase):
    def runner(self, name: str) -> None:
        io_lines: list[list[list[str]]] = [[[]]] * 2
        for index, template in enumerate(['input%s.txt', 'output%s.txt']):
            path = os.path.join(os.path.split(__file__)[0], template % name)
            with open(path, 'r', encoding='utf-8') as handle:
                lines = handle.readlines()
            io_lines[index] = [line.strip().split(' ') for line in lines]
        count = int(io_lines[0][0][0])
        for i in range(count):
            m = int(io_lines[0][1 + i * 3][0])
            arr = [int(item) for item in io_lines[0][3 + i * 3]]
            result = icecream_parlor(m, arr)
            expected = [int(item) for item in io_lines[1][i]]
            assert expected == result

    def test_example(self) -> None:
        self.runner('_example')

    def test_02(self) -> None:
        self.runner('02')

    def test_same(self) -> None:
        expected = [3, 4]
        result = icecream_parlor(6, [3, 1, 2, 4])
        assert expected == result

    def test_none(self) -> None:
        expected = [0, 0]
        result = icecream_parlor(10, [3, 1, 2, 4])
        assert expected == result
