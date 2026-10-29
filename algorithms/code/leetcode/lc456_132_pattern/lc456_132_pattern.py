# https://leetcode.com/problems/132-pattern/
# #medium


class Solution:
    def find132pattern(self, nums: list[int]) -> bool:
        stack: list[tuple[int, int]] = []
        minimum = nums[0]
        for num in nums[1:]:
            while stack and num >= stack[-1][1]:
                stack.pop()
            if stack and num > stack[-1][0]:
                return True
            stack.append((minimum, num))
            minimum = min(minimum, num)
        return False


class TestCode:
    def test_example_1(self) -> None:
        assert not Solution().find132pattern([1, 2, 3, 4])

    def test_example_2(self) -> None:
        assert Solution().find132pattern([3, 1, 4, 2])

    def test_example_3(self) -> None:
        assert Solution().find132pattern([-1, 3, 2, 0])

    def test_35034(self) -> None:
        assert Solution().find132pattern([3, 5, 0, 3, 4])
