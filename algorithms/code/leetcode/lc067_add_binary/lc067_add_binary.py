#!/usr/bin/env python3
# https://leetcode.com/problems/add-binary/

import unittest


class Solution:
    def addBinary(self, a: str, b: str) -> str:
        ar = a[::-1]
        br = b[::-1]
        result = ''
        carry = 0
        i = 0
        while i < len(ar) or i < len(br):
            summed = carry
            summed += ord(ar[i]) - ord('0') if i < len(ar) else 0
            summed += ord(br[i]) - ord('0') if i < len(br) else 0
            carry = summed >> 1
            result += '0' if ((summed & 1) == 0) else '1'
            i += 1
        if carry == 1:
            result += '1'
        return result[::-1]


class TestCode(unittest.TestCase):
    def test_example_1(self):
        self.assertEqual('100', Solution().addBinary('11', '1'))

    def test_example_2(self):
        self.assertEqual('10101', Solution().addBinary('1010', '1011'))

    def test_example_1_reversed(self):
        self.assertEqual('100', Solution().addBinary('1', '11'))

    def test_no_carry(self):
        self.assertEqual('1', Solution().addBinary('1', '0'))
