# https://www.hackerrank.com/challenges/the-time-in-words

import os
import unittest

LUT = [
    'zero',
    'one',
    'two',
    'three',
    'four',
    'five',
    'six',
    'seven',
    'eight',
    'nine',
    'ten',
    'eleven',
    'twelve',
    'thirteen',
    'fourteen',
    'fifteen',
    'sixteen',
    'seventeen',
    'eighteen',
    'nineteen',
    'twenty',
    'twenty one',
    'twenty two',
    'twenty three',
    'twenty four',
    'twenty five',
    'twenty six',
    'twenty seven',
    'twenty eight',
    'twenty nine',
    'thirty',
]


def time_in_words(h: int, m: int) -> str:
    result = ''
    if m != 0:
        if m == 30:
            result += 'half past '
        elif m == 15:
            result += 'quarter past '
        elif m == 45:
            result += 'quarter to '
            h = (h + 1) % 12
        elif m < 30:
            result += LUT[m]
            if m == 1:
                result += ' minute past '
            else:
                result += ' minutes past '
        elif m > 30:
            m = 60 - m
            result += LUT[m]
            if m == 1:
                result += ' minute to '
            else:
                result += ' minutes to '
            h = (h + 1) % 12
    result += LUT[h]
    if m == 0:
        result += " o' clock"
    return result


class TestCode(unittest.TestCase):
    def runner(self, name: str) -> None:
        io_lines: list[list[list[str]]] = [[[]]] * 2
        for index, template in enumerate(['input%s.txt', 'output%s.txt']):
            path = os.path.join(os.path.split(__file__)[0], template % name)
            with open(path, 'r', encoding='utf-8') as handle:
                lines = handle.readlines()
            io_lines[index] = [line.strip().split(' ') for line in lines]
        result = time_in_words(int(io_lines[0][0][0]), int(io_lines[0][1][0]))
        assert ' '.join(io_lines[1][0]) == result

    def test_example_0(self) -> None:
        self.runner('_example_0')

    def test_example_1(self) -> None:
        self.runner('_example_1')

    def test_example_2(self) -> None:
        self.runner('_example_2')

    def test_03_59(self) -> None:
        assert time_in_words(3, 59) == 'one minute to four'

    def test_03_01(self) -> None:
        assert time_in_words(3, 1) == 'one minute past three'

    def test_03_45(self) -> None:
        assert time_in_words(3, 45) == 'quarter to four'

    def test_03_30(self) -> None:
        assert time_in_words(3, 30) == 'half past three'

    def test_03_20(self) -> None:
        assert time_in_words(3, 20) == 'twenty minutes past three'
