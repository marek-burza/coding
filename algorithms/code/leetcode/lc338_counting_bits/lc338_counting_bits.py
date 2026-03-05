# https://leetcode.com/problems/counting-bits/

import unittest


class Solution:
    def countBits(self, n: int) -> list[int]:
        result = [0] * (n + 1)
        threshold = 1
        for i, _ in enumerate(result):
            if threshold << 1 <= i:
                threshold <<= 1
            if i == 0:
                result[0] = 0
            else:
                result[i] = 1 + result[i - threshold]
        return result


class TestCode(unittest.TestCase):
    def test_2(self) -> None:
        assert Solution().countBits(2) == [0, 1, 1]

    def test_5(self) -> None:
        assert Solution().countBits(5) == [0, 1, 1, 2, 1, 2]
