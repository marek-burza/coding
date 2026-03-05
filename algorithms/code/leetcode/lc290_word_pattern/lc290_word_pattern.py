# https://leetcode.com/problems/word-pattern/

import unittest


class Solution:
    def check(self, first: str, second: str, mapping: dict[str, str]) -> bool:
        if first in mapping:
            if mapping[first] != second:
                return False
        else:
            mapping[first] = second
        return True

    def wordPattern(self, pattern: str, s: str) -> bool:
        words = s.split(' ')
        if len(pattern) != len(words):
            return False
        mapping_ps: dict[str, str] = {}
        mapping_sp: dict[str, str] = {}
        i = 0
        while i < len(words):
            key = pattern[i : i + 1]
            check_ps = self.check(key, words[i], mapping_ps)
            check_sp = self.check(words[i], key, mapping_sp)
            if not check_ps or not check_sp:
                return False
            i += 1
        return True


class TestCode(unittest.TestCase):
    def test_abba__dog_cat_cat_dog(self) -> None:
        assert Solution().wordPattern('abba', 'dog cat cat dog')

    def test_abba__dog_cat_cat_fish(self) -> None:
        assert not Solution().wordPattern('abba', 'dog cat cat fish')

    def test_aaaa__dog_cat_cat_dog(self) -> None:
        assert not Solution().wordPattern('aaaa', 'dog cat cat dog')

    def test_abba__dog_dog_dog_dog(self) -> None:
        assert not Solution().wordPattern('abba', 'dog dog dog dog')

    def test_ab_b_c(self) -> None:
        assert Solution().wordPattern('ab', 'b c')

    def test_mismatched(self) -> None:
        assert not Solution().wordPattern('ab', 'c')
