#!/usr/bin/env python3
# https://leetcode.com/problems/different-ways-to-add-parentheses/

import re
import unittest
from functools import cache
from typing import List


class Solution:
    def diffWaysToCompute(self, expression: str) -> List[int]:
        items = re.findall(r'[+\-*]|\d+', expression)

        @cache
        def traverse(a: int, z: int) -> List[int]:
            result = []
            if a == z:
                result.append(int(items[a]))
            else:
                for operator in range(a + 1, z, 2):
                    before = traverse(a, operator - 1)
                    after = traverse(operator + 1, z)
                    for ante in before:
                        for post in after:
                            if items[operator] == '+':
                                result.append(ante + post)
                            elif items[operator] == '-':
                                result.append(ante - post)
                            elif items[operator] == '*':
                                result.append(ante * post)
            return result

        return traverse(0, len(items) - 1)


class TestCode(unittest.TestCase):
    def generic(self, expected, result):
        result = sorted(result)
        self.assertListEqual(expected, result)

    def test_example_1(self):
        expected = [0, 2]
        self.generic(expected, Solution().diffWaysToCompute('2-1-1'))

    def test_example_2(self):
        expected = [-34, -14, -10, -10, 10]
        self.generic(expected, Solution().diffWaysToCompute('2*3-4*5'))

    def test_other(self):
        expected = [7]
        self.generic(expected, Solution().diffWaysToCompute('3+4'))
