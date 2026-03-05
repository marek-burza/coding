# https://leetcode.com/problems/valid-parentheses/

import unittest


class Solution:
    def check(self, s: str) -> bool:
        return len(s) == 0

    def isValid(self, s: str) -> bool:
        result = self.check(s)
        if result:
            return True
        stack = []
        lut = {')': '(', '}': '{', ']': '['}
        for character in s:
            if character in lut.values():
                stack.append(character)
            elif not stack or stack.pop() != lut[character]:
                return False
        return len(stack) == 0


class TestCode(unittest.TestCase):
    def test_garbage(self) -> None:
        assert not Solution().isValid('*')

    def test_RB(self) -> None:
        assert not Solution().isValid('(')

    def test_RE(self) -> None:
        assert not Solution().isValid(')')

    def test_RB_RE(self) -> None:
        assert Solution().isValid('()')

    def test_RB_RE_SB_SE_CB_CE(self) -> None:
        assert Solution().isValid('()[]{}')

    def test_RB_SB_RE_SE(self) -> None:
        assert not Solution().isValid('([)]')

    def test_RB_CB_RE_CE(self) -> None:
        assert not Solution().isValid('({)}')

    def test_SB_RB_SE_RE(self) -> None:
        assert not Solution().isValid('[(])')

    def test_CB_RB_CE_RE(self) -> None:
        assert not Solution().isValid('{(})')

    def test_RE__SE__CE(self) -> None:
        assert not Solution().isValid(')')
        assert not Solution().isValid(']')
        assert not Solution().isValid('}')

    def test_nothing(self) -> None:
        assert Solution().isValid('')
