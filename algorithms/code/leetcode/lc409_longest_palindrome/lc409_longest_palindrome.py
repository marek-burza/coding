#!/usr/bin/env python3
# https://leetcode.com/problems/longest-palindrome/

import unittest
from collections import Counter


class Solution:
    def longestPalindrome(self, s: str) -> int:
        longest = 0
        counted = Counter(s)
        odd = 0
        for count in counted.values():
            if count % 2 == 1:
                count -= 1
                odd = 1
            longest += count
        longest += odd
        return longest


class TestCode(unittest.TestCase):
    def test_example_1(self):
        self.assertEqual(7, Solution().longestPalindrome('abccccdd'))

    def test_example_2(self):
        self.assertEqual(1, Solution().longestPalindrome('a'))
