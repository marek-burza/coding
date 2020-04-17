#!/usr/bin/env python3
# https://www.hackerrank.com/challenges/camelcase

import unittest


def camelcase(s):
    count = 1
    for character in s:
        if character.isupper():
            count += 1
    return count


class TestCode(unittest.TestCase):
    def test_example(self):
        self.assertEqual(5, camelcase('saveChangesInTheEditor'))
