# https://leetcode.com/problems/single-number-iii/

import unittest


class Solution:
    def singleNumber(self, nums: list[int]) -> list[int]:
        xor = 0
        for value in nums:
            xor ^= value
        mask = xor & ~(xor - 1)
        values = [0, 0]
        for value in nums:
            if (value & mask) == 0:
                values[0] ^= value
            else:
                values[1] ^= value
        return values


class TestCode(unittest.TestCase):
    def test(self) -> None:
        result = Solution().singleNumber([1, 2, 1, 3, 2, 5])
        result.sort()
        assert result == [3, 5]
