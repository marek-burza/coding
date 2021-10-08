#!/usr/bin/env python3
# https://www.hackerrank.com/challenges/py-collections-namedtuple
# #python

import collections
import io
import sys
import unittest


def average(order, table):
    students = []
    student_tuple = collections.namedtuple('Student', order)
    total = 0
    for entry in table:
        student = student_tuple(entry[0], entry[1], entry[2], entry[3])
        students.append(student)
        total += int(student.MARKS)
    return total / len(students)


def main():
    n = int(input().strip())
    order = input().strip().split()
    table = []
    for _ in range(n):
        table.append(input().strip().split())
    print('%.2f' % average(order, table))


if __name__ == '__main__':  # pragma: no cover
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

    def test_1(self):
        self.generalized_test('1')

    def test_2(self):
        self.generalized_test('2')
