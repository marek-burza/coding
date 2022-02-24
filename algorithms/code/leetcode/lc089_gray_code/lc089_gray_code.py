#!/usr/bin/env python3
# https://leetcode.com/problems/gray-code/

import unittest
from typing import List


class Solution:
    def grayCode(self, bits: int) -> List[int]:
        if bits == 0:
            listed = [0]
            return listed
        listed = [0, 1]
        shifted = 2
        while bits > 1:
            bits -= 1
            n = len(listed)
            for i in range(n - 1, -1, -1):
                value = listed[i]
                listed.append(shifted | value)
            shifted <<= 1
        return listed


class TestCode(unittest.TestCase):
    def test_4(self):
        expected = [0, 1, 3, 2, 6, 7, 5, 4, 12, 13, 15, 14, 10, 11, 9, 8]
        self.assertListEqual(expected, Solution().grayCode(4))

    def test_0(self):
        expected = [0]
        self.assertListEqual(expected, Solution().grayCode(0))
