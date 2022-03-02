#!/usr/bin/env python3
# https://leetcode.com/problems/bulls-and-cows/

import collections
import unittest


class Solution:
    def getHint(self, secret: str, guess: str) -> str:
        countKnown: collections.Counter = collections.Counter()
        countAsked: collections.Counter = collections.Counter()
        bulls = 0
        cows = 0
        i = 0
        while i < min(len(secret), len(guess)):
            # Count characters of each type
            known = secret[i]
            asked = guess[i]
            countKnown[known] += 1
            countAsked[asked] += 1
            # Check for a bull
            if known == asked:
                bulls += 1
            i += 1
        # Count the cows
        for asked in countAsked:
            if asked in countKnown:
                cows += min(countKnown[asked], countAsked[asked])
        # Remove the bulls from the cows
        cows -= bulls
        return '' + str(bulls) + 'A' + str(cows) + 'B'
        # It would have been faster to have one lookup table
        # and update cows up or down accordingly


class TestCode(unittest.TestCase):
    def test_1807__7810(self):
        self.assertEqual('1A3B', Solution().getHint('1807', '7810'))

    def test_1123__0111(self):
        self.assertEqual('1A1B', Solution().getHint('1123', '0111'))

    def test1122and2211(self):
        self.assertEqual('0A4B', Solution().getHint('1122', '2211'))

    def test11and10(self):
        self.assertEqual('1A0B', Solution().getHint('11', '10'))
