# https://www.hackerrank.com/challenges/py-if-else

import io
import sys
import unittest


def is_weird(value: int) -> bool:
    if (value % 2) == 1:
        return True
    if 2 <= value <= 5:
        return False
    return 6 <= value <= 20


def main() -> None:
    print('Weird' if is_weird(int(input().strip())) else 'Not Weird')


if __name__ == '__main__':  # pragma: no cover
    main()


class TestCode(unittest.TestCase):
    def generalized_test(self, which) -> None:
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

    def test_1_to_24(self) -> None:
        expected = [
            True,
            False,
            True,
            False,
            True,
            True,
            True,
            True,
            True,
            True,
            True,
            True,
            True,
            True,
            True,
            True,
            True,
            True,
            True,
            True,
            True,
            False,
            True,
            False,
        ]
        result = []
        for value in range(1, 25):
            result.append(is_weird(value))
        assert result == expected
