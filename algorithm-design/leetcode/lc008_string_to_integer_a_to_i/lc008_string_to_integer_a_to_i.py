#!/usr/bin/env python3
# https://leetcode.com/problems/string-to-integer-atoi/

import unittest


class Solution:
    def myAtoi(self, s: str) -> int:
        result = 0
        sign = 0
        index = 0
        while index < len(s):
            if not s[index].isspace():
                break
            index += 1
        if index < len(s):
            sign = -1 if s[index] == '-' else 1
            if s[index] in ['+', '-']:
                index += 1
        while index < len(s) and s[index].isdigit():
            result *= 10
            result += ord(s[index]) - ord('0')
            if result > 2**31-1:
                break
            index += 1
        result *= sign
        return max(-2**31, min(2**31-1, result))


class TestCode(unittest.TestCase):
    def test_MinusMinus3241(self):
        self.assertEqual(Solution().myAtoi('--3241'), 0)

    def test_PlusPlus3241(self):
        self.assertEqual(Solution().myAtoi('++3241'), 0)

    def test_MinusPlus3241(self):
        self.assertEqual(Solution().myAtoi('-+3241'), 0)

    def test_Minus3241(self):
        self.assertEqual(Solution().myAtoi('-3241'), -3241)

    def test_SpaceMinusPlus3241A(self):
        self.assertEqual(Solution().myAtoi(' -3241a'), -3241)

    def test_9223372036854775809(self):
        self.assertEqual(Solution().myAtoi('9223372036854775809'), 2**31-1)

    def test_Minus9223372036854775809(self):
        self.assertEqual(Solution().myAtoi('-9223372036854775809'), -2**31)

    def test_Nothing(self):
        self.assertEqual(Solution().myAtoi('nothing'), 0)
