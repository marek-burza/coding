#!/usr/bin/env python3
# https://www.hackerrank.com/challenges/time-conversion

import unittest


def to_military(s):
    afternoon = s[8] == 'P'
    hour = s[0:2]
    afternoon = not afternoon if hour == '12' else afternoon
    hour = '%02d' % ((int(hour) + (12 if afternoon else 0)) % 24)
    return hour + s[2:8]


class TestCode(unittest.TestCase):
    def test_070545PM(self):
        self.assertEqual('19:05:45', to_military('07:05:45PM'))

    def test_120000PM(self):
        self.assertEqual('12:00:00', to_military('12:00:00PM'))

    def test_120000AM(self):
        self.assertEqual('00:00:00', to_military('12:00:00AM'))
