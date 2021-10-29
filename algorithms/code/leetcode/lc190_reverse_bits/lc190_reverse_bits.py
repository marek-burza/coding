#!/usr/bin/env python3
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
    def test_43261596(self):
        self.assertEqual(964176192, Solution().reverseBits(43261596))
