#!/usr/bin/env python3
# https://leetcode.com/problems/counting-bits/

import unittest
from typing import List


class Solution:
    def countBits(self, n: int) -> List[int]:
        result = [0] * (n + 1)
        threshold = 1
        for i, _ in enumerate(result):
            if threshold << 1 <= i:
                threshold <<= 1
            if i == 0:
                result[0] = 0
            else:
                result[i] = 1 + result[i - threshold]
        return result


class TestCode(unittest.TestCase):
    def test_2(self):
        self.assertListEqual([0, 1, 1], Solution().countBits(2))

    def test_5(self):
        self.assertListEqual([0, 1, 1, 2, 1, 2], Solution().countBits(5))
