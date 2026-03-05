#!/usr/bin/env python3
# https://leetcode.com/problems/generate-parentheses/

import unittest


class Solution:
    def generate(
        self, prefix: str, standing: int, n: int, found: list[str]
    ) -> None:
        if n == 0 and standing == 0:
            found.append(prefix)
            return
        # open
        if n > 0:
            self.generate(prefix + '(', standing + 1, n - 1, found)
        # close
        if standing > 0:
            self.generate(prefix + ')', standing - 1, n, found)

    def generateParenthesis(self, n: int) -> list[str]:
        if n == 0:
            return []
        found: list[str] = []
        self.generate('(', 1, n - 1, found)
        return found


class TestCode(unittest.TestCase):
    def generalized(self, expected: list[str], n: int) -> None:
        result = Solution().generateParenthesis(n)
        result = sorted(result)
        expected = sorted(expected)
        for i in range(max(len(result), len(expected))):
            self.assertEqual(result[i], expected[i])

    def test_0(self) -> None:
        expected: list[str] = []
        self.generalized(expected, 0)

    def test_1(self) -> None:
        expected = ['()']
        self.generalized(expected, 1)

    def test_2(self) -> None:
        expected = ['()()', '(())']
        self.generalized(expected, 2)

    def test_3(self) -> None:
        expected = ['((()))', '(()())', '(())()', '()(())', '()()()']
        self.generalized(expected, 3)

    def test_4(self) -> None:
        expected = [
            '(((())))',
            '((()()))',
            '((())())',
            '((()))()',
            '(()(()))',
            '(()()())',
            '(()())()',
            '(())(())',
            '(())()()',
            '()((()))',
            '()(()())',
            '()(())()',
            '()()(())',
            '()()()()',
        ]
        self.generalized(expected, 4)
