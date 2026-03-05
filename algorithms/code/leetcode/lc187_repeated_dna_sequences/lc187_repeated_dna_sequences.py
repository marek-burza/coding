#!/usr/bin/env python3
# https://leetcode.com/problems/repeated-dna-sequences/

import unittest


class Solution:
    def __compress(self, nucleotide: str) -> int:
        return {'A': 0, 'C': 1, 'G': 2, 'T': 3}[nucleotide]

    def __encode(self, sequence: int, compressed: int) -> int:
        return (compressed << 18) | (sequence >> 2)

    def __decode(self, sequence: int) -> str:
        decoded = ''
        for _ in range(0, 10):
            nucleotide = sequence & 0x3
            decoded += {0: 'A', 1: 'C', 2: 'G', 3: 'T'}[nucleotide]
            sequence >>= 2
        return decoded

    def findRepeatedDnaSequences(self, s: str) -> list[str]:
        listed: list[str] = []
        if s is None or len(s) < 10:
            return listed
        seen: set[int] = set()
        now = 0
        for i in range(0, 9):
            now = self.__encode(now, self.__compress(s[i]))
        for i in range(9, len(s)):
            now = self.__encode(now, self.__compress(s[i]))
            if now | 0xFFF00000 in seen:
                continue
            if now in seen:
                seen.remove(now)
                seen.add(now | 0xFFF00000)
                continue
            seen.add(now)
        for sequence in seen:
            if sequence & 0x80000000 != 0:
                listed.append(self.__decode(sequence & 0xFFFFF))
        return listed


class TestCode(unittest.TestCase):
    def test_AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT(self) -> None:
        expected = ['AAAAACCCCC', 'CCCCCAAAAA']
        result = Solution().findRepeatedDnaSequences(
            'AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT'
        )
        result.sort()
        self.assertListEqual(expected, result)

    def test_nothing(self) -> None:
        result = Solution().findRepeatedDnaSequences('')
        self.assertListEqual([], result)

    def test_AAAAAAAAAAAAA(self) -> None:
        expected = ['AAAAAAAAAA']
        result = Solution().findRepeatedDnaSequences('AAAAAAAAAAAAA')
        result.sort()
        self.assertListEqual(expected, result)
