#!/usr/bin/env python3
# https://leetcode.com/problems/letter-combinations-of-a-phone-number/

import unittest

from typing import List


class Solution:
    def letterCombinations(self, digits: str) -> List[str]:
        if digits is None or len(digits) == 0:
            return []
        mapping = [
            ' ',
            '1', 'abc', 'def',
            'ghi', 'jkl', 'mno',
            'pqrs', 'tuv', 'wxyz']
        mapped = []
        mapped.append('')
        for digit in digits:
            index = ord(digit) - ord('0')
            longer = []
            for prefix in mapped:
                for suffix in mapping[index]:
                    longer.append(prefix + suffix)
            mapped = longer
        return mapped


class TestCode(unittest.TestCase):
    def test_empty(self):
        result = Solution().letterCombinations('')
        self.assertEqual(len(result), 0)

    def test_example(self):
        expected = ['ad', 'ae', 'af', 'bd', 'be', 'bf', 'cd', 'ce', 'cf']
        expected = sorted(expected)
        result = Solution().letterCombinations('23')
        result = sorted(result)
        self.assertEqual(result, expected)
