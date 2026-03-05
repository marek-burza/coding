# https://leetcode.com/problems/reverse-vowels-of-a-string/

import unittest


class Solution:
    def __isVowel(self, letter: str) -> bool:
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
    def test_example_1(self) -> None:
        assert Solution().reverseVowels('hello') == 'holle'

    def test_example_2(self) -> None:
        assert Solution().reverseVowels('leotcede') == 'leetcode'

    def test_oe(self) -> None:
        assert Solution().reverseVowels('OE') == 'EO'

    def test_zt(self) -> None:
        assert Solution().reverseVowels('zt') == 'zt'
