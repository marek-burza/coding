# https://leetcode.com/problems/contains-duplicate/

import unittest


class Solution:
    def containsDuplicate(self, nums: list[int]) -> bool:
        seen = set()
        for num in nums:
            if num in seen:
                return True
            seen.add(num)
        return False


class TestCode(unittest.TestCase):
    def test_0_5_7(self) -> None:
        assert not Solution().containsDuplicate([0, 5, 7])

    def test_0_5_7_5_10(self) -> None:
        assert Solution().containsDuplicate([0, 5, 7, 5, 10])
