# https://leetcode.com/problems/combination-sum-iii/

import unittest


class Solution:
    # pylint: disable=R0913,R0917
    def __traverse(
        self,
        contains: int,
        summed: int,
        left: int,
        n: int,
        found: list[int],
        start: int,
    ) -> None:
        if left == 0 and summed == n:
            found.append(contains)
        else:
            for i in range(start, 10):
                mask = 1 << i
                # if (contains & mask) == 0:
                self.__traverse(
                    contains | mask, summed + i, left - 1, n, found, i + 1
                )

    def combinationSum3(self, k: int, n: int) -> list[list[int]]:
        found: list[int] = []
        self.__traverse(0, 0, k, n, found, 1)
        each = []
        for contains in found:
            entry: list[int] = []
            each.append(entry)
            for i in range(1, 10):
                mask = 1 << i
                if (contains & mask) != 0:
                    entry.append(i)
        return each


class TestCode(unittest.TestCase):
    def __test(
        self, expected: list[list[int]], result: list[list[int]]
    ) -> None:
        for entry in result:
            entry.sort()
        assert len(expected) == len(result)
        for i, expected_i in enumerate(expected):
            entry = result[i]
            assert len(expected_i) == len(entry)
            for j, expected_i_j in enumerate(expected_i):
                assert expected_i_j == entry[j]

    def test_3_7(self) -> None:
        expected = [[1, 2, 4]]
        self.__test(expected, Solution().combinationSum3(3, 7))

    def test_3_9(self) -> None:
        expected = [[1, 2, 6], [1, 3, 5], [2, 3, 4]]
        self.__test(expected, Solution().combinationSum3(3, 9))
