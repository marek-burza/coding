# https://www.hackerrank.com/challenges/compare-the-triplets

import unittest


def compare_triplets(a: list[int], b: list[int]) -> list[int]:
    result = [0, 0]
    for i in range(3):
        result[0] += 1 if a[i] > b[i] else 0
        result[1] += 1 if a[i] < b[i] else 0
    return result


class TestCode(unittest.TestCase):
    def test_example_0(self) -> None:
        self.assertEqual([1, 1], compare_triplets([5, 6, 7], [3, 6, 10]))

    def test_example_1(self) -> None:
        self.assertEqual([2, 1], compare_triplets([17, 28, 30], [99, 16, 8]))
