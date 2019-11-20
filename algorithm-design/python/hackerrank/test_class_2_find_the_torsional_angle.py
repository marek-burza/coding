#!/usr/bin/env python3
# https://www.hackerrank.com/challenges/class-2-find-the-torsional-angle

import math


class Point:
    def __init__(self, x, y, z):
        self.x = x
        self.y = y
        self.z = z


class Vector(Point):
    def __init__(self, from_point, to_point):
        x = to_point.x - from_point.x
        y = to_point.y - from_point.y
        z = to_point.z - from_point.z
        Point.__init__(self, x, y, z)

    def dot_product(self, other):
        return self.x * other.x + self.y * other.y + self.z * other.z

    def cross_product(self, other):
        zero = Point(0, 0, 0)
        x = self.y * other.z - self.z * other.y
        y = self.z * other.x - self.x * other.z
        z = self.x * other.y - self.y * other.x
        return Vector(zero, Point(x, y, z))

    def value(self):
        return math.sqrt(math.pow(self.x, 2) + math.pow(self.y, 2) + math.pow(self.z, 2))


def torsional_angle(a, b, c, d):
    ab = Vector(a, b)
    bc = Vector(b, c)
    cd = Vector(c, d)
    x = ab.cross_product(bc)
    y = bc.cross_product(cd)
    cosine = x.dot_product(y) / (x.value() * y.value())
    return math.degrees(math.acos(cosine))


def main():
    a_x, a_y, a_z = tuple(map(float, input().strip().split()))
    b_x, b_y, b_z = tuple(map(float, input().strip().split()))
    c_x, c_y, c_z = tuple(map(float, input().strip().split()))
    d_x, d_y, d_z = tuple(map(float, input().strip().split()))
    a = Point(a_x, a_y, a_z)
    b = Point(b_x, b_y, b_z)
    c = Point(c_x, c_y, c_z)
    d = Point(d_x, d_y, d_z)
    print('%.2f' % torsional_angle(a, b, c, d))

if __name__ == '__main__':
    main()
