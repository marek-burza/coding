#!/usr/bin/env python3
# https://leetcode.com/problems/restore-ip-addresses/

import unittest

from typing import List


class Solution:
    def __partial(self, s, count, ip, listed):
        if len(s) < count or (s[0] == '0' and count > 1):
            return
        part = None
        prefix = None
        prefix = s[0:count]
        part = int(prefix)
        if part <= 255:  # 0 <= part <= 255
            ip.append(prefix)
            self.__restore(s[count:], ip, listed)
            ip.pop()

    def __restore(self, s, ip, listed):
        if len(ip) >= 4:
            if len(s) == 0:
                string = ''
                for i in range(0, 4):
                    if i > 0:
                        string += '.'
                    string += ip[i]
                listed.append(string)
        else:
            self.__partial(s, 1, ip, listed)
            self.__partial(s, 2, ip, listed)
            self.__partial(s, 3, ip, listed)

    def restoreIpAddresses(self, s: str) -> List[str]:
        ip: List[str] = []
        listed: List[str] = []
        self.__restore(s, ip, listed)
        return listed


class TestCode(unittest.TestCase):
    def __freeze(self, listed):
        result = [None] * len(listed)
        for i, _ in enumerate(result):
            result[i] = listed[i]
        return result

    def test_25525511135(self):
        expected = ['255.255.11.135', '255.255.111.35']
        result = self.__freeze(Solution().restoreIpAddresses('25525511135'))
        result.sort()
        self.assertListEqual(expected, result)

    def test_101023(self):
        expected = [
            '1.0.10.23', '1.0.102.3', '10.1.0.23', '10.10.2.3', '101.0.2.3']
        result = self.__freeze(Solution().restoreIpAddresses('101023'))
        result.sort()
        self.assertListEqual(expected, result)
