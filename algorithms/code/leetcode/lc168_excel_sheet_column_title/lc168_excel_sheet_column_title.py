# https://leetcode.com/problems/excel-sheet-column-title/

import unittest


class Solution:
    def convertToTitle(self, columnNumber: int) -> str:
        n = columnNumber
        buffer = ''
        condition = True
        while condition:
            n -= 1
            digit = chr(ord('A') + (n % 26))
            buffer += digit
            n -= n % 26
            n //= 26
            condition = n > 0
        return buffer[::-1]


class TestCode(unittest.TestCase):
    def test_1(self) -> None:
        assert Solution().convertToTitle(1) == 'A'

    def test_2(self) -> None:
        assert Solution().convertToTitle(2) == 'B'

    def test_3(self) -> None:
        assert Solution().convertToTitle(3) == 'C'

    def test_26(self) -> None:
        assert Solution().convertToTitle(26) == 'Z'

    def test_27(self) -> None:
        assert Solution().convertToTitle(27) == 'AA'

    def test_28(self) -> None:
        assert Solution().convertToTitle(28) == 'AB'
