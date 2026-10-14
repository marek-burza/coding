from collections import deque
from typing import Deque


class Solution:
    def countBattleships(self, board: list[list[str]]) -> int:
        m = len(board)
        n = len(board[0])
        total = 0
        for mi in range(m):
            for ni in range(n):
                if board[mi][ni] == "X":
                    total += 1
                    queue: Deque = deque()
                    queue.append((mi, ni))
                    while len(queue) != 0:
                        mj, nj = queue.popleft()
                        if 0 <= mj < m and 0 <= nj < n and board[mj][nj] == "X":
                            board[mj][nj] = "."
                            for mo, no in ((-1, 0), (1, 0), (0, -1), (0, 1)):
                                queue.append((mj + mo, nj + no))
        return total


class TestCode:
    def test_example_1(self) -> None:
        assert (
            Solution().countBattleships(
                [["X", ".", ".", "X"], [".", ".", ".", "X"], [".", ".", ".", "X"]]
            )
            == 2
        )

    def test_example_2(self) -> None:
        assert Solution().countBattleships([["."]]) == 0

    # def test_213423(self) -> None:
    #     assert Solution().numberOfArithmeticSlices([2, 1, 3, 4, 2, 3]) == 0

    # def test_1230(self) -> None:
    #     assert Solution().numberOfArithmeticSlices([1, 2, 3, 0]) == 1
