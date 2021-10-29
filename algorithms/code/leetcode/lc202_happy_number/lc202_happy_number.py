#!/usr/bin/env python3
# https://leetcode.com/problems/happy-number/

import unittest


class Solution:
    def __re(self, n):
        result = 0
        while n != 0:
            digit = n % 10
            n //= 10
            result += digit * digit
        return result

    def isHappy(self, n: int) -> bool:
        seen = set()
        seen.add(n)
        while n != 1:
            n = self.__re(n)
            if n in seen:
                return False
            seen.add(n)
        return True


class TestCode(unittest.TestCase):
    def test_19(self):
        self.assertTrue(Solution().isHappy(19))

    def test_2(self):
        self.assertFalse(Solution().isHappy(2))
