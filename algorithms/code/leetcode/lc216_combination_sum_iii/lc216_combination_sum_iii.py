#!/usr/bin/env python3
# https://leetcode.com/problems/combination-sum-iii/

import unittest

from typing import List


class Solution:
    # pylint: disable=R0913
    def __traverse(self, contains, summed, left, n, found, start):
        if left == 0 and summed == n:
            found.append(contains)
        else:
            for i in range(start, 10):
                mask = 1 << i
                # if (contains & mask) == 0:
                self.__traverse(
                    contains | mask, summed + i, left - 1, n, found, i + 1)

    def combinationSum3(self, k: int, n: int) -> List[List[int]]:
        found: List[int] = []
        self.__traverse(0, 0, k, n, found, 1)
        each = []
        for contains in found:
            entry: List[int] = []
            each.append(entry)
            for i in range(1, 10):
                mask = 1 << i
                if (contains & mask) != 0:
                    entry.append(i)
        return each


class TestCode(unittest.TestCase):
    def __test(self, expected, result):
        for entry in result:
            entry.sort()
        self.assertEqual(len(expected), len(result))
        for i, _ in enumerate(expected):
            entry = result[i]
            self.assertEqual(len(expected[i]), len(entry))
            for j, _ in enumerate(expected[i]):
                self.assertEqual(expected[i][j], entry[j])

    def test_3_7(self):
        expected = [[1, 2, 4]]
        self.__test(expected, Solution().combinationSum3(3, 7))

    def test_3_9(self):
        expected = [[1, 2, 6], [1, 3, 5], [2, 3, 4]]
        self.__test(expected, Solution().combinationSum3(3, 9))
