#!/usr/bin/env python3
# https://leetcode.com/problems/super-ugly-number/

import unittest
from typing import List


class Solution:
    def nthSuperUglyNumber(self, n: int, primes: List[int]) -> int:
        m = len(primes)
        mul = [0] * m
        dp = [0] * n
        dp[0] = 1
        for i in range(1, n):
            dp_i = float('inf')
            temp1 = -1
            for j in range(m):
                temp2 = dp[mul[j]] * primes[j]
                if dp_i > temp2:
                    dp_i = temp2
                    dp[i] = dp_i
                    temp1 = j
                else:
                    if dp_i == temp2:
                        mul[j] += 1
            mul[temp1] += 1
        return dp[n - 1]


class TestCode(unittest.TestCase):
    def test_example(self):
        expected = [1, 2, 4, 7, 8, 13, 14, 16, 19, 26, 28, 32]
        primes = [2, 7, 13, 19]
        for i, expected_i in enumerate(expected):
            self.assertEqual(
                expected_i, Solution().nthSuperUglyNumber(i + 1, primes)
            )

    def test_other(self):
        expected = 1092889481
        primes = [
            7,
            19,
            29,
            37,
            41,
            47,
            53,
            59,
            61,
            79,
            83,
            89,
            101,
            103,
            109,
            127,
            131,
            137,
            139,
            157,
            167,
            179,
            181,
            199,
            211,
            229,
            233,
            239,
            241,
            251,
        ]
        self.assertEqual(
            expected, Solution().nthSuperUglyNumber(100000, primes)
        )

    def test_even_bigger(self):
        expected = 15132
        primes = [
            2,
            3,
            5,
            13,
            19,
            29,
            31,
            41,
            43,
            53,
            59,
            73,
            83,
            89,
            97,
            103,
            107,
            109,
            127,
            137,
            139,
            149,
            163,
            173,
            179,
            193,
            197,
            199,
            211,
            223,
            227,
            229,
            239,
            241,
            251,
            257,
            263,
            269,
            271,
            281,
            317,
            331,
            337,
            347,
            353,
            359,
            367,
            373,
            379,
            389,
            397,
            409,
            419,
            421,
            433,
            449,
            457,
            461,
            463,
            479,
            487,
            509,
            521,
            523,
            541,
            547,
            563,
            569,
            577,
            593,
            599,
            601,
            613,
            619,
            631,
            641,
            659,
            673,
            683,
            701,
            709,
            719,
            733,
            739,
            743,
            757,
            761,
            769,
            773,
            809,
            811,
            829,
            857,
            859,
            881,
            919,
            947,
            953,
            967,
            971,
        ]
        self.assertEqual(expected, Solution().nthSuperUglyNumber(4000, primes))
