# https://leetcode.com/problems/maximum-product-of-word-lengths/

import unittest


class Solution:
    def maxProduct(self, words: list[str]) -> int:
        signature = [0] * len(words)
        for i, _ in enumerate(words):
            for character in words[i]:
                signature[i] |= 1 << (ord(character) - ord('a'))
        maximum = 0
        for i in range(len(words) - 1):
            for j in range(i + 1, len(words)):
                if (signature[i] & signature[j]) == 0:
                    maximum = max(maximum, len(words[i]) * len(words[j]))
        return maximum


class TestCode(unittest.TestCase):
    def test_example_1(self) -> None:
        words = ['abcw', 'baz', 'foo', 'bar', 'xtfn', 'abcdef']
        assert Solution().maxProduct(words) == 16

    def test_example_2(self) -> None:
        words = ['a', 'ab', 'abc', 'd', 'cd', 'bcd', 'abcd']
        assert Solution().maxProduct(words) == 4

    def test_example_3(self) -> None:
        words = ['a', 'aa', 'aaa', 'aaaa']
        assert Solution().maxProduct(words) == 0
