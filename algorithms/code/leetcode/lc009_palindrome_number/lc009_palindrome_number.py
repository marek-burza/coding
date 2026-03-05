# https://leetcode.com/problems/palindrome-number/

import unittest


class Solution:
    def isPalindrome(self, x: int) -> bool:
        if x < 0:
            return False
        xa = x
        xb = 0
        while x > 0:
            xb = xb * 10 + x % 10
            x //= 10
        return xa == xb


class TestCode(unittest.TestCase):
    def test_213(self) -> None:
        assert not Solution().isPalindrome(213)

    def test_456(self) -> None:
        assert not Solution().isPalindrome(456)

    def test_454(self) -> None:
        assert Solution().isPalindrome(454)

    def test_99(self) -> None:
        assert Solution().isPalindrome(99)

    def test_1(self) -> None:
        assert Solution().isPalindrome(1)

    def test_10(self) -> None:
        assert not Solution().isPalindrome(10)

    def test_minus1(self) -> None:
        assert not Solution().isPalindrome(-1)

    def test_0(self) -> None:
        assert Solution().isPalindrome(0)
