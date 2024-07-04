#!/usr/bin/env python3
# https://www.hackerrank.com/challenges/defaultdict-tutorial
# #python

import collections
import io
import sys
import unittest


def list_repetitions(words_a, words_b):
    result = collections.defaultdict(list)
    for i, word in enumerate(words_a):
        if word in words_b:
            result[word].append(i + 1)
    return result


def main():
    n_in, m_in = list(map(int, input().strip().split(' ')))
    words_a_in = []
    words_b_in = []
    for _ in range(n_in):
        words_a_in.append(input().strip())
    for _ in range(m_in):
        words_b_in.append(input().strip())
    result_out = list_repetitions(words_a_in, words_b_in)
    for each in words_b_in:
        if len(result_out[each]) == 0:
            print(-1)
        else:
            print(' '.join(list(map(str, result_out[each]))))


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

    def test_01(self):
        self.generalized_test('01')
