# https://leetcode.com/problems/factorial-trailing-zeroes/

import unittest


class Solution:
    def trailingZeroes(self, n: int) -> int:
        step = 5
        count = 0
        while step <= n:
            count += n // step
            step = step * 5
        return count


class TestCode(unittest.TestCase):
    def test_5(self) -> None:
        assert Solution().trailingZeroes(5) == 1

    def test_1808548329(self) -> None:
        assert Solution().trailingZeroes(1808548329) == 452137076

    def test_2147483647(self) -> None:
        assert Solution().trailingZeroes(2147483647) == 536870902
