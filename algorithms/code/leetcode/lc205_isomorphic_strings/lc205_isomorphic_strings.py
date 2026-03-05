# https://leetcode.com/problems/isomorphic-strings/

import unittest


class Solution:
    def isIsomorphic(self, s: str, t: str) -> bool:
        mapped: dict[str, str] = {}
        for i, _ in enumerate(s):
            source = s[i]
            target = t[i]
            if source in mapped:
                if target != mapped[source]:
                    return False
            else:
                if target in mapped.values():
                    return False
                mapped[source] = target
        return True


class TestCode(unittest.TestCase):
    def test_aa__ab(self) -> None:
        assert not Solution().isIsomorphic('aa', 'ab')

    def test_egg__add(self) -> None:
        assert Solution().isIsomorphic('egg', 'add')

    def test_ac__bb(self) -> None:
        assert not Solution().isIsomorphic('ac', 'bb')
