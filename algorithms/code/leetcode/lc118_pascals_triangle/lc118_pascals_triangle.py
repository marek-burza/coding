# https://leetcode.com/problems/pascals-triangle/

import unittest


class Solution:
    def generate(self, numRows: int) -> list[list[int]]:
        if numRows < 0:
            return []
        triangle = []
        for i in range(numRows):
            row: list[int] = []
            triangle.append(row)
            row.append(1)
            if i > 0:
                above = triangle[i - 1]
                for j in range(i - 1):
                    row.append(above[j] + above[j + 1])
                row.append(1)
        return triangle


class TestCode(unittest.TestCase):
    def test_5(self) -> None:
        expected = [[1], [1, 1], [1, 2, 1], [1, 3, 3, 1], [1, 4, 6, 4, 1]]
        result = Solution().generate(5)
        assert len(expected) == len(result)
        for expected_i, result_i in zip(expected, result, strict=True):
            assert expected_i == result_i

    def test_nothing(self) -> None:
        assert not Solution().generate(-1)
