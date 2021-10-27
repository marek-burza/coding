#!/usr/bin/env python3
# https://leetcode.com/problems/shortest-palindrome/

import unittest


class Solution:
    def shortestPalindrome(self, s: str) -> str:
        if s is None or len(s) == 0:
            return s
        A = s + s[::-1]
        cont = [0] * len(A)
        for i in range(1, len(A)):
            index = cont[i - 1]
            while index > 0 and A[index] != A[i]:
                index = cont[index - 1]
            cont[i] = index + (1 if A[index] == A[i] else 0)
        return s[cont[- 1]:][::-1] + s


class TestCode(unittest.TestCase):
    def test_aacecaaa(self):
        self.assertEqual(
            'aaacecaaa', Solution().shortestPalindrome('aacecaaa'))

    def test_abcd(self):
        self.assertEqual('dcbabcd', Solution().shortestPalindrome('abcd'))

    def test_nothing(self):
        self.assertIsNone(Solution().shortestPalindrome(None))
        self.assertEqual('', Solution().shortestPalindrome(''))
