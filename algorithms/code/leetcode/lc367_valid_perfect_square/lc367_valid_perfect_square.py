# https://leetcode.com/problems/valid-perfect-square/

import unittest


class Solution:
    def isPerfectSquare(self, num: int) -> bool:
        a = 0
        z = 1 + num // 2
        while a <= z:
            m = (a + z) // 2
            mm = m * m
            if mm == num:
                return True
            if mm > num:
                z = m - 1
            else:
                a = m + 1
        return False


class TestCode(unittest.TestCase):
    def test_1(self) -> None:
        self.assertTrue(Solution().isPerfectSquare(1))

    def test_14(self) -> None:
        self.assertFalse(Solution().isPerfectSquare(14))

    def test_16(self) -> None:
        self.assertTrue(Solution().isPerfectSquare(16))

    def test_maximum(self) -> None:
        self.assertFalse(Solution().isPerfectSquare(2**31 - 1))
