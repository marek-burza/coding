#!/usr/bin/env python3
# https://leetcode.com/problems/rectangle-area/

import unittest


class Solution:
    def __area(self, left, bottom, right, top):
        return (right - left) * (top - bottom)

    # pylint: disable=C0301,R0913
    def computeArea(self, ax1: int, ay1: int, ax2: int, ay2: int, bx1: int, by1: int, bx2: int, by2: int) -> int:  # noqa
        total = self.__area(ax1, ay1, ax2, ay2)
        total += self.__area(bx1, by1, bx2, by2)
        top = min(ay2, by2)
        bottom = max(ay1, by1)
        left = max(ax1, bx1)
        right = min(ax2, bx2)
        if bottom < top and left < right:
            total -= self.__area(left, bottom, right, top)
        return total


class TestCode(unittest.TestCase):
    def test_minus3_0_3_4_0_minus1_9_2(self):
        self.assertEqual(
            45, Solution().computeArea(-3, 0, 3, 4, 0, -1, 9, 2))

    def test_minus2_minus2_2_2_minus1_4_1_6(self):
        self.assertEqual(
            20, Solution().computeArea(-2, -2, 2, 2, -1, 4, 1, 6))

    def test_minus5_minus5_minus4_0_minus3_minus3_3_3(self):
        self.assertEqual(
            41, Solution().computeArea(-5, -5, -4, 0, -3, -3, 3, 3))
