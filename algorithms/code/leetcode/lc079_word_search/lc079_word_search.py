# https://leetcode.com/problems/word-search/

import unittest


class Solution:
    # pylint: disable=R0911,R0912,R0913,R0917
    def __exist(
        self,
        board: list[list[str]],
        word: str,
        visited: list[list[bool]],
        index: int,
        i: int,
        j: int,
    ) -> bool:
        if visited[i][j]:
            # index >= len(word) or visited[i][j]
            return False
        if word[index] == board[i][j]:
            if index == len(word) - 1:
                return True
            visited[i][j] = True
            if i + 1 < len(visited) and self.__exist(
                board, word, visited, index + 1, i + 1, j
            ):
                return True
            if i - 1 >= 0 and self.__exist(
                board, word, visited, index + 1, i - 1, j
            ):
                return True
            if j + 1 < len(visited[i]) and self.__exist(
                board, word, visited, index + 1, i, j + 1
            ):
                return True
            if j - 1 >= 0 and self.__exist(
                board, word, visited, index + 1, i, j - 1
            ):
                return True
            visited[i][j] = False
            return False
        return False

    def exist(self, board: list[list[str]], word: str) -> bool:
        if not board or not word:
            return False
        visited = [[False] * len(board_i) for i, board_i in enumerate(board)]
        for i, board_i in enumerate(board):
            for j, _ in enumerate(board_i):
                if self.__exist(board, word, visited, 0, i, j):
                    return True
        return False


class TestCode(unittest.TestCase):
    def test_example(self) -> None:
        assert Solution().exist(
            [
                ['A', 'B', 'C', 'E'],
                ['S', 'F', 'C', 'S'],
                ['A', 'D', 'E', 'E'],
            ],
            'ABCCED',
        )

    def test_other(self) -> None:
        assert Solution().exist([['a', 'b'], ['c', 'd']], 'acdb')

    def test_a__ab(self) -> None:
        assert not Solution().exist([['a']], 'ab')

    def test_a__a(self) -> None:
        assert Solution().exist([['a']], 'a')

    def test_a_a__aaa(self) -> None:
        assert not Solution().exist([['a', 'a']], 'aaa')

    def test_nothing(self) -> None:
        assert not Solution().exist([], 'dummy')
        assert not Solution().exist([['a']], '')
