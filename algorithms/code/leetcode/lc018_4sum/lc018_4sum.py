# https://leetcode.com/problems/4sum/

import unittest


def twoSum(
    nums: list[int],
    target: int,
    left: int,
    right: int,
) -> list[list[int]]:
    results: list[list[int]] = []
    original_left = left
    original_right = right
    while left < right:
        summed = nums[left] + nums[right]
        if summed < target or (
            left > original_left and nums[left] == nums[left - 1]
        ):
            left += 1
        elif summed > target or (
            right < original_right and nums[right] == nums[right + 1]
        ):
            right -= 1
        else:
            results.append([nums[left], nums[right]])
            left += 1
            right -= 1
    return results


# pylint: disable-msg=R0913,R0917
def kSum(
    nums: list[int],
    k: int,
    target: int,
    left: int,
    right: int,
    result: list[int],
    results: list[list[int]],
) -> None:
    if (
        not nums
        or (right - left + 1 < k)
        or (k < 2)
        or (nums[left] * k > target)
        or nums[right] * k < target
    ):
        return
    if k > 2:
        for i in range(left, right + 1):
            if i > left and nums[i] == nums[i - 1]:
                continue
            kSum(
                nums,
                k - 1,
                target - nums[i],
                i + 1,
                right,
                result + [nums[i]],
                results,
            )
        return
    for item in twoSum(nums, target, left, right):
        results.append(result + item)


class Solution:
    def fourSum(self, nums: list[int], target: int) -> list[list[int]]:
        results: list[list[int]] = []
        kSum(sorted(nums), 4, target, 0, len(nums) - 1, [], results)
        return results


class TestCode(unittest.TestCase):
    def test_example_1(self) -> None:
        nums = [1, 0, -1, 0, -2, 2]
        target = 0
        expected = [[-2, -1, 1, 2], [-2, 0, 0, 2], [-1, 0, 0, 1]]
        solution = Solution()
        result = solution.fourSum(nums, target)
        assert sorted(result) == sorted(expected)

    def test_example_2(self) -> None:
        nums = [2, 2, 2, 2, 2]
        target = 8
        expected = [[2, 2, 2, 2]]
        solution = Solution()
        result = solution.fourSum(nums, target)
        assert sorted(result) == sorted(expected)
