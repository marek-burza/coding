#!/usr/bin/env python3
# https://leetcode.com/problems/ransom-note/

import unittest


class Solution:
    def canConstruct(self, ransomNote: str, magazine: str) -> bool:
        counts = [0] * 256
        for letter in magazine:
            counts[ord(letter)] += 1
        for letter in ransomNote:
            counts[ord(letter)] -= 1
            if counts[ord(letter)] < 0:
                return False
        return True


class TestCode(unittest.TestCase):
    def test_example_a_b(self):
        self.assertFalse(Solution().canConstruct('a', 'b'))

    def test_example_aa_ab(self):
        self.assertFalse(Solution().canConstruct('aa', 'ab'))

    def test_example_aa_aab(self):
        self.assertTrue(Solution().canConstruct('aa', 'aab'))
