#!/usr/bin/env python3
# https://www.hackerrank.com/challenges/validate-list-of-email-address-with-filter
# #python

import io
import re
import sys
import unittest


def check(email: str) -> bool:
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


def process(emails: list[str]) -> list[str]:
    emails = list(filter(check, emails))
    emails.sort()
    return emails


def main() -> None:
    n = int(input().strip())
    emails = []
    for _ in range(n):
        emails.append(input().strip())
    print(process(emails))


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
            assert sys.stdout.getvalue() == expected.read()

    def test_0(self) -> None:
        self.generalized_test('0')

    def test_double_at(self) -> None:
        assert not process(['somebody@somewhere@else.com'])

    def test_double_dot(self) -> None:
        assert not process(['somebody@somewhere.else.com'])

    def test_garbage(self) -> None:
        assert not process(['somebody@some+where.com'])

    def test_white_space(self) -> None:
        assert not process(['somebody\t@somewhere.com'])
