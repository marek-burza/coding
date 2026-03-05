# https://leetcode.com/problems/two-sum-ii-input-array-is-sorted/

import unittest


class Solution:
    def twoSum(self, numbers: list[int], target: int) -> list[int]:
        indices = [0] * 2
        if len(numbers) < 2:
            return indices
        a = 0
        z = len(numbers) - 1
        while a < z:
            v = numbers[a] + numbers[z]
            if v == target:
                indices[0] = a + 1
                indices[1] = z + 1
                break
            if v > target:
                z -= 1
            else:
                a += 1
        return indices


class TestCode(unittest.TestCase):
    def test_example(self) -> None:
        assert Solution().twoSum([2, 7, 11, 15], 9) == [1, 2]

    def test_other_example(self) -> None:
        assert Solution().twoSum([1, 5, 6, 9], 9) == [0, 0]

    def test_nothing(self) -> None:
        assert Solution().twoSum([], 0) == [0, 0]
        assert Solution().twoSum([0], 0) == [0, 0]
