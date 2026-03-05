# https://leetcode.com/problems/count-numbers-with-unique-digits/

import unittest


class Solution:
    def __count(self, prefix: str, n: int) -> int:
        if len(prefix) == n:
            return 1
        summed = 1
        digits = ['0', '1', '2', '3', '4', '5', '6', '7', '8', '9']
        first = 1 if len(prefix) == 0 else 0
        for i in range(first, len(digits)):
            if digits[i] not in prefix:
                summed += self.__count(prefix + digits[i], n)
        return summed

    def countNumbersWithUniqueDigits(self, n: int) -> int:
        return self.__count('', n)


class TestCode(unittest.TestCase):
    def test_example(self) -> None:
        assert Solution().countNumbersWithUniqueDigits(2) == 91

    def test_0(self) -> None:
        assert Solution().countNumbersWithUniqueDigits(0) == 1

    def test_1(self) -> None:
        assert Solution().countNumbersWithUniqueDigits(1) == 10
