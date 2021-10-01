#!/usr/bin/env python3
# https://www.hackerrank.com/challenges/decorators-2-name-directory

import io
import operator
import sys
import unittest


def title(gender):
    return 'Mr.' if gender == 'M' else 'Ms.'


def person_lister(f):
    def inner(people):
        people.sort(key=operator.itemgetter(2))
        return [f(person) for person in people]
    return inner


@person_lister
def name_format(person):
    return ' '.join([title(person[3]), person[0], person[1]])


def main():
    n = int(input().strip())
    people = [input().strip().split() for _ in range(n)]
    print(*name_format(people), sep='\n')


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

    def test_0(self):
        self.generalized_test('0')
