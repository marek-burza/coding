#!/usr/bin/env python3
# https://leetcode.com/problems/integer-to-english-words/

import unittest


class Solution:
    __MAGNITUDE = [
        '', ' Thousand', ' Million', ' Billion', ' Trillion', ' Quadrillion',
        ' Quintillion', ' Sextillion', ' Septillion', ' Octillion',
        ' Nonillion']
    __TENS = [
        '', 'Ten', 'Twenty', 'Thirty', 'Forty', 'Fifty', 'Sixty', 'Seventy',
        'Eighty', 'Ninety']
    __DIGITS = [
        '', 'One', 'Two', 'Three', 'Four', 'Five', 'Six', 'Seven', 'Eight',
        'Nine', 'Ten', 'Eleven', 'Twelve', 'Thirteen', 'Fourteen', 'Fifteen',
        'Sixteen', 'Seventeen', 'Eighteen', 'Nineteen']

    def __tripleToWords(self, i):
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

    def numberToWords(self, i):
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
    def test_123(self):
        self.assertEqual(
            'One Hundred Twenty Three',
            Solution().numberToWords(123))

    def test_12345(self):
        self.assertEqual(
            'Twelve Thousand Three Hundred Forty Five',
            Solution().numberToWords(12345))

    def test_1234567(self):
        self.assertEqual(
            'One Million Two Hundred Thirty Four Thousand Five Hundred Sixty Seven',  # noqa
            Solution().numberToWords(1234567))

    def test_91(self):
        self.assertEqual('Ninety One', Solution().numberToWords(91))

    def test_19(self):
        self.assertEqual('Nineteen', Solution().numberToWords(19))

    def test_100(self):
        self.assertEqual('One Hundred', Solution().numberToWords(100))

    def test_0(self):
        self.assertEqual('Zero', Solution().numberToWords(0))

    def test_1000(self):
        self.assertEqual('One Thousand', Solution().numberToWords(1000))

    def test_20(self):
        self.assertEqual('Twenty', Solution().numberToWords(20))
