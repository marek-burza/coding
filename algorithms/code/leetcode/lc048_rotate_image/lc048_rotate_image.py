# https://leetcode.com/problems/rotate-image/

import unittest


class Solution:
    def rotate(self, matrix: list[list[int]]) -> None:
        row = 0
        while row < (len(matrix) // 2) + (len(matrix) & 1):
            column = row
            while column < len(matrix) - 1 - row:
                exchange = matrix[row][column]
                matrix[row][column] = matrix[len(matrix) - 1 - column][row]
                value = matrix[len(matrix) - 1 - row][len(matrix) - 1 - column]
                matrix[len(matrix) - 1 - column][row] = value
                value = matrix[column][len(matrix) - 1 - row]
                matrix[len(matrix) - 1 - row][len(matrix) - 1 - column] = value
                matrix[column][len(matrix) - 1 - row] = exchange
                column += 1
            row += 1


class TestCode(unittest.TestCase):
    def __test_matrices(
        self, expected: list[list[int]], result: list[list[int]]
    ) -> None:
        assert len(expected) == len(result)
        row = 0
        while row < len(expected):
            assert len(expected[row]) == len(result[row])
            col = 0
            while col < len(expected[row]):
                assert expected[row][col] == result[row][col]
                col += 1
            row += 1

    def test_even(self) -> None:
        matrix = [[0, 1], [2, 3]]
        expected = [[2, 0], [3, 1]]
        Solution().rotate(matrix)
        self.__test_matrices(expected, matrix)

    def test_odd(self) -> None:
        matrix = [[0, 1, 2], [3, 4, 5], [6, 7, 8]]
        expected = [[6, 3, 0], [7, 4, 1], [8, 5, 2]]
        Solution().rotate(matrix)
        self.__test_matrices(expected, matrix)
