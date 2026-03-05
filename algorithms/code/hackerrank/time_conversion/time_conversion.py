# https://www.hackerrank.com/challenges/time-conversion

import unittest


def to_military(s: str) -> str:
    afternoon = s[8] == 'P'
    hour = s[0:2]
    afternoon = not afternoon if hour == '12' else afternoon
    hour = f'{(int(hour) + (12 if afternoon else 0)) % 24:02d}'
    return hour + s[2:8]


class TestCode(unittest.TestCase):
    def test_070545PM(self) -> None:
        assert to_military('07:05:45PM') == '19:05:45'

    def test_120000PM(self) -> None:
        assert to_military('12:00:00PM') == '12:00:00'

    def test_120000AM(self) -> None:
        assert to_military('12:00:00AM') == '00:00:00'
