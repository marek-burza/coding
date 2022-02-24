#!/usr/bin/env python3
# https://leetcode.com/problems/search-a-2d-matrix/

import unittest
from typing import List


class Solution:
    def searchMatrix(self, matrix: List[List[int]], target: int) -> bool:
        ra = 0
        rz = len(matrix) - 1
        while ra < rz:
            rm = 1 + (ra + rz) // 2
            if target >= matrix[rm][0]:
                ra = rm
            else:
                rz = rm - 1
        # if rz < 0:
        #     return False
        ca = 0
        cz = len(matrix[ra]) - 1
        while ca < cz:
            cm = 1 + (ca + cz) // 2
            if target == matrix[ra][cm]:
                return True
            if target > matrix[ra][cm]:
                ca = cm + 1
            else:
                cz = cm - 1
        return ca == cz and target == matrix[ra][ca]


class TestCode(unittest.TestCase):
    def test_example_1(self):
        matrix = [[1, 3, 5, 7], [10, 11, 16, 20], [23, 30, 34, 50]]
        self.assertTrue(Solution().searchMatrix(matrix, 3))

    def test_example_2(self):
        matrix = [[1]]
        self.assertTrue(Solution().searchMatrix(matrix, 1))

    def test_example_3(self):
        matrix = [[1]]
        self.assertFalse(Solution().searchMatrix(matrix, 0))

    def test_example_4(self):
        matrix = [[1, 1]]
        self.assertFalse(Solution().searchMatrix(matrix, 0))

    def test_example_5(self):
        matrix = [[1, 1]]
        self.assertFalse(Solution().searchMatrix(matrix, 2))

    def test_example_6(self):
        matrix = [[1], [3]]
        self.assertTrue(Solution().searchMatrix(matrix, 1))

    def test_other(self):
        matrix = [[1, 3, 5, 7], [10, 11, 16, 20], [23, 30, 34, 60]]
        self.assertFalse(Solution().searchMatrix(matrix, 13))
