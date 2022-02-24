#!/usr/bin/env python3
# https://leetcode.com/problems/spiral-matrix/

import unittest

from typing import List


class Solution:
    __DELTAS = [[1, 0], [0, 1], [-1, 0], [0, -1]]

    def spiralOrder(self, matrix: List[List[int]]) -> List[int]:
        listed: List[int] = []
        if matrix is None or len(matrix) == 0:
            return listed
        index = 0
        top = 0
        bottom = len(matrix) - 1
        left = 0
        right = len(matrix[0]) - 1
        x = 0
        y = 0
        while top <= bottom and left <= right:
            if x > right:
                index = 1
                top += 1
                y = top
                x -= 1
                continue
            if y > bottom:
                index = 2
                right -= 1
                x = right
                y -= 1
                continue
            if x < left:
                index = 3
                bottom -= 1
                y = bottom
                x += 1
                continue
            if y < top:
                index = 0
                left += 1
                x = left
                y += 1
                continue
            listed.append(matrix[y][x])
            x += self.__DELTAS[index][0]
            y += self.__DELTAS[index][1]
        return listed


class TestCode(unittest.TestCase):
    def __test(self, expected, matrix):
        result = Solution().spiralOrder(matrix)
        array = [0] * len(result)
        for i, _ in enumerate(array):
            array[i] = result[i]
        self.assertListEqual(expected, array)

    def test_2_5_8__4_0_Minus1(self):
        matrix = [[2, 5, 8], [4, 0, -1]]
        expected = [2, 5, 8, -1, 0, 4]
        self.__test(expected, matrix)

    def test_2_5__8_4__0_Minus1(self):
        matrix = [[2, 5], [8, 4], [0, -1]]
        expected = [2, 5, 4, -1, 0, 8]
        self.__test(expected, matrix)

    def test_nothing(self):
        self.assertListEqual([], Solution().spiralOrder(None))
        self.assertListEqual([], Solution().spiralOrder([]))
