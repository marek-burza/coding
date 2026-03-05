# https://leetcode.com/problems/shuffle-an-array/

import random
import unittest


class Solution:
    def __init__(self, nums: list[int]) -> None:
        self.__nums = nums

    def reset(self) -> list[int]:
        return self.__nums.copy()

    def shuffle(self) -> list[int]:
        result = self.__nums.copy()
        for i in range(len(self.__nums) - 1, 0, -1):
            j = random.SystemRandom().randint(0, i)
            result[i], result[j] = result[j], result[i]
        return result


class TestCode(unittest.TestCase):
    def __test(self, nums: list[int]) -> None:
        solution = Solution(nums.copy())
        result = solution.shuffle()
        reset = solution.reset()
        assert nums == reset
        nums.sort()
        result.sort()
        assert nums == result

    def test_example(self) -> None:
        nums = [1, 2, 3]
        self.__test(nums)
        # Should use Chi-squared test

    def test_nothing(self) -> None:
        solution = Solution([])
        assert not solution.shuffle()
