#!/usr/bin/env python3
# https://leetcode.com/problems/maximum-product-of-word-lengths/

import unittest

from typing import List


class Solution:
    def maxProduct(self, words: List[str]) -> int:
        signature = [0] * len(words)
        for i, _ in enumerate(words):
            for character in words[i]:
                signature[i] |= 1 << (ord(character) - ord('a'))
        maximum = 0
        for i in range(len(words) - 1):
            for j in range(i + 1, len(words)):
                if (signature[i] & signature[j]) == 0:
                    maximum = max(maximum, len(words[i]) * len(words[j]))
        return maximum


class TestCode(unittest.TestCase):
    def test_example_1(self):
        words = ['abcw', 'baz', 'foo', 'bar', 'xtfn', 'abcdef']
        self.assertEqual(16, Solution().maxProduct(words))

    def test_example_2(self):
        words = ['a', 'ab', 'abc', 'd', 'cd', 'bcd', 'abcd']
        self.assertEqual(4, Solution().maxProduct(words))

    def test_example_3(self):
        words = ['a', 'aa', 'aaa', 'aaaa']
        self.assertEqual(0, Solution().maxProduct(words))
