# https://www.hackerrank.com/challenges/tutorial-intro

import unittest


def intro_tutorial(v: int, arr: list[int]) -> int:
    a = 0
    z = len(arr) - 1
    while a <= z:
        m = (a + z) >> 1
        if arr[m] == v:
            return m
        if arr[m] < v:
            a = m + 1
        if arr[m] > v:
            z = m - 1
    return -1


class TestCode(unittest.TestCase):
    def test_example(self) -> None:
        assert intro_tutorial(4, [1, 4, 5, 7, 9, 12]) == 1

    def test_another(self) -> None:
        assert intro_tutorial(7, [1, 4, 5, 7, 9, 12]) == 3

    def test_invalid(self) -> None:
        assert intro_tutorial(20, [1, 4, 5, 7, 9, 12]) == -1
