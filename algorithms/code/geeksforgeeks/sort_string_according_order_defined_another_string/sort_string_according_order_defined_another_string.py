# https://www.geeksforgeeks.org/sort-string-according-order-defined-another-string/
# #google

import unittest


class Solution:
    def sortByPattern(self, string: str, pat: str) -> str:
        # Build a decorating hash table
        lut = {}
        for i, key in enumerate(pat):
            lut[key] = i
        # Reorder
        chars = sorted(string, key=lambda character: lut.get(character, -1))
        return ''.join(chars)


class TestCode(unittest.TestCase):
    def test_example_1(self) -> None:
        assert Solution().sortByPattern('abc', 'bca') == 'bca'

    def test_example_2(self) -> None:
        assert Solution().sortByPattern('abcabcabc', 'bxyzca') == 'bbbcccaaa'

    def test_example_3(self) -> None:
        assert (
            Solution().sortByPattern('jcdokai', 'wcyuogmlrdfphitxjakqvzbnes')
            == 'codijak'
        )
