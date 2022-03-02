#!/usr/bin/env python3
# https://leetcode.com/problems/longest-palindromic-substring/
# See also: Manacher algorithm

import unittest


class Solution:
    def longestPalindrome(self, s: str) -> str:
        longest = ''
        index = 0
        while index < len(s):
            # Find head & tail
            head = tail = index
            while tail + 1 < len(s):
                if s[head] != s[tail + 1]:
                    break
                tail += 1
            index += 1 + tail - head
            # Expand
            while head - 1 >= 0 and tail + 1 < len(s):
                if s[head - 1] != s[tail + 1]:
                    break
                head -= 1
                tail += 1
            # Check
            if len(longest) < 1 + tail - head:
                longest = s[head : tail + 1]
        return longest


class TestCode(unittest.TestCase):
    def test_1(self):
        self.assertEqual(Solution().longestPalindrome('babad'), 'bab')

    def test_2(self):
        self.assertEqual(Solution().longestPalindrome('cbbd'), 'bb')

    def test_3(self):
        self.assertEqual(Solution().longestPalindrome('a'), 'a')

    def test_4(self):
        self.assertEqual(Solution().longestPalindrome('ac'), 'a')

    def test_bb(self):
        self.assertEqual(Solution().longestPalindrome('bb'), 'bb')

    def test_longer(self):
        self.assertEqual(
            # pylint: disable=C0301
            Solution().longestPalindrome(
                'civilwartestingwhetherthatnaptionoranynartionsoconceivedandsodedicatedcanlongendureWeareqmetonagreatbattlefiemldoftzhatwarWehavecometodedicpateaportionofthatfieldasafinalrestingplaceforthosewhoheregavetheirlivesthatthatnationmightliveItisaltogetherfangandproperthatweshoulddothisButinalargersensewecannotdedicatewecannotconsecratewecannothallowthisgroundThebravelmenlivinganddeadwhostruggledherehaveconsecrateditfaraboveourpoorponwertoaddordetractTgheworldadswfilllittlenotlenorlongrememberwhatwesayherebutitcanneverforgetwhattheydidhereItisforusthelivingrathertobededicatedheretotheulnfinishedworkwhichtheywhofoughtherehavethusfarsonoblyadvancedItisratherforustobeherededicatedtothegreattdafskremainingbeforeusthatfromthesehonoreddeadwetakeincreaseddevotiontothatcauseforwhichtheygavethelastpfullmeasureofdevotionthatweherehighlyresolvethatthesedeadshallnothavediedinvainthatthisnationunsderGodshallhaveanewbirthoffreedomandthatgovernmentofthepeoplebythepeopleforthepeopleshallnotperishfromtheearth'  # noqa
            ),
            'ranynar',
        )
