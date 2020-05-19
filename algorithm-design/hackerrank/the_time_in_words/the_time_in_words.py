#!/usr/bin/env python3
# https://www.hackerrank.com/challenges/the-time-in-words

import os
import unittest


LUT = [
    'zero', 'one', 'two', 'three', 'four', 'five',
    'six', 'seven', 'eight', 'nine', 'ten',
    'eleven', 'twelve', 'thirteen', 'fourteen', 'fifteen',
    'sixteen', 'seventeen', 'eighteen', 'nineteen', 'twenty',
    'twenty one', 'twenty two', 'twenty three', 'twenty four', 'twenty five',
    'twenty six', 'twenty seven', 'twenty eight', 'twenty nine', 'thirty'
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
        result += ' o\' clock'
    return result


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
        arguments = io_lines[0][1]
        arguments = [int(item) for item in arguments]
        result = time_in_words(int(io_lines[0][0][0]), int(io_lines[0][1][0]))
        self.assertEqual(' '.join(io_lines[1][0]), result)

    def test_example_0(self):
        self.runner('_example_0')

    def test_example_1(self):
        self.runner('_example_1')

    def test_example_2(self):
        self.runner('_example_2')

    def test_03_59(self):
        self.assertEqual('one minute to four', time_in_words(3, 59))

    def test_03_01(self):
        self.assertEqual('one minute past three', time_in_words(3, 1))

    def test_03_45(self):
        self.assertEqual('quarter to four', time_in_words(3, 45))

    def test_03_30(self):
        self.assertEqual('half past three', time_in_words(3, 30))

    def test_03_20(self):
        self.assertEqual(
            'twenty minutes past three', time_in_words(3, 20))
