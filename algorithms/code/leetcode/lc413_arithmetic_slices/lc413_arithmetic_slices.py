# https://leetcode.com/problems/arithmetic-slices/


class Solution:
    @staticmethod
    def _arithmetic_progression_sum(first: int, delta: int, n: int) -> int:
        return n * (2 * first + (n - 1) * delta) // 2

    def numberOfArithmeticSlices(self, nums: list[int]) -> int:
        if len(nums) < 3:
            return 0
        delta = nums[0] - nums[1]
        same = 2
        result = 0
        for offset, current in enumerate(nums[2:]):
            index = 2 + offset
            previous = nums[index - 1]
            candidate = previous - current
            if delta == candidate:
                same += 1
            else:
                if same >= 3:
                    result += Solution._arithmetic_progression_sum(1, 1, same - 2)
                delta = candidate
                same = 2
        if same >= 3:
            result += Solution._arithmetic_progression_sum(1, 1, same - 2)
        return result


class TestCode:
    def test_example_1(self) -> None:
        assert Solution().numberOfArithmeticSlices([1, 2, 3, 4]) == 3

    def test_example_2(self) -> None:
        assert Solution().numberOfArithmeticSlices([1]) == 0

    def test_213423(self) -> None:
        assert Solution().numberOfArithmeticSlices([2, 1, 3, 4, 2, 3]) == 0

    def test_1230(self) -> None:
        assert Solution().numberOfArithmeticSlices([1, 2, 3, 0]) == 1
