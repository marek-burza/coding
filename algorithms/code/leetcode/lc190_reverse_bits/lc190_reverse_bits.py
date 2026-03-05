# https://leetcode.com/problems/reverse-bits/

import unittest


class Solution:
    def reverseBits(self, n: int) -> int:
        r = 0
        for _ in range(0, 32):
            r <<= 1
            r |= n & 1
            n >>= 1
        return r


class TestCode(unittest.TestCase):
    def test_43261596(self) -> None:
        assert Solution().reverseBits(43261596) == 964176192
