# https://leetcode.com/problems/excel-sheet-column-number/

import unittest


class Solution:
    def titleToNumber(self, columnTitle: str) -> int:
        s = columnTitle
        if not s:
            return -1
        result = 0
        i = 0
        while i < len(s):
            result *= 26
            result += ord(s[i]) - ord('A') + 1
            i += 1
        return result


class TestCode(unittest.TestCase):
    def test_A(self) -> None:
        assert Solution().titleToNumber('A') == 1

    def test_B(self) -> None:
        assert Solution().titleToNumber('B') == 2

    def test_C(self) -> None:
        assert Solution().titleToNumber('C') == 3

    def test_Z(self) -> None:
        assert Solution().titleToNumber('Z') == 26

    def test_AA(self) -> None:
        assert Solution().titleToNumber('AA') == 27

    def test_AB(self) -> None:
        assert Solution().titleToNumber('AB') == 28

    def test_nothing(self) -> None:
        assert Solution().titleToNumber('') == -1
