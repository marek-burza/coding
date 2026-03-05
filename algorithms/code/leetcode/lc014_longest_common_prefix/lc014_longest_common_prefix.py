# https://leetcode.com/problems/longest-common-prefix/

import unittest


class Solution:
    def longestCommonPrefix(self, strs: list[str]) -> str:
        if len(strs) == 0:
            return ''
        i = 0
        done = False
        while not done:
            for item in strs:
                if i >= len(item) or item[i] != strs[0][i]:
                    done = True
                    break
            i += 1
        return strs[0][0 : i - 1]


class TestCode(unittest.TestCase):
    def test_Ala_AlaMaKota(self) -> None:
        self.assertEqual(
            Solution().longestCommonPrefix(['Ala', 'Ala Ma Kota']), 'Ala'
        )

    def test_aa_a(self) -> None:
        self.assertEqual(Solution().longestCommonPrefix(['aa', 'a']), 'a')

    def test_ab_aa_coverage(self) -> None:
        self.assertEqual(Solution().longestCommonPrefix(['ab', 'aa']), 'a')

    def test_none(self) -> None:
        self.assertEqual(Solution().longestCommonPrefix([]), '')

    def test_empty_b(self) -> None:
        self.assertEqual(Solution().longestCommonPrefix(['', 'b']), '')

    def test_same(self) -> None:
        self.assertEqual(
            Solution().longestCommonPrefix(['same', 'same']), 'same'
        )
