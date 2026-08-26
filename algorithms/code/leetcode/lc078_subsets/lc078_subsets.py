# https://leetcode.com/problems/subsets/
# #medium


class Solution:
    def __subsets(
        self,
        nums: list[int],
        offset: int,
        current: list[int],
        listed: list[list[int]],
    ) -> None:
        listed.append(current.copy())
        for i in range(offset, len(nums)):
            current.append(nums[i])
            self.__subsets(nums, i + 1, current, listed)
            current.pop()

    def subsets(self, nums: list[int]) -> list[list[int]]:
        nums.sort()
        listed: list[list[int]] = []
        self.__subsets(nums, 0, [], listed)
        return listed


class TestCode:
    def __test(self, expected: list[list[int]], result: list[list[int]]) -> None:
        result = sorted(result)
        expected = sorted(expected)
        expected = [sorted(expected_i) for expected_i in expected]
        result = [sorted(result_i) for result_i in result]
        assert expected == result

    def test_1_2_3(self) -> None:
        listed = Solution().subsets([1, 2, 3])
        expected = [[], [1], [2], [3], [1, 2], [1, 3], [2, 3], [1, 2, 3]]
        self.__test(expected, listed)
