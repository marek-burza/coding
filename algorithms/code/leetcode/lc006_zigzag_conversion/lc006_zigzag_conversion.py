#!/usr/bin/env python3
# https://leetcode.com/problems/zigzag-conversion/

import unittest


class Solution:
    def convert(self, s: str, numRows: int) -> str:
        if s is None or numRows < 1:
            return ''
        buffer = ''
        n = len(s)
        scan = (numRows - 1) * 2
        scan = 1 if scan == 0 else scan
        for row in range(numRows):
            for i in range(row, n, scan):
                buffer += s[i]
                if 0 < row < numRows - 1:
                    offset = (numRows - 1 - row) * 2
                    if i + offset >= n:
                        break
                    buffer += s[i + offset]
        return buffer


class TestCode(unittest.TestCase):
    def test_1(self):
        self.assertEqual(
            Solution().convert('PAYPALISHIRING', 3), 'PAHNAPLSIIGYIR'
        )

    def test_2(self):
        self.assertEqual(
            Solution().convert('PAYPALISHIRING', 4), 'PINALSIGYAHRPI'
        )

    def test_3(self):
        self.assertEqual(Solution().convert('A', 1), 'A')

    def test_abcd(self):
        self.assertEqual(Solution().convert('ABCD', 3), 'ABDC')

    def test_abc(self):
        self.assertEqual(Solution().convert('ABC', 2), 'ACB')

    def test_nothing(self):
        self.assertEqual(Solution().convert(None, 2), '')

    def test_zero(self):
        self.assertEqual(Solution().convert('A', 0), '')
