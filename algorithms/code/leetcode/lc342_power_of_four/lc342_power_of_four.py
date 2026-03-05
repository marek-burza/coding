# https://leetcode.com/problems/power-of-four/

import math
import unittest


class Solution:
    def isPowerOfFour(self, num: int) -> bool:
        if num <= 0:
            return False
        value = math.log(num) / math.log(4)
        return value == math.floor(value)


class TestCode(unittest.TestCase):
    def test_16(self) -> None:
        assert Solution().isPowerOfFour(16)

    def test_5(self) -> None:
        assert not Solution().isPowerOfFour(5)

    def test_non_positive(self) -> None:
        assert not Solution().isPowerOfFour(0)
        assert not Solution().isPowerOfFour(-1)
