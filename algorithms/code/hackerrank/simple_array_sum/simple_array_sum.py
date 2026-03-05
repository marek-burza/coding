# https://www.hackerrank.com/challenges/simple-array-sum

import unittest


def simple_array_sum(ar: list[int]) -> int:
    return sum(ar)


class TestCode(unittest.TestCase):
    def test_example(self) -> None:
        self.assertEqual(31, simple_array_sum([1, 2, 3, 4, 10, 11]))
