#!/usr/bin/env python3
# https://www.hackerrank.com/challenges/validate-list-of-email-address-with-filter
# #python

import io
import re
import sys
import unittest


def check(email):
    items = email.split('@')
    if len(items) != 2:
        return False
    if not re.match(r'[\w-]+$', items[0]):
        return False
    items = items[1].split('.')
    if len(items) != 2:
        return False
    if not items[0].isalnum():
        return False
    return 0 < len(items[1]) <= 3


def process(emails):
    emails = list(filter(check, emails))
    emails.sort()
    return emails


def main():
    n = int(input().strip())
    emails = []
    for _ in range(n):
        emails.append(input().strip())
    print(process(emails))


if __name__ == '__main__':  # pragma: no cover
    main()


class TestCode(unittest.TestCase):
    def generalized_test(self, which):
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

    def test_0(self):
        self.generalized_test('0')

    def test_double_at(self):
        self.assertEqual(process(['somebody@somewhere@else.com']), [])

    def test_double_dot(self):
        self.assertEqual(process(['somebody@somewhere.else.com']), [])

    def test_garbage(self):
        self.assertEqual(process(['somebody@some+where.com']), [])

    def test_white_space(self):
        self.assertEqual(process(['somebody\t@somewhere.com']), [])
