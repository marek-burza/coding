# https://leetcode.com/problems/permutation-sequence/

import unittest


class Solution:
    def getPermutation(self, n: int, k: int) -> str:
        if n < 0 or k < 0:
            return ''
        result = ''
        remaining = []
        factorials = []
        factorials.append(0)
        factorial = 1
        for i in range(1, n + 1):
            factorial *= i
            factorials.append(factorial)
            remaining.append(i)
        for i in range(1, n):
            block = factorials[n - i]
            index = (k - 1) // block
            result += str(remaining.pop(index))
            k -= index * block
        result += str(remaining.pop(0))
        return result


class TestCode(unittest.TestCase):
    def test_2_1(self) -> None:
        assert Solution().getPermutation(2, 1) == '12'

    def test_3_2(self) -> None:
        assert Solution().getPermutation(3, 2) == '132'

    def test_nothing(self) -> None:
        assert Solution().getPermutation(1, -1) == ''
        assert Solution().getPermutation(-1, 1) == ''
