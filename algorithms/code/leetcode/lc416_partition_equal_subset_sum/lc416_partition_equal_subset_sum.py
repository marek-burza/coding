# https://leetcode.com/problems/partition-equal-subset-sum/

import unittest


def canPartitionSubset(
    nums: list[int],
    count: int,
    summed: int,
    cache: list[list[bool | None]],
) -> bool:
    if summed == 0:
        return True
    if count == 0:
        return False
    cached = cache[count][summed]
    if cached is not None:
        return cached
    if nums[count - 1] > summed:
        return canPartitionSubset(nums, count - 1, summed, cache)
    cached = canPartitionSubset(
        nums, count - 1, summed - nums[count - 1], cache
    ) or canPartitionSubset(nums, count - 1, summed, cache)
    cache[count][summed] = cached
    return cached


class Solution:
    def canPartition(self, nums: list[int]) -> bool:
        count = len(nums)
        summed = sum(nums)
        if summed % 2 != 0:
            return False
        cache: list[list[bool | None]] = []
        for _ in range(count + 1):
            line: list[bool | None] = []
            for _ in range(summed + 1):
                line.append(None)
            cache.append(line)
        return canPartitionSubset(nums, count, summed // 2, cache)


class TestCode(unittest.TestCase):
    def test_example_1(self) -> None:
        assert Solution().canPartition([1, 5, 11, 5])

    def test_example_2(self) -> None:
        assert not Solution().canPartition([1, 2, 3, 5])

    def test_longer(self) -> None:
        nums = ([100] * 198) + [99, 97]
        assert not Solution().canPartition(nums)
