#!/usr/bin/env python3
# https://www.hackerrank.com/challenges/unbounded-knapsack

import io
import os
import sys
import unittest


def unboundedKnapsack(w, values):
    weights = values  # Special case
    n = len(values)
    m = [0] * (w + 1)
    for wi in range(w + 1):
        for vi in range(n):
            if weights[vi] <= wi:
                m[wi] = max(m[wi], m[wi - weights[vi]] + values[vi])
    return m[w]


def main():
    fptr = sys.stdout
    t = int(input().strip())
    for _ in range(t):
        first_multiple_input = input().rstrip().split()
        _ = int(first_multiple_input[0])  # n
        k = int(first_multiple_input[1])
        arr = list(map(int, input().rstrip().split()))
        result = unboundedKnapsack(k, arr)
        fptr.write(str(result) + '\n')


if __name__ == '__main__':  # pragma: no cover
    main()


class TestCode(unittest.TestCase):
    def generalized_test(self, which):
        resources = os.path.dirname(__file__)
        with (
            open(
                os.path.join(resources, f'output{which}.txt'),
                'r',
                encoding='utf-8',
            ) as expected,
            open(
                os.path.join(resources, f'input{which}.txt'),
                'r',
                encoding='utf-8',
            ) as sys.stdin,
            io.StringIO() as sys.stdout,
        ):
            main()
            self.assertEqual(sys.stdout.getvalue(), expected.read())

    def test_example(self):
        self.generalized_test('example')
