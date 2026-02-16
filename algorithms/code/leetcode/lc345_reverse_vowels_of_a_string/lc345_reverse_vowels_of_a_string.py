#!/usr/bin/env python3
# https://leetcode.com/problems/reverse-vowels-of-a-string/

import unittest


class Solution:
    def __isVowel(self, letter):
        vowels = ['a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U']
        return any(vowel == letter for vowel in vowels)

    def reverseVowels(self, s: str) -> str:
        text = list(s)
        a = 0
        z = len(text) - 1
        while a < z:
            while a < len(text) and not self.__isVowel(text[a]):
                a += 1
            while z >= 0 and not self.__isVowel(text[z]):
                z -= 1
            if a < z:
                exchange = text[a]
                text[a] = text[z]
                text[z] = exchange
                a += 1
                z -= 1
        return ''.join(text)


class TestCode(unittest.TestCase):
    def test_example_1(self):
        self.assertEqual('holle', Solution().reverseVowels('hello'))

    def test_example_2(self):
        self.assertEqual('leetcode', Solution().reverseVowels('leotcede'))

    def test_oe(self):
        self.assertEqual('EO', Solution().reverseVowels('OE'))

    def test_zt(self):
        self.assertEqual('zt', Solution().reverseVowels('zt'))
