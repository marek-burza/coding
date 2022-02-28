#!/usr/bin/env python3
# https://leetcode.com/problems/set-matrix-zeroes/

import unittest
from typing import List


class Solution:
    # pylint: disable=R0912
    def setZeroes(self, matrix: List[List[int]]) -> None:
        row0 = False
        for row, _ in enumerate(matrix):
            if matrix[row][0] == 0:
                row0 = True
        col0 = False
        for col, _ in enumerate(matrix[0]):
            if matrix[0][col] == 0:
                col0 = True
        for row in range(1, len(matrix)):
            for col in range(1, len(matrix[row])):
                if matrix[row][col] == 0:
                    matrix[row][0] = matrix[0][col] = 0
        for row in range(1, len(matrix)):
            for col in range(1, len(matrix[row])):
                if matrix[row][0] == 0 or matrix[0][col] == 0:
                    matrix[row][col] = 0
        if row0:
            for row, _ in enumerate(matrix):
                matrix[row][0] = 0
        if col0:
            for col, _ in enumerate(matrix[0]):
                matrix[0][col] = 0


class TestCode(unittest.TestCase):
    def __test(self, expected, matrix):
        self.assertEqual(len(expected), len(matrix))
        row = 0
        while row < len(expected):
            self.assertListEqual(expected[row], matrix[row])
            row += 1

    def test_smaller_example_1(self):
        matrix = [[1, 0]]
        expected = [[0, 0]]
        Solution().setZeroes(matrix)
        self.__test(expected, matrix)

    def test_smaller_example_2(self):
        matrix = [[0, 1]]
        expected = [[0, 0]]
        Solution().setZeroes(matrix)
        self.__test(expected, matrix)

    def test_bigger_example(self):
        matrix = [
            [0, 0, 0, 5],
            [4, 3, 1, 4],
            [0, 1, 1, 4],
            [1, 2, 1, 3],
            [0, 0, 1, 1],
        ]
        expected = [
            [0, 0, 0, 0],
            [0, 0, 0, 4],
            [0, 0, 0, 0],
            [0, 0, 0, 3],
            [0, 0, 0, 0],
        ]
        Solution().setZeroes(matrix)
        self.__test(expected, matrix)

    def test_other(self):
        matrix = [[1, 1, 1], [1, 0, 1], [1, 1, 1]]
        expected = [[1, 0, 1], [0, 0, 0], [1, 0, 1]]
        Solution().setZeroes(matrix)
        self.__test(expected, matrix)
