#!/usr/bin/env python3
# https://leetcode.com/problems/climbing-stairs/

import unittest


class Solution:
    def __climbStairs(self, n, at, lut):
        if at + 2 == n:
            return 2
        if at + 1 == n:
            return 1
        if lut[at] == 0:
            lut[at] = self.__climbStairs(n, at + 1, lut)
            lut[at] += self.__climbStairs(n, at + 2, lut)
        return lut[at]

    def climbStairs(self, n: int) -> int:
        lut = [0] * n
        return self.__climbStairs(n, 0, lut)


class TestCode(unittest.TestCase):
    def test_20(self):
        self.assertEqual(10946, Solution().climbStairs(20))
