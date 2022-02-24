#!/usr/bin/env python3
# https://leetcode.com/problems/isomorphic-strings/

import unittest

from typing import Dict


class Solution:
    def isIsomorphic(self, s: str, t: str) -> bool:
        mapped: Dict[str, str] = {}
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
    def test_aa__ab(self):
        self.assertFalse(Solution().isIsomorphic('aa', 'ab'))

    def test_egg__add(self):
        self.assertTrue(Solution().isIsomorphic('egg', 'add'))

    def test_ac__bb(self):
        self.assertFalse(Solution().isIsomorphic('ac', 'bb'))
