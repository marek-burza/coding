# https://leetcode.com/problems/sqrtx/

import unittest


class Solution:
    def mySqrt(self, x: int) -> int:
        a = 0
        z = x
        while a + 1 < z:
            m = (a + z) >> 1
            mm = m * m
            if mm == x:
                return int(m)
            if mm < x:
                a = m
            else:
                z = m - 1
        if z * z <= x:
            return int(z)
        return int(a)


class TestCode(unittest.TestCase):
    def test_example_1(self) -> None:
        assert Solution().mySqrt(4) == 2

    def test_example_2(self) -> None:
        assert Solution().mySqrt(8) == 2

    def test_64(self) -> None:
        assert Solution().mySqrt(64) == 8

    def test_2(self) -> None:
        assert Solution().mySqrt(2) == 1

    def test_1(self) -> None:
        assert Solution().mySqrt(1) == 1
