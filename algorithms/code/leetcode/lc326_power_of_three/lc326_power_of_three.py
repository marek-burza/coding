#!/usr/bin/env python3
# https://leetcode.com/problems/power-of-three/
# To do it without a loop resort to logarithms (but beware of accuracy)

import unittest


class Solution:
    def isPowerOfThree(self, n: int) -> bool:
        if n < 1:
            return False
        while n > 1:
            if n % 3 != 0:
                return False
            n //= 3
        return True


class TestCode(unittest.TestCase):
    def test_27(self):
        self.assertTrue(Solution().isPowerOfThree(27))

    def test_11(self):
        self.assertFalse(Solution().isPowerOfThree(11))

    def test_1(self):
        self.assertTrue(Solution().isPowerOfThree(1))

    def test_0(self):
        self.assertEqual(Solution().isPowerOfThree(0), False)

    def test_Minus3(self):
        self.assertEqual(Solution().isPowerOfThree(-3), False)
