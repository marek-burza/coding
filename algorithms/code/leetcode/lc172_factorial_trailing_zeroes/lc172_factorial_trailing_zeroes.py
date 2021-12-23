#!/usr/bin/env python3
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
    def test_5(self):
        self.assertEqual(1, Solution().trailingZeroes(5))

    def test_1808548329(self):
        self.assertEqual(452137076, Solution().trailingZeroes(1808548329))

    def test_2147483647(self):
        self.assertEqual(536870902, Solution().trailingZeroes(2147483647))
