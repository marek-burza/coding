#!/usr/bin/env python3
# https://leetcode.com/problems/integer-to-roman/

import unittest


class Solution:
    def intToRoman(self, num: int) -> str:
        digits = [
            'M',
            'CM',
            'D',
            'CD',
            'C',
            'XC',
            'L',
            'XL',
            'X',
            'IX',
            'V',
            'IV',
            'I',
        ]
        weights = [1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1]
        roman = ''
        for i, digits_i in enumerate(digits):
            multiple = num // weights[i]
            roman += digits_i * multiple
            num -= multiple * weights[i]
        return roman


class TestCode(unittest.TestCase):
    def test_1234(self):
        self.assertEqual(Solution().intToRoman(1234), 'MCCXXXIV')

    def test_9(self):
        self.assertEqual(Solution().intToRoman(9), 'IX')
