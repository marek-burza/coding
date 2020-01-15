#!/usr/bin/env python3
# https://www.hackerrank.com/challenges/class-2-find-the-torsional-angle

import io
import math
import sys
import unittest


class Vector:
    def __init__(self, x, y, z):
        self.x = x
        self.y = y
        self.z = z

    def subtract(self, other):
        x = self.x - other.x
        y = self.y - other.y
        z = self.z - other.z
        return Vector(x, y, z)

    def dot_product(self, other):
        return self.x * other.x + self.y * other.y + self.z * other.z

    def cross_product(self, other):
        zero = Vector(0, 0, 0)
        x = self.y * other.z - self.z * other.y
        y = self.z * other.x - self.x * other.z
        z = self.x * other.y - self.y * other.x
        return zero.subtract(Vector(x, y, z))

    def value(self):
        xx = math.pow(self.x, 2)
        yy = math.pow(self.y, 2)
        zz = math.pow(self.z, 2)
        return math.sqrt(xx + yy + zz)


def torsional_angle(a, b, c, d):
    ab = a.subtract(b)
    bc = b.subtract(c)
    cd = c.subtract(d)
    x = ab.cross_product(bc)
    y = bc.cross_product(cd)
    cosine = x.dot_product(y) / (x.value() * y.value())
    return math.degrees(math.acos(cosine))


def main():
    a = Vector(*tuple(map(float, input().strip().split())))
    b = Vector(*tuple(map(float, input().strip().split())))
    c = Vector(*tuple(map(float, input().strip().split())))
    d = Vector(*tuple(map(float, input().strip().split())))
    print('%.2f' % torsional_angle(a, b, c, d))


if __name__ == '__main__':
    main()


class TestCode(unittest.TestCase):
    def generalized_test(self, which):
        sys.stdin = open(__file__.replace('.py', f'.{which}.in'), 'r')
        sys.stdout = io.StringIO()
        expected = open(__file__.replace('.py', f'.{which}.out'), 'r')
        main()
        self.assertEqual(sys.stdout.getvalue(), expected.read())
        for handle in [sys.stdin, sys.stdout, expected]:
            handle.close()

    def test_0(self):
        self.generalized_test('0')
