#!/usr/bin/env python3
# https://leetcode.com/problems/excel-sheet-column-title/

import unittest


class Solution:
    def convertToTitle(self, columnNumber: int) -> str:
        n = columnNumber
        buffer = ''
        condition = True
        while condition:
            n -= 1
            digit = chr(ord('A') + (n % 26))
            buffer += digit
            n -= n % 26
            n //= 26
            condition = n > 0
        return buffer[::-1]


class TestCode(unittest.TestCase):
    def test_1(self):
        self.assertEqual('A', Solution().convertToTitle(1))

    def test_2(self):
        self.assertEqual('B', Solution().convertToTitle(2))

    def test_3(self):
        self.assertEqual('C', Solution().convertToTitle(3))

    def test_26(self):
        self.assertEqual('Z', Solution().convertToTitle(26))

    def test_27(self):
        self.assertEqual('AA', Solution().convertToTitle(27))

    def test_28(self):
        self.assertEqual('AB', Solution().convertToTitle(28))
