#!/usr/bin/env python3
# https://leetcode.com/problems/guess-number-higher-or-lower/

import unittest

lc374_number_higher_or_lower = 0


def guess(num: int) -> int:
    # pylint: disable=W0602,W0603
    global lc374_number_higher_or_lower  # noqa: F824
    if lc374_number_higher_or_lower < num:
        return -1
    if lc374_number_higher_or_lower > num:
        return 1
    return 0


class Solution:
    def guessNumber(self, n: int) -> int:
        a = 1
        z = n
        while a != z:
            checking = (a + z) >> 1
            checked = guess(checking)
            if checked == 0:
                return checking
            if checked == 1:
                a = checking + 1
            else:
                z = checking - 1
        return a


class TestCode(unittest.TestCase):
    def test_2_in_10(self):
        # pylint: disable=W0603
        global lc374_number_higher_or_lower
        lc374_number_higher_or_lower = 2
        self.assertEqual(
            lc374_number_higher_or_lower, Solution().guessNumber(10)
        )

    def test_8_in_10(self):
        # pylint: disable=W0603
        global lc374_number_higher_or_lower
        lc374_number_higher_or_lower = 8
        self.assertEqual(
            lc374_number_higher_or_lower, Solution().guessNumber(10)
        )

    def test_65789(self):
        # pylint: disable=W0603
        global lc374_number_higher_or_lower
        lc374_number_higher_or_lower = 65789
        self.assertEqual(
            lc374_number_higher_or_lower, Solution().guessNumber(2**31 - 1)
        )

    def test_1(self):
        # pylint: disable=W0603
        global lc374_number_higher_or_lower
        lc374_number_higher_or_lower = 1
        self.assertEqual(
            lc374_number_higher_or_lower, Solution().guessNumber(1)
        )
