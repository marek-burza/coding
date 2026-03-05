# https://www.hackerrank.com/challenges/kangaroo

import os
import unittest


def kangaroo(x1: int, v1: int, x2: int, v2: int) -> str:
    if v1 == v2:
        return 'YES' if x1 == x2 else 'NO'
    check = (x2 - x1) % (v2 - v1) == 0 and (x2 - x1) / (v2 - v1) < 0
    return 'YES' if check else 'NO'


# pylint: disable=R0904
class TestCode(unittest.TestCase):
    def runner(self, name: str) -> None:
        io_lines: list[list[list[str]]] = [[[]]] * 2
        for index, template in enumerate(['input%s.txt', 'output%s.txt']):
            path = os.path.join(os.path.split(__file__)[0], template % name)
            with open(path, 'r', encoding='utf-8') as handle:
                lines = handle.readlines()
            io_lines[index] = [line.strip().split(' ') for line in lines]
        x1, v1, x2, v2 = [int(item) for item in io_lines[0][0]]
        result = kangaroo(x1, v1, x2, v2)
        expected = io_lines[1][0][0]
        self.assertEqual(expected, result)

    def test_example_0(self) -> None:
        self.runner('_example_0')

    def test_example_1(self) -> None:
        self.runner('_example_1')

    def test_same(self) -> None:
        self.assertEqual('YES', kangaroo(1, 2, 1, 2))

    def test_00(self) -> None:
        self.runner('00')

    def test_01(self) -> None:
        self.runner('01')

    def test_02(self) -> None:
        self.runner('02')

    def test_03(self) -> None:
        self.runner('03')

    def test_04(self) -> None:
        self.runner('04')

    def test_05(self) -> None:
        self.runner('05')

    def test_06(self) -> None:
        self.runner('06')

    def test_07(self) -> None:
        self.runner('07')

    def test_08(self) -> None:
        self.runner('08')

    def test_09(self) -> None:
        self.runner('09')

    def test_10(self) -> None:
        self.runner('10')

    def test_11(self) -> None:
        self.runner('11')

    def test_12(self) -> None:
        self.runner('12')

    def test_13(self) -> None:
        self.runner('13')

    def test_14(self) -> None:
        self.runner('14')

    def test_15(self) -> None:
        self.runner('15')

    def test_16(self) -> None:
        self.runner('16')

    def test_17(self) -> None:
        self.runner('17')

    def test_18(self) -> None:
        self.runner('18')

    def test_19(self) -> None:
        self.runner('19')

    def test_20(self) -> None:
        self.runner('20')

    def test_21(self) -> None:
        self.runner('21')

    def test_22(self) -> None:
        self.runner('22')

    def test_23(self) -> None:
        self.runner('23')

    def test_24(self) -> None:
        self.runner('24')

    def test_25(self) -> None:
        self.runner('25')

    def test_26(self) -> None:
        self.runner('26')

    def test_27(self) -> None:
        self.runner('27')

    def test_28(self) -> None:
        self.runner('28')

    def test_29(self) -> None:
        self.runner('29')
