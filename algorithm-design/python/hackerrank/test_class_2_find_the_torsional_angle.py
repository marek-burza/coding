#!/usr/bin/env python3
# https://www.hackerrank.com/challenges/class-2-find-the-torsional-angle

import math


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
        return math.sqrt(math.pow(self.x, 2) + math.pow(self.y, 2) + math.pow(self.z, 2))


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
