# https://www.hackerrank.com/challenges/a-very-big-sum

import unittest


def a_very_big_sum(ar: list[int]) -> int:
    return sum(ar)


class TestCode(unittest.TestCase):
    def test_example(self) -> None:
        ar = [1000000001, 1000000002, 1000000003, 1000000004, 1000000005]
        result = a_very_big_sum(ar)
        self.assertEqual(result, 5000000015)
