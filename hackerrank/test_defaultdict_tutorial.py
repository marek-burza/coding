#!/usr/bin/env python3
# https://www.hackerrank.com/challenges/defaultdict-tutorial

from .helper import io_checker

import collections
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
    for j in range(n_in):
        words_a_in.append(input().strip())
    for j in range(m_in):
        words_b_in.append(input().strip())
    result_out = list_repetitions(words_a_in, words_b_in)
    for each in words_b_in:
        if len(result_out[each]) == 0:
            print(-1)
        else:
            print(' '.join(list(map(str, result_out[each]))))

if __name__ == '__main__':
    main()


class TestCode(unittest.TestCase):
    def test_0(self):
        io_checker(self, __file__, '0', main)

    def test_01(self):
        io_checker(self, __file__, '01', main)
