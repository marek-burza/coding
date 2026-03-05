#!/usr/bin/env python3
# https://www.hackerrank.com/challenges/class-1-dealing-with-complex-numbers
# #python

from __future__ import annotations

import io
import math
import sys
import unittest


class Complex:
    def __init__(self, real: float, imaginary: float) -> None:
        self.real = real
        self.imaginary = imaginary

    def __add__(self, other: Complex) -> Complex:
        real = self.real + other.real
        imaginary = self.imaginary + other.imaginary
        return Complex(real, imaginary)

    def __sub__(self, other: Complex) -> Complex:
        real = self.real - other.real
        imaginary = self.imaginary - other.imaginary
        return Complex(real, imaginary)

    def __mul__(self, other: Complex) -> Complex:
        real = self.real * other.real - self.imaginary * other.imaginary
        imaginary = self.real * other.imaginary + self.imaginary * other.real
        return Complex(real, imaginary)

    def __truediv__(self, other: Complex) -> Complex:
        conjugate = Complex(other.real, -other.imaginary)
        denominator = other * conjugate
        result = self * conjugate
        result.real /= denominator.real
        result.imaginary /= denominator.real
        return result

    def __str__(self) -> str:
        real = f'{self.real:.2f}'
        middle = '+' if self.imaginary >= 0 else ''
        imaginary = f'{self.imaginary:.2f}'
        return f'{real}{middle}{imaginary}i'

    def mod(self) -> Complex:
        value = math.sqrt(math.pow(self.real, 2) + math.pow(self.imaginary, 2))
        return Complex(value, 0)


def operate(c: Complex, d: Complex) -> list[Complex]:
    return [c + d, c - d, c * d, c / d, c.mod(), d.mod()]


def main() -> None:
    c_real, c_imaginary = tuple(map(float, input().strip().split()))
    d_real, d_imaginary = tuple(map(float, input().strip().split()))
    c = Complex(c_real, c_imaginary)
    d = Complex(d_real, d_imaginary)
    for r in operate(c, d):
        print(r)


if __name__ == '__main__':  # pragma: no cover
    main()


class TestCode(unittest.TestCase):
    def generalized_test(self, which: str) -> None:
        with (
            open(
                __file__.replace('.py', f'.{which}.out'), 'r', encoding='utf-8'
            ) as expected,
            open(
                __file__.replace('.py', f'.{which}.in'), 'r', encoding='utf-8'
            ) as sys.stdin,
            io.StringIO() as sys.stdout,
        ):
            main()
            self.assertEqual(sys.stdout.getvalue(), expected.read())

    def test_0(self) -> None:
        self.generalized_test('0')
