#!/usr/bin/env python3
# https://www.hackerrank.com/challenges/py-collections-namedtuple

import collections


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

if __name__ == '__main__':
    main()
