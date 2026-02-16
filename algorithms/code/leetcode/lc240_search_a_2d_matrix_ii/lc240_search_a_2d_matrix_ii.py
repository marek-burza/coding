#!/usr/bin/env python3
# https://leetcode.com/problems/search-a-2d-matrix-ii/

import unittest
from typing import List


class Solution:
    # pylint: disable=R0911,R0913
    def __searchMatrix(self, matrix, target, rowA, rowZ, colA, colZ):
        if rowA == rowZ and colA == colZ:
            return matrix[rowZ][colZ] == target
        # if target < matrix[rowA][colA] or matrix[rowZ][colZ] < target:
        #     return False
        rowM = (rowA + rowZ) // 2
        colM = (colA + colZ) // 2
        cols = colA < colZ
        rows = rowA < rowZ
        if target <= matrix[rowM][colM] and self.__searchMatrix(
            matrix, target, rowA, rowM, colA, colM
        ):
            return True
        if (
            cols
            and target <= matrix[rowM][colZ]
            and self.__searchMatrix(matrix, target, rowA, rowM, colM + 1, colZ)
        ):
            return True
        if (
            rows
            and target <= matrix[rowZ][colM]
            and self.__searchMatrix(matrix, target, rowM + 1, rowZ, colA, colM)
        ):
            return True
        if not (rows and cols):
            return False
        return self.__searchMatrix(
            matrix, target, rowM + 1, rowZ, colM + 1, colZ
        )

    def searchMatrix(self, matrix: List[List[int]], target: int) -> bool:
        return self.__searchMatrix(
            matrix, target, 0, len(matrix) - 1, 0, len(matrix[0]) - 1
        )


class TestCode(unittest.TestCase):
    EXAMPLE_MATRIX = [
        [1, 4, 7, 11, 15],
        [2, 5, 8, 12, 19],
        [3, 6, 9, 16, 22],
        [10, 13, 14, 17, 24],
        [18, 21, 23, 26, 30],
    ]

    def test_example_1(self):
        self.assertTrue(Solution().searchMatrix(TestCode.EXAMPLE_MATRIX, 5))

    def test_example_2(self):
        self.assertFalse(Solution().searchMatrix(TestCode.EXAMPLE_MATRIX, 20))

    def test_other_1(self):
        matrix = [[1, 4], [2, 5]]
        self.assertTrue(Solution().searchMatrix(matrix, 2))

    def test_other_2(self):
        matrix = [[-1, 3]]
        self.assertFalse(Solution().searchMatrix(matrix, 1))

    def test_other_3(self):
        matrix = [
            [1, 2, 3, 4, 5],
            [6, 7, 8, 9, 10],
            [11, 12, 13, 14, 15],
            [16, 17, 18, 19, 20],
            [21, 22, 23, 24, 25],
        ]
        self.assertTrue(Solution().searchMatrix(matrix, 5))
