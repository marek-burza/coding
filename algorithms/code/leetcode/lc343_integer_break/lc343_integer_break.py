# https://leetcode.com/problems/integer-break/

import unittest


class Solution:
    def integerBreak(self, n: int) -> int:
        if n == 2:
            return 1
        if n == 3:
            return 2
        if n == 5:
            return 6
        threes = n // 3
        rest = n - 3 * (threes - 1)
        rest = 6 if rest == 5 else rest
        return (3 ** (threes - 1)) * rest
        # product = 1
        # while n > 4:
        #     product *= 3
        #     n -= 3
        # return product * n


class TestCode(unittest.TestCase):
    def test_2(self) -> None:
        assert Solution().integerBreak(2) == 1

    def test_3(self) -> None:
        assert Solution().integerBreak(3) == 2

    def test_4(self) -> None:
        assert Solution().integerBreak(4) == 4

    def test_5(self) -> None:
        assert Solution().integerBreak(5) == 6

    def test_6(self) -> None:
        assert Solution().integerBreak(6) == 9

    def test_7(self) -> None:
        assert Solution().integerBreak(7) == 12

    def test_8(self) -> None:
        assert Solution().integerBreak(8) == 18

    def test_9(self) -> None:
        assert Solution().integerBreak(9) == 27

    def test_10(self) -> None:
        assert Solution().integerBreak(10) == 36
