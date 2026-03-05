# https://leetcode.com/problems/super-pow/

import unittest


class Solution:
    __MODULO_1337 = 1337

    def __findPowerLoop(self, value: int) -> list[int]:
        modulos = []
        lut = [False] * self.__MODULO_1337
        modulo = value
        while not lut[modulo]:
            lut[modulo] = True
            modulos.append(modulo)
            modulo = (modulo * value) % self.__MODULO_1337
        return modulos

    def __modulo(self, dividend: list[int], divisor: int) -> int:
        length = len(dividend)
        modulo = 0
        for i in range(length):
            modulo = (modulo * 10 + dividend[i]) % divisor
        return modulo

    def superPow(self, a: int, b: list[int]) -> int:
        # Assume: a = (1337 * n + m) where 0 <= m < 1337
        # Then: a^b mod 1337 = (1337 * n + m)^n mod 1337 == m^b mod 1337
        # This multiplication will cycle through certain 'digits' of base 1337
        # You can search for the loop by iterating
        m = a % self.__MODULO_1337
        modulos = self.__findPowerLoop(m)
        # Get rid of loops from the power
        length = len(modulos)
        index = self.__modulo(b, length)
        # Look-up the power modulo
        index = (index - 1 + length) % length
        return modulos[index]


class TestCode(unittest.TestCase):
    def test_2_3(self) -> None:
        assert Solution().superPow(2, [3]) == 8

    def test_2_1_0(self) -> None:
        assert Solution().superPow(2, [1, 0]) == 1024
