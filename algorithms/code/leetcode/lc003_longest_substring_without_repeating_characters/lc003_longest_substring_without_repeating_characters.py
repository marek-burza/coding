#!/usr/bin/env python3
# https://leetcode.com/problems/longest-substring-without-repeating-characters/

import unittest

from typing import Set


class Solution:
    def lengthOfLongestSubstring(self, s: str) -> int:
        seen: Set[str] = set()
        longest = count = 0
        for i, found in enumerate(s):
            while count > 0 and found in seen:
                seen.remove(s[i - count])
                count -= 1
            count += 1
            seen.add(found)
            longest = max(longest, count)
        return longest


class TestCode(unittest.TestCase):
    def test_abcabcbb(self):
        self.assertEqual(Solution().lengthOfLongestSubstring('abcabcbb'), 3)

    def test_bbbbb(self):
        self.assertEqual(Solution().lengthOfLongestSubstring('bbbbb'), 1)

    def test_dvdf(self):
        self.assertEqual(Solution().lengthOfLongestSubstring('dvdf'), 3)
