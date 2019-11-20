#!/usr/bin/env python3
# https://www.hackerrank.com/challenges/class-1-dealing-with-complex-numbers

import math


class Complex:
    def __init__(self, real, imaginary):
        self.real = real
        self.imaginary = imaginary

    def __add__(self, other):
        return Complex(self.real + other.real, self.imaginary + other.imaginary)

    def __sub__(self, other):
        return Complex(self.real - other.real, self.imaginary - other.imaginary)

    def __mul__(self, other):
        real = self.real * other.real - self.imaginary * other.imaginary
        imaginary = self.real * other.imaginary + self.imaginary * other.real
        return Complex(real, imaginary)

    def __truediv__(self, other):
        conjugate = Complex(other.real, -other.imaginary)
        denominator = other * conjugate
        result = self * conjugate
        result.real /= denominator.real
        result.imaginary /= denominator.real
        return result

    def __str__(self):
        real = '%.2f' % self.real
        imaginary = '%.2f' % self.imaginary
        return '%s%s%si' % (real, '+' if self.imaginary >= 0 else '', imaginary)

    def mod(self):
        value = math.sqrt(math.pow(self.real, 2) + math.pow(self.imaginary, 2))
        return Complex(value, 0)


def operate(c, d):
    return [c + d, c - d, c * d, c / d, c.mod(), d.mod()]


def main():
    c_real, c_imaginary = tuple(map(float, input().strip().split()))
    d_real, d_imaginary = tuple(map(float, input().strip().split()))
    c = Complex(c_real, c_imaginary)
    d = Complex(d_real, d_imaginary)
    for r in operate(c, d):
        print(r)

if __name__ == '__main__':
    main()
