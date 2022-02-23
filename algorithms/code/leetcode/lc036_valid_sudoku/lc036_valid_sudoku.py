#!/usr/bin/env python3
# https://leetcode.com/problems/valid-sudoku/

import unittest

from typing import List


class Solution:
    def validate(self, board, indices, dx, dy):
        check = 0
        for at in indices:
            spot = board[at[1] + dy][at[0] + dx]
            if spot == '.':
                continue
            mask = 1 << (ord(spot) - ord('0'))
            if (check & mask) == 0:
                check |= mask
            else:
                return False
        return True

    def isValidSudoku(self, board: List[List[str]]) -> bool:
        row = [
            [0, 0], [1, 0], [2, 0], [3, 0],
            [4, 0], [5, 0], [6, 0], [7, 0],
            [8, 0]]
        column = [
            [0, 0], [0, 1], [0, 2], [0, 3],
            [0, 4], [0, 5], [0, 6], [0, 7],
            [0, 8]]
        block = [
            [0, 0], [0, 1], [0, 2], [1, 0],
            [1, 1], [1, 2], [2, 0], [2, 1],
            [2, 2]]
        for i in range(0, 9):
            if not self.validate(board, row, 0, i):
                return False
            if not self.validate(board, column, i, 0):
                return False
            if not self.validate(board, block, 3 * (i // 3), 3 * (i % 3)):
                return False
        return True


class TestCode(unittest.TestCase):
    EXAMPLE_BOARD = [
        ['5', '3', '.', '.', '7', '.', '.', '.', '.'],
        ['6', '.', '.', '1', '9', '5', '.', '.', '.'],
        ['9', '8', '.', '.', '.', '.', '.', '6', '.'],
        ['8', '.', '.', '.', '6', '.', '.', '.', '3'],
        ['4', '.', '.', '8', '.', '3', '.', '.', '1'],
        ['7', '.', '.', '.', '2', '.', '.', '.', '6'],
        ['.', '6', '.', '.', '.', '.', '2', '8', '.'],
        ['.', '.', '.', '4', '1', '9', '.', '.', '5'],
        ['.', '.', '.', '.', '8', '.', '.', '7', '9']]

    def test_example(self):
        self.assertTrue(Solution().isValidSudoku(TestCode.EXAMPLE_BOARD))

    def test_other_example(self):
        board = [
            ['.', '8', '7', '6', '5', '4', '3', '2', '1'],
            ['2', '.', '.', '.', '.', '.', '.', '.', '.'],
            ['3', '.', '.', '.', '.', '.', '.', '.', '.'],
            ['4', '.', '.', '.', '.', '.', '.', '.', '.'],
            ['5', '.', '.', '.', '.', '.', '.', '.', '.'],
            ['6', '.', '.', '.', '.', '.', '.', '.', '.'],
            ['7', '.', '.', '.', '.', '.', '.', '.', '.'],
            ['8', '.', '.', '.', '.', '.', '.', '.', '.'],
            ['9', '.', '.', '.', '.', '.', '.', '.', '.']]
        self.assertTrue(Solution().isValidSudoku(board))

    def test_another_1(self):
        board = [line[:] for line in TestCode.EXAMPLE_BOARD]
        board[2][0] = '.'
        board[2][1] = '9'
        board[2][2] = '8'
        self.assertTrue(Solution().isValidSudoku(board))

    def test_another_2(self):
        board = [
            ['.', '.', '4', '.', '.', '.', '6', '3', '.'],
            ['.', '.', '.', '.', '.', '.', '.', '.', '.'],
            ['5', '.', '.', '.', '.', '.', '.', '9', '.'],
            ['.', '.', '.', '5', '6', '.', '.', '.', '.'],
            ['4', '.', '3', '.', '.', '.', '.', '.', '1'],
            ['.', '.', '.', '7', '.', '.', '.', '.', '.'],
            ['.', '.', '.', '5', '.', '.', '.', '.', '.'],
            ['.', '.', '.', '.', '.', '.', '.', '.', '.'],
            ['.', '.', '.', '.', '.', '.', '.', '.', '.']]
        self.assertFalse(Solution().isValidSudoku(board))

    def test_another_3(self):
        board = [
            ['.', '.', '.', '.', '5', '.', '.', '1', '.'],
            ['.', '4', '.', '3', '.', '.', '.', '.', '.'],
            ['.', '.', '.', '.', '.', '3', '.', '.', '1'],
            ['8', '.', '.', '.', '.', '.', '.', '2', '.'],
            ['.', '.', '2', '.', '7', '.', '.', '.', '.'],
            ['.', '1', '5', '.', '.', '.', '.', '.', '.'],
            ['.', '.', '.', '.', '.', '2', '.', '.', '.'],
            ['.', '2', '.', '9', '.', '.', '.', '.', '.'],
            ['.', '.', '4', '.', '.', '.', '.', '.', '.']]
        self.assertFalse(Solution().isValidSudoku(board))

    BLANK = ['.', '.', '.', '.', '.', '.', '.', '.', '.']

    def test_another_4(self):
        board = [
            ['7', '.', '.', '.', '4', '.', '.', '.', '.'],
            ['.', '.', '.', '8', '6', '5', '.', '.', '.'],
            ['.', '1', '.', '2', '.', '.', '.', '.', '.'],
            ['.', '.', '.', '.', '.', '9', '.', '.', '.'],
            ['.', '.', '.', '.', '5', '.', '5', '.', '.'],
            ['.', '.', '.', '.', '.', '.', '.', '.', '.'],
            ['.', '.', '.', '.', '.', '.', '2', '.', '.'],
            TestCode.BLANK,
            TestCode.BLANK]
        self.assertFalse(Solution().isValidSudoku(board))
