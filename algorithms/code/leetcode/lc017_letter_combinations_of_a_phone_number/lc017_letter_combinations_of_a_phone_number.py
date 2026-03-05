# https://leetcode.com/problems/letter-combinations-of-a-phone-number/

import unittest


class Solution:
    def letterCombinations(self, digits: str) -> list[str]:
        if digits is None or len(digits) == 0:
            return []
        mapping = [
            ' ',
            '1',
            'abc',
            'def',
            'ghi',
            'jkl',
            'mno',
            'pqrs',
            'tuv',
            'wxyz',
        ]
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
    def test_empty(self) -> None:
        result = Solution().letterCombinations('')
        assert len(result) == 0

    def test_example(self) -> None:
        expected = ['ad', 'ae', 'af', 'bd', 'be', 'bf', 'cd', 'ce', 'cf']
        expected = sorted(expected)
        result = Solution().letterCombinations('23')
        result = sorted(result)
        assert result == expected
