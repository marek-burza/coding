#!/usr/bin/env python3
# https://leetcode.com/problems/bulb-switcher/

import math
import unittest


class Solution:
    def bulbSwitch(self, n: int) -> int:
        return int(math.sqrt(n))


class TestCode(unittest.TestCase):
    def test_1_to_16(self):
        expected = [0, 1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 3, 3, 4]
        for i, expected_i in enumerate(expected):
            self.assertEqual(expected_i, Solution().bulbSwitch(i))
