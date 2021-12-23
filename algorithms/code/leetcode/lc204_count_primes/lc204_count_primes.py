#!/usr/bin/env python3
# https://leetcode.com/problems/count-primes/

import unittest


class Solution:
    def countPrimes(self, n: int) -> int:
        if n < 2:
            return 0
        # Eratosthenes sieve
        sieve = [True] * (n - 2)
        count = 0
        for i, _ in enumerate(sieve):
            if not sieve[i]:
                continue
            count += 1
            number = 2 + i
            for j in range(i + number, len(sieve), number):
                sieve[j] = False
        return count


class TestCode(unittest.TestCase):
    def test_11(self):
        self.assertEqual(4, Solution().countPrimes(11))

    def test_1(self):
        self.assertEqual(0, Solution().countPrimes(1))
