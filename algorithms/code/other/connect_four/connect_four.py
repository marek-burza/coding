#!/usr/bin/env python3
# https://en.wikipedia.org/wiki/Connect_Four

import enum
import unittest
from typing import List, Optional


class Player(enum.IntEnum):
    ONE = 1
    TWO = 2


class Winner(enum.IntEnum):
    ONE = 1
    TWO = 2
    UNDECIDED = 0
    DRAW = -1


class BoardState:
    MAX_COLUMNS = 7
    MAX_ROWS = 6
    MAX_STEPS = 4

    def __init__(self) -> None:
        self.__board: List[List[Player]] = [
            [] for _ in range(BoardState.MAX_COLUMNS)
        ]  # noqa

    def check_drop(self, c: int) -> bool:
        return len(self.__board[c]) < BoardState.MAX_COLUMNS - 1

    def drop(self, player: Player, c: int) -> bool:
        if not self.check_drop(c):
            return False
        if self.check_winning() != Winner.UNDECIDED:
            return False
        self.__board[c].append(player)
        return True

    def player_at(self, r: int, c: int) -> Optional[Player]:
        if c < len(self.__board) and r < len(self.__board[c]):
            return self.__board[c][r]
        return None

    def check_winning_at(self, r: int, c: int) -> Optional[Player]:
        player = self.player_at(r, c)
        for dr, dc in [(0, 1), (1, 0), (1, -1), (1, 1)]:
            for delta in range(1, BoardState.MAX_STEPS):
                if self.player_at(r + delta * dr, c + delta * dc) != player:
                    break
                if delta == BoardState.MAX_STEPS - 1:
                    return player
        return None

    def check_winning(self) -> Optional[Winner]:
        columns = range(BoardState.MAX_COLUMNS)
        for c in columns:
            for r in range(BoardState.MAX_ROWS):
                if self.player_at(r, c) is None:
                    break
                player = self.check_winning_at(r, c)
                if player is not None:
                    return Winner(int(player))
        sizes = [len(self.__board[c]) for c in columns]
        if all(size == BoardState.MAX_ROWS for size in sizes):
            return Winner.DRAW
        return Winner.UNDECIDED

    def __str__(self):
        lut = {Player.ONE: 'O', Player.TWO: 'X', None: '.'}
        rows = range(BoardState.MAX_ROWS - 1, -1, -1)
        columns = range(BoardState.MAX_COLUMNS)

        def line(r):
            return ''.join([lut[self.player_at(r, c)] for c in columns])

        return '\n'.join([line(r) for r in rows])


class GameState(BoardState):
    def __init__(self, first):
        super().__init__()
        self.__turn = first

    def drop_for_current_player(self, c: int) -> bool:
        ok = self.drop(self.__turn, c)
        if ok:
            if self.__turn == Player.ONE:
                self.__turn = Player.TWO
            else:
                self.__turn = Player.ONE
        return ok


class TestCode(unittest.TestCase):
    # pylint: disable=R0913
    def generic(self, first, moves, outcomes, winners, board):
        g = GameState(first)
        i = 1
        for move, outcome, winner in zip(moves, outcomes, winners):
            result = g.drop_for_current_player(move)
            message = f'Round {i} :\n{str(g)}'
            self.assertEqual(outcome, result, message)
            self.assertEqual(winner, g.check_winning(), message)
            i += 1
        self.assertEqual('\n'.join(board), str(g))

    def test_one_in_the_middle(self):
        moves = [3]
        outcomes = [True]
        winners = [Winner.UNDECIDED]
        board = [
            '.......',
            '.......',
            '.......',
            '.......',
            '.......',
            '...O...',
        ]
        self.generic(Player.ONE, moves, outcomes, winners, board)

    def test_too_high(self):
        moves = [3] * (BoardState.MAX_ROWS + 1)
        outcomes = [True] * BoardState.MAX_ROWS
        outcomes.append(False)
        winners = [Winner.UNDECIDED] * (BoardState.MAX_ROWS + 1)
        board = [
            '...X...',
            '...O...',
            '...X...',
            '...O...',
            '...X...',
            '...O...',
        ]
        self.generic(Player.ONE, moves, outcomes, winners, board)

    def test_horizontal(self):
        moves = [3, 0, 4, 1, 5, 2, 6]
        outcomes = [True] * 7
        winners = [Winner.UNDECIDED] * 6
        winners.append(Player.ONE)
        board = [
            '.......',
            '.......',
            '.......',
            '.......',
            '.......',
            'XXXOOOO',
        ]
        self.generic(Player.ONE, moves, outcomes, winners, board)

    def test_vertical(self):
        moves = [3, 1, 3, 1, 3, 1, 3]
        outcomes = [True] * 7
        winners = [Winner.UNDECIDED] * 6
        winners.append(Player.ONE)
        board = [
            '.......',
            '.......',
            '...O...',
            '.X.O...',
            '.X.O...',
            '.X.O...',
        ]
        self.generic(Player.ONE, moves, outcomes, winners, board)

    def test_draw(self):
        cell_count = BoardState.MAX_COLUMNS * BoardState.MAX_ROWS
        moves = []
        for i in range(3):
            for _ in range(3):
                moves.append(2 * i)
                moves.append(2 * i + 1)
        for i in range(3):
            moves.append(6)
            moves.append(0)
        for i in range(3):
            for _ in range(3):
                moves.append(2 * i + 1)
                moves.append(2 * i + 2)
        outcomes = [True] * cell_count
        winners = [Winner.UNDECIDED] * cell_count
        winners[-1] = Winner.DRAW
        board = [
            'XOXOXOX',
            'XOXOXOX',
            'XOXOXOX',
            'OXOXOXO',
            'OXOXOXO',
            'OXOXOXO',
        ]
        self.generic(Player.ONE, moves, outcomes, winners, board)

    def test_diagonal_ascending(self):
        moves = [2, 1, 1, 2, 3, 3, 2, 3, 4, 4, 4, 4]
        outcomes = [True] * len(moves)
        winners = [Winner.UNDECIDED] * (len(moves) - 1)
        winners.append(Player.TWO)
        board = [
            '.......',
            '.......',
            '....X..',
            '..OXO..',
            '.OXXX..',
            '.XOOO..',
        ]
        self.generic(Player.ONE, moves, outcomes, winners, board)

    def test_diagonal_descending(self):
        moves = [4, 5, 5, 4, 3, 3, 4, 3, 2, 2, 2, 2]
        outcomes = [True] * len(moves)
        winners = [Winner.UNDECIDED] * (len(moves) - 1)
        winners.append(Player.TWO)
        board = [
            '.......',
            '.......',
            '..X....',
            '..OXO..',
            '..XXXO.',
            '..OOOX.',
        ]
        self.generic(Player.ONE, moves, outcomes, winners, board)

    def test_one_more_move(self):
        moves = [4, 5, 5, 4, 3, 3, 4, 3, 2, 2, 2, 2, 0]
        outcomes = [True] * len(moves)
        outcomes[-1] = False
        winners = [Winner.UNDECIDED] * len(moves)
        winners[-2] = Player.TWO
        winners[-1] = Player.TWO
        board = [
            '.......',
            '.......',
            '..X....',
            '..OXO..',
            '..XXXO.',
            '..OOOX.',
        ]
        self.generic(Player.ONE, moves, outcomes, winners, board)
