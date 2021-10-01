#!/usr/bin/env python3
# https://www.hackerrank.com/challenges/unbounded-knapsack

import os
import io
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
    if 'OUTPUT_PATH' in os.environ:  # pragma: no cover
        fptr = open(os.environ['OUTPUT_PATH'], 'w')
    else:
        fptr = sys.stdout
    t = int(input().strip())
    for _ in range(t):
        first_multiple_input = input().rstrip().split()
        _ = int(first_multiple_input[0])  # n
        k = int(first_multiple_input[1])
        arr = list(map(int, input().rstrip().split()))
        result = unboundedKnapsack(k, arr)
        fptr.write(str(result) + '\n')
    if 'OUTPUT_PATH' in os.environ:  # pragma: no cover
        fptr.close()


if __name__ == '__main__':  # pragma: no cover
    main()


class TestCode(unittest.TestCase):
    def generalized_test(self, which):
        resources = os.path.join(os.path.dirname(__file__), 'resources')
        sys.stdin = open(os.path.join(resources, f'input{which}.txt'), 'r')
        sys.stdout = io.StringIO()
        expected = open(os.path.join(resources, f'output{which}.txt'), 'r')
        main()
        self.assertEqual(sys.stdout.getvalue(), expected.read())
        for handle in [sys.stdin, sys.stdout, expected]:
            handle.close()

    def test_example(self):
        self.generalized_test('example')
