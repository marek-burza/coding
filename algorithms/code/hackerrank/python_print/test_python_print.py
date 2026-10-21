# https://www.hackerrank.com/challenges/python-print
# #python

import io
import sys
from typing import IO, Any


def printer(n: int, file: IO[Any]) -> None:
    for item in range(1, n + 1):
        print(item, end="", file=file)


def main() -> None:
    printer(int(input().strip()), sys.stdout)
    print()


if __name__ == "__main__":  # pragma: no cover
    main()


class TestCode:
    def generalized_test(self, which: str) -> None:
        with (
            open(
                __file__.replace(".py", f".{which}.out"), "r", encoding="utf-8"
            ) as expected,
            open(
                __file__.replace(".py", f".{which}.in"), "r", encoding="utf-8"
            ) as sys.stdin,
            io.StringIO() as sys.stdout,
        ):
            main()
            assert sys.stdout.getvalue() == expected.read()

    def test_0(self) -> None:
        self.generalized_test("0")
