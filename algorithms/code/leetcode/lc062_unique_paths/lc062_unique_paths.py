# https://leetcode.com/problems/unique-paths/

import unittest


class Solution:
    def __nck(self, n: int, k: int) -> int:
        if k > n:
            return 0
        if k * 2 > n:
            k = n - k
        if k == 0:
            return 1
        r = n
        for i in range(2, k + 1):
            r *= n - i + 1
            r //= i
        return r

    def uniquePaths(self, m: int, n: int) -> int:
        m -= 1
        return int(self.__nck(m + n - 1, m))


class TestCode(unittest.TestCase):
    def test_3_7(self) -> None:
        assert Solution().uniquePaths(3, 7) == 28

    def test_59_5(self) -> None:
        assert Solution().uniquePaths(59, 5) == 557845

    def test_1_10(self) -> None:
        assert Solution().uniquePaths(1, 10) == 1

    def test_nothing(self) -> None:
        assert Solution().uniquePaths(1, 0) == 0
