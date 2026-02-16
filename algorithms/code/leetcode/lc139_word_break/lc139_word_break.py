# https://leetcode.com/problems/word-break/

import unittest


class Solution:
    # pylint: disable=R0913,R0917
    def __wordBreak(
        self,
        s: str,
        wordDict: set[str],
        at: int,
        length: int,
        checked: list[bool],
    ) -> bool:
        if checked[at]:
            return False
        limit = min(len(s), at + length)
        for i in range(at + 1, limit + 1):
            if s[at:i] in wordDict and (
                i == len(s)
                or self.__wordBreak(s, wordDict, i, length, checked)
            ):
                return True
        checked[at] = True
        return False

    def wordBreak(self, s: str, wordDict: list[str]) -> bool:
        length = max(len(word) for word in wordDict)
        checked = [False] * len(s)
        return self.__wordBreak(s, set(wordDict), 0, length, checked)


class TestCode(unittest.TestCase):
    def test_a__a(self) -> None:
        assert Solution().wordBreak('a', ['a'])

    def test_other(self) -> None:
        assert not Solution().wordBreak(
            'catsandog',
            ['cats', 'dog', 'sand', 'and', 'cat'],
        )

    def test_another(self) -> None:
        assert Solution().wordBreak('leetcode', ['leet', 'code'])
