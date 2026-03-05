# https://leetcode.com/problems/perfect-squares/

import unittest


class Solution:
    def numSquares(self, n: int) -> int:
        lut = [2 * n] * (n + 1)  # Instead of inf
        lut[0] = 0
        i = 1
        ii = 1
        while ii <= n:
            j = ii
            while j < len(lut):
                lut[j] = min(lut[j], lut[j - ii] + 1)
                j += 1
            i += 1
            ii = i * i
        return lut[n]


class TestCode(unittest.TestCase):
    def test_12(self) -> None:
        assert Solution().numSquares(12) == 3

    def test_13(self) -> None:
        assert Solution().numSquares(13) == 2

    def test_9975(self) -> None:
        assert Solution().numSquares(9975) == 4

    def test_9732(self) -> None:
        assert Solution().numSquares(9732) == 3

    def test_5756(self) -> None:
        assert Solution().numSquares(5756) == 4

    def test_6255(self) -> None:
        assert Solution().numSquares(6255) == 4
