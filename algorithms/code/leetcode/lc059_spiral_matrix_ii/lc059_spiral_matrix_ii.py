#!/usr/bin/env python3
# https://leetcode.com/problems/spiral-matrix-ii/

import unittest

from typing import List


class Solution:
    def generateMatrix(self, n: int) -> List[List[int]]:
        limits = [n - 1, n - 1, 0, 0]
        restrict = [1, -1, -1, 1]
        delta = [[0, 1], [1, 0], [0, -1], [-1, 0]]
        indices = [0, -1]
        matrix = [[0] * n for _ in range(n)]
        value = 1
        stage = 0
        index = 1
        while limits[0] >= limits[2] and limits[1] >= limits[3]:
            condition = True
            while condition:
                indices[0] += delta[stage][0]
                indices[1] += delta[stage][1]
                matrix[indices[0]][indices[1]] = value
                value += 1
                condition = indices[index] != limits[stage]
            limits[(stage + 3) % 4] += restrict[stage]
            stage = (stage + 1) % 4
            index = (index + 1) % 2
        return matrix


class TestCode(unittest.TestCase):
    def test_example(self):
        expected = [[1, 2, 3], [8, 9, 4], [7, 6, 5]]
        result = Solution().generateMatrix(3)
        self.assertIsNotNone(result)
        self.assertEqual(len(expected), len(result))
        for i, _ in enumerate(expected):
            self.assertListEqual(expected[i], result[i])
