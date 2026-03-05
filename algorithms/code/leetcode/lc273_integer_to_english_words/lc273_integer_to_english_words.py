# https://leetcode.com/problems/integer-to-english-words/

import unittest


class Solution:
    __MAGNITUDE = [
        '',
        ' Thousand',
        ' Million',
        ' Billion',
        ' Trillion',
        ' Quadrillion',
        ' Quintillion',
        ' Sextillion',
        ' Septillion',
        ' Octillion',
        ' Nonillion',
    ]
    __TENS = [
        '',
        'Ten',
        'Twenty',
        'Thirty',
        'Forty',
        'Fifty',
        'Sixty',
        'Seventy',
        'Eighty',
        'Ninety',
    ]
    __DIGITS = [
        '',
        'One',
        'Two',
        'Three',
        'Four',
        'Five',
        'Six',
        'Seven',
        'Eight',
        'Nine',
        'Ten',
        'Eleven',
        'Twelve',
        'Thirteen',
        'Fourteen',
        'Fifteen',
        'Sixteen',
        'Seventeen',
        'Eighteen',
        'Nineteen',
    ]

    def __tripleToWords(self, i: int) -> str:
        result = ''
        if i >= 100:
            result += self.__DIGITS[i // 100]
            result += ' Hundred'
            i %= 100
        if i != 0 and len(result) != 0:
            result += ' '
        if i <= 19:
            result += self.__DIGITS[i]
        else:
            result += self.__TENS[i // 10]
            i %= 10
            if i != 0:
                result += ' '
                result += self.__DIGITS[i]
        return str(result)

    def numberToWords(self, i: int) -> str:
        if i == 0:
            return 'Zero'
        result = ''
        position = 0
        while i != 0:
            vocalization = self.__tripleToWords(i % 1000)
            if len(vocalization) != 0:
                if len(result) != 0:
                    result = ' ' + result
                result = self.__MAGNITUDE[position] + result
                result = vocalization + result
            i //= 1000
            position += 1
        return str(result)


class TestCode(unittest.TestCase):
    def test_123(self) -> None:
        assert Solution().numberToWords(123) == 'One Hundred Twenty Three'

    def test_12345(self) -> None:
        assert (
            Solution().numberToWords(12345)
            == 'Twelve Thousand Three Hundred Forty Five'
        )

    def test_1234567(self) -> None:
        expected = 'One Million Two Hundred Thirty Four Thousand Five Hundred Sixty Seven'  # noqa
        assert Solution().numberToWords(1234567) == expected

    def test_91(self) -> None:
        assert Solution().numberToWords(91) == 'Ninety One'

    def test_19(self) -> None:
        assert Solution().numberToWords(19) == 'Nineteen'

    def test_100(self) -> None:
        assert Solution().numberToWords(100) == 'One Hundred'

    def test_0(self) -> None:
        assert Solution().numberToWords(0) == 'Zero'

    def test_1000(self) -> None:
        assert Solution().numberToWords(1000) == 'One Thousand'

    def test_20(self) -> None:
        assert Solution().numberToWords(20) == 'Twenty'
