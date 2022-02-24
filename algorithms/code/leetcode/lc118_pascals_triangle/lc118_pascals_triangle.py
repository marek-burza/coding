#!/usr/bin/env python3
# https://leetcode.com/problems/pascals-triangle/

import unittest
from typing import List


class Solution:
    def generate(self, numRows: int) -> List[List[int]]:
        if numRows < 0:
            return None
        triangle = []
        for i in range(numRows):
            row: List[int] = []
            triangle.append(row)
            row.append(1)
            if i > 0:
                above = triangle[i - 1]
                for j in range(i - 1):
                    row.append(above[j] + above[j + 1])
                row.append(1)
        return triangle


class TestCode(unittest.TestCase):
    def test_5(self):
        expected = [[1], [1, 1], [1, 2, 1], [1, 3, 3, 1], [1, 4, 6, 4, 1]]
        result = Solution().generate(5)
        self.assertEqual(len(expected), len(result))
        for expected_i, result_i in zip(expected, result):
            self.assertListEqual(expected_i, result_i)

    def test_nothing(self):
        self.assertIsNone(Solution().generate(-1))
