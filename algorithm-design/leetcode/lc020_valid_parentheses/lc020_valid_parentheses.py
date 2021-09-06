#!/usr/bin/env python3
# https://leetcode.com/problems/valid-parentheses/

import unittest


class Solution:
    def isValid(self, s: str) -> bool:
        if s is None:
            return False
        if len(s) == 0:
            return True
        stack = []
        for character in s:
            if character in ['(', '{', '[']:
                stack.append(character)
            elif character == ')':
                if len(stack) == 0 or stack[-1] != '(':
                    return False
                stack.pop()
            elif character == '}':
                if len(stack) == 0 or stack[-1] != '{':
                    return False
                stack.pop()
            elif character == ']':
                if len(stack) == 0 or stack[-1] != '[':
                    return False
                stack.pop()
        return len(stack) == 0


class TestCode(unittest.TestCase):
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

    def test_RE__SE__CE(self):
        self.assertEqual(Solution().isValid(')'), False)
        self.assertEqual(Solution().isValid(']'), False)
        self.assertEqual(Solution().isValid('}'), False)

    def test_nothing(self):
        self.assertEqual(Solution().isValid(None), False)
        self.assertEqual(Solution().isValid(''), True)
