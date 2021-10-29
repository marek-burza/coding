#!/usr/bin/env python3
# https://leetcode.com/problems/excel-sheet-column-number/

import unittest


class Solution:
    def titleToNumber(self, columnTitle: str) -> int:
        s = columnTitle
        if s is None:
            return -1
        result = 0
        i = 0
        while i < len(s):
            result *= 26
            result += ord(s[i]) - ord('A') + 1
            i += 1
        return result


class TestCode(unittest.TestCase):
    def test_A(self):
        self.assertEqual(1, Solution().titleToNumber('A'))

    def test_B(self):
        self.assertEqual(2, Solution().titleToNumber('B'))

    def test_C(self):
        self.assertEqual(3, Solution().titleToNumber('C'))

    def test_Z(self):
        self.assertEqual(26, Solution().titleToNumber('Z'))

    def test_AA(self):
        self.assertEqual(27, Solution().titleToNumber('AA'))

    def test_AB(self):
        self.assertEqual(28, Solution().titleToNumber('AB'))

    def test_nothing(self):
        self.assertEqual(-1, Solution().titleToNumber(None))
