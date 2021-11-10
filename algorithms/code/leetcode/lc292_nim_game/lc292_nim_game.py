#!/usr/bin/env python3
# https://leetcode.com/problems/nim-game/

import unittest


class Solution:
    def canWinNim(self, n: int) -> bool:
        return n % 4 != 0


class TestCode(unittest.TestCase):
    def test_1to10(self):
        # +  1 o
        self.assertTrue(Solution().canWinNim(1))
        # +  2 o o
        self.assertTrue(Solution().canWinNim(2))
        # +  3 o o o
        self.assertTrue(Solution().canWinNim(3))
        # -  4 o ? ? x
        self.assertFalse(Solution().canWinNim(4))
        # +  5 o x ? ? o
        self.assertTrue(Solution().canWinNim(5))
        # +  6 o o x ? ? o
        self.assertTrue(Solution().canWinNim(6))
        # +  7 o o o x ? ? o
        self.assertTrue(Solution().canWinNim(7))
        # -  8 o ? ? . . . . x     (leads to 7, 6 or 5)
        self.assertFalse(Solution().canWinNim(8))
        # +  9 o x ? ? . . . . o   (leads to 8)
        self.assertTrue(Solution().canWinNim(9))
        # + 10 o o x ? ? . . . . o (leads to 8)
        self.assertTrue(Solution().canWinNim(10))
