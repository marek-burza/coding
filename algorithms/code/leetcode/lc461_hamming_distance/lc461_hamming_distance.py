#!/usr/bin/env python3
# https://leetcode.com/problems/hamming-distance/

import unittest


class Solution:
    def hammingDistance(self, x: int, y: int) -> int:
        return (x ^ y).bit_count()


class TestCode(unittest.TestCase):
    def test_example_1(self):
        self.assertEqual(2, Solution().hammingDistance(1, 4))

    def test_example_2(self):
        self.assertEqual(1, Solution().hammingDistance(3, 1))
