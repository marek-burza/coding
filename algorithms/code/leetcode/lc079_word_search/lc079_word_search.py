#!/usr/bin/env python3
# https://leetcode.com/problems/word-search/

import unittest


class Solution:
    # pylint: disable=R0911,R0912,R0913
    def __exist(self, board, word, visited, index, i, j):
        if visited[i][j]:
            # index >= len(word) or visited[i][j]
            return False
        if word[index] == board[i][j]:
            if index == len(word) - 1:
                return True
            visited[i][j] = True
            if i + 1 < len(visited):
                if self.__exist(board, word, visited, index + 1, i + 1, j):
                    return True
            if i - 1 >= 0:
                if self.__exist(board, word, visited, index + 1, i - 1, j):
                    return True
            if j + 1 < len(visited[i]):
                if self.__exist(board, word, visited, index + 1, i, j + 1):
                    return True
            if j - 1 >= 0:
                if self.__exist(board, word, visited, index + 1, i, j - 1):
                    return True
            visited[i][j] = False
            return False
        return False

    def exist(self, board, word):
        if board is None or word is None or len(word) == 0:
            return False
        visited = [[False] * len(board[i]) for i, _ in enumerate(board)]
        for i, _ in enumerate(board):
            for j, _ in enumerate(board[i]):
                if self.__exist(board, word, visited, 0, i, j):
                    return True
        return False


class TestCode(unittest.TestCase):
    def test_example(self):
        self.assertTrue(Solution().exist(
            [['A', 'B', 'C', 'E'], ['S', 'F', 'C', 'S'], ['A', 'D', 'E', 'E']],
            'ABCCED'))

    def test_other(self):
        self.assertTrue(Solution().exist([['a', 'b'], ['c', 'd']], 'acdb'))

    def test_a__ab(self):
        self.assertFalse(Solution().exist([['a']], 'ab'))

    def test_a__a(self):
        self.assertTrue(Solution().exist([['a']], 'a'))

    def test_a_a__aaa(self):
        self.assertFalse(Solution().exist([['a', 'a']], 'aaa'))

    def test_nothing(self):
        self.assertFalse(Solution().exist(None, 'dummy'))
        self.assertFalse(Solution().exist([['a']], None))
        self.assertFalse(Solution().exist([['a']], ''))
