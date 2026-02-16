# https://www.hackerrank.com/challenges/library-fine

import os
import unittest


def library_fine(  # pylint: disable=R0913,R0917
    d1: int, m1: int, y1: int, d2: int, m2: int, y2: int
) -> int:
    if y1 > y2:
        return 10000
    if y1 == y2:
        if m1 > m2:
            return (m1 - m2) * 500
        if m1 == m2 and d1 > d2:
            return (d1 - d2) * 15
    return 0


class TestCode(unittest.TestCase):
    # pylint: disable=R0914
    def runner(self, name: str) -> None:
        io_lines: list[list[list[str]]] = [[[]]] * 2
        for index, template in enumerate(['input%s.txt', 'output%s.txt']):
            path = os.path.join(os.path.split(__file__)[0], template % name)
            with open(path, 'r', encoding='utf-8') as handle:
                lines = handle.readlines()
            io_lines[index] = [line.strip().split(' ') for line in lines]
        d1, m1, y1 = [int(item) for item in io_lines[0][0]]
        d2, m2, y2 = [int(item) for item in io_lines[0][1]]
        result = library_fine(d1, m1, y1, d2, m2, y2)
        expected = int(io_lines[1][0][0])
        assert expected == result

    def test_example(self) -> None:
        self.runner('_example')

    def test_00(self) -> None:
        self.runner('00')

    def test_01(self) -> None:
        self.runner('01')

    def test_02(self) -> None:
        self.runner('02')

    def test_03(self) -> None:
        self.runner('03')

    def test_07(self) -> None:
        self.runner('07')

    def test_09(self) -> None:
        self.runner('09')

    def test_14(self) -> None:
        self.runner('14')

    def test_15(self) -> None:
        self.runner('15')

    def test_16(self) -> None:
        self.runner('16')
