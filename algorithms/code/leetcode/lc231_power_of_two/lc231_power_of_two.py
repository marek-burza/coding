#!/usr/bin/env python3
# https://leetcode.com/problems/power-of-two/

import unittest


class Solution:
    def isPowerOfTwo(self, n: int) -> bool:
        if n <= 0:
            return False
        count = 0
        mask = 1
        while mask != 0:
            count += 0 if ((n & mask) == 0) else 1
            mask = (mask << 1) & 0xFFFFFFFF
        return count == 1


class TestCode(unittest.TestCase):
    def test_minus10(self):
        self.assertEqual(False, Solution().isPowerOfTwo(-10))

    def test_0(self):
        self.assertEqual(False, Solution().isPowerOfTwo(0))

    def test_1(self):
        self.assertEqual(True, Solution().isPowerOfTwo(1))

    def test_2(self):
        self.assertEqual(True, Solution().isPowerOfTwo(2))

    def test_5(self):
        self.assertEqual(False, Solution().isPowerOfTwo(5))

    def test_1024(self):
        self.assertEqual(True, Solution().isPowerOfTwo(1024))
