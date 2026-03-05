# https://www.hackerrank.com/challenges/camelcase

import unittest


def camelcase(s: str) -> int:
    count = 1
    for character in s:
        if character.isupper():
            count += 1
    return count


class TestCode(unittest.TestCase):
    def test_example(self) -> None:
        assert camelcase('saveChangesInTheEditor') == 5
