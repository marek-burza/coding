# https://leetcode.com/problems/nim-game/

import unittest


class Solution:
    def canWinNim(self, n: int) -> bool:
        return n % 4 != 0


class TestCode(unittest.TestCase):
    def test_1to10(self) -> None:
        # +  1 o
        assert Solution().canWinNim(1)
        # +  2 o o
        assert Solution().canWinNim(2)
        # +  3 o o o
        assert Solution().canWinNim(3)
        # -  4 o ? ? x
        assert not Solution().canWinNim(4)
        # +  5 o x ? ? o
        assert Solution().canWinNim(5)
        # +  6 o o x ? ? o
        assert Solution().canWinNim(6)
        # +  7 o o o x ? ? o
        assert Solution().canWinNim(7)
        # -  8 o ? ? . . . . x     (leads to 7, 6 or 5)
        assert not Solution().canWinNim(8)
        # +  9 o x ? ? . . . . o   (leads to 8)
        assert Solution().canWinNim(9)
        # + 10 o o x ? ? . . . . o (leads to 8)
        assert Solution().canWinNim(10)
