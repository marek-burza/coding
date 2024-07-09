#!/usr/bin/env python3
# https://leetcode.com/problems/valid-parentheses/

import unittest


class Solution:
    def check(self, s):
        if s is None:
            return False
        if len(s) == 0:
            return True
        return None

    def isValid(self, s: str) -> bool:
        result = self.check(s)
        if result is not None:
            return result
        stack = []
        lut = {')': '(', '}': '{', ']': '['}
        for character in s:
            if character in lut.values():
                stack.append(character)
            elif not stack or stack.pop() != lut[character]:
                return False
        return len(stack) == 0


class TestCode(unittest.TestCase):
    def test_garbage(self):
        self.assertEqual(Solution().isValid('*'), False)

    def test_RB(self):
        self.assertEqual(Solution().isValid('('), False)

    def test_RE(self):
        self.assertEqual(Solution().isValid(')'), False)

    def test_RB_RE(self):
        self.assertEqual(Solution().isValid('()'), True)

    def test_RB_RE_SB_SE_CB_CE(self):
        self.assertEqual(Solution().isValid('()[]{}'), True)

    def test_RB_SB_RE_SE(self):
        self.assertEqual(Solution().isValid('([)]'), False)

    def test_RB_CB_RE_CE(self):
        self.assertEqual(Solution().isValid('({)}'), False)

    def test_SB_RB_SE_RE(self):
        self.assertEqual(Solution().isValid('[(])'), False)

    def test_CB_RB_CE_RE(self):
        self.assertEqual(Solution().isValid('{(})'), False)

    def test_RE__SE__CE(self):
        self.assertEqual(Solution().isValid(')'), False)
        self.assertEqual(Solution().isValid(']'), False)
        self.assertEqual(Solution().isValid('}'), False)

    def test_nothing(self):
        self.assertEqual(Solution().isValid(None), False)
        self.assertEqual(Solution().isValid(''), True)
