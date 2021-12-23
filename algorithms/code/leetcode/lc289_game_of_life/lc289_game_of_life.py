#!/usr/bin/env python3
# https://leetcode.com/problems/game-of-life/

import unittest

from typing import List


class Solution:
    def __count(self, board, row, col):
        if 0 <= row < len(board) and 0 <= col < len(board[row]):
            return board[row][col] & 1
        return 0

    def __countAlive(self, board, row, col):
        count = 0
        count += self.__count(board, row - 1, col - 1)
        count += self.__count(board, row - 1, col)
        count += self.__count(board, row - 1, col + 1)
        count += self.__count(board, row, col - 1)
        count += self.__count(board, row, col + 1)
        count += self.__count(board, row + 1, col - 1)
        count += self.__count(board, row + 1, col)
        count += self.__count(board, row + 1, col + 1)
        return count

    def gameOfLife(self, board: List[List[int]]) -> None:
        for row, _ in enumerate(board):
            for col, _ in enumerate(board[row]):
                count = self.__countAlive(board, row, col)
                mask = None
                if (board[row][col] & 1) == 1:
                    mask = 0 if (count < 2 or count > 3) else 2
                else:
                    mask = 2 if count == 3 else 0
                board[row][col] |= mask
        for row, _ in enumerate(board):
            for col, _ in enumerate(board[row]):
                board[row][col] >>= 1


class TestCode(unittest.TestCase):
    def __test(self, expected, board):
        self.assertEqual(len(expected), len(board))
        for row, _ in enumerate(board):
            self.assertEqual(len(expected[row]), len(board[row]))
            for col, _ in enumerate(board[row]):
                self.assertEqual(expected[row][col], board[row][col])

    def test_empty(self):
        board = [[]]
        expected = [[]]
        Solution().gameOfLife(board)
        self.__test(expected, board)

    def test_example_1(self):
        board = [[0, 1, 0], [0, 0, 1], [1, 1, 1], [0, 0, 0]]
        expected = [[0, 0, 0], [1, 0, 1], [0, 1, 1], [0, 1, 0]]
        Solution().gameOfLife(board)
        self.__test(expected, board)

    def test_example_2(self):
        board = [[1, 1], [1, 0]]
        expected = [[1, 1], [1, 1]]
        Solution().gameOfLife(board)
        self.__test(expected, board)

    def test_other(self):
        board = [
            [0, 0, 0, 0, 0, 0],
            [0, 0, 0, 0, 0, 0],
            [0, 0, 1, 1, 1, 0],
            [0, 1, 1, 1, 0, 0],
            [0, 0, 0, 0, 0, 0],
            [0, 0, 0, 0, 0, 0]]
        expected = [
            [0, 0, 0, 0, 0, 0],
            [0, 0, 0, 1, 0, 0],
            [0, 1, 0, 0, 1, 0],
            [0, 1, 0, 0, 1, 0],
            [0, 0, 1, 0, 0, 0],
            [0, 0, 0, 0, 0, 0]]
        Solution().gameOfLife(board)
        self.__test(expected, board)
