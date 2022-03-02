#!/usr/bin/env python3
# https://leetcode.com/problems/count-and-say/

import unittest


class Solution:
    def countAndSay(self, n: int) -> str:
        if n < 1:
            return ''
        result = '1'
        while n > 1:
            current = ''
            check = '0'
            count = 0
            for character in result:
                if check != character:
                    if count > 0:
                        current += str(count)
                        current += check
                    count = 1
                    check = character
                else:
                    count += 1
            current += str(count)
            current += check
            n -= 1
            result = current
        return result


class TestCode(unittest.TestCase):
    def test_1(self):
        self.assertEqual('1', Solution().countAndSay(1))

    def test_2(self):
        self.assertEqual('11', Solution().countAndSay(2))

    def test_3(self):
        self.assertEqual('21', Solution().countAndSay(3))

    def test_4(self):
        self.assertEqual('1211', Solution().countAndSay(4))

    def test_5(self):
        self.assertEqual('111221', Solution().countAndSay(5))

    def test_0(self):
        self.assertEqual('', Solution().countAndSay(0))
