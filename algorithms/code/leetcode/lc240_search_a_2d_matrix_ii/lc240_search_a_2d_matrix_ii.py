# https://leetcode.com/problems/search-a-2d-matrix-ii/

import unittest


class Solution:
    # pylint: disable=R0911,R0913,R0917
    def __searchMatrix(
        self,
        matrix: list[list[int]],
        target: int,
        rowA: int,
        rowZ: int,
        colA: int,
        colZ: int,
    ) -> bool:
        if rowA == rowZ and colA == colZ:
            return matrix[rowZ][colZ] == target
        # if target < matrix[rowA][colA] or matrix[rowZ][colZ] < target:
        #     return False
        rowM = (rowA + rowZ) // 2
        colM = (colA + colZ) // 2
        cols = colA < colZ
        rows = rowA < rowZ
        if target <= matrix[rowM][colM] and self.__searchMatrix(
            matrix, target, rowA, rowM, colA, colM
        ):
            return True
        if (
            cols
            and target <= matrix[rowM][colZ]
            and self.__searchMatrix(matrix, target, rowA, rowM, colM + 1, colZ)
        ):
            return True
        if (
            rows
            and target <= matrix[rowZ][colM]
            and self.__searchMatrix(matrix, target, rowM + 1, rowZ, colA, colM)
        ):
            return True
        if not (rows and cols):
            return False
        return self.__searchMatrix(
            matrix, target, rowM + 1, rowZ, colM + 1, colZ
        )

    def searchMatrix(self, matrix: list[list[int]], target: int) -> bool:
        return self.__searchMatrix(
            matrix, target, 0, len(matrix) - 1, 0, len(matrix[0]) - 1
        )


class TestCode(unittest.TestCase):
    EXAMPLE_MATRIX = [
        [1, 4, 7, 11, 15],
        [2, 5, 8, 12, 19],
        [3, 6, 9, 16, 22],
        [10, 13, 14, 17, 24],
        [18, 21, 23, 26, 30],
    ]

    def test_example_1(self) -> None:
        assert Solution().searchMatrix(TestCode.EXAMPLE_MATRIX, 5)

    def test_example_2(self) -> None:
        assert not Solution().searchMatrix(TestCode.EXAMPLE_MATRIX, 20)

    def test_other_1(self) -> None:
        matrix = [[1, 4], [2, 5]]
        assert Solution().searchMatrix(matrix, 2)

    def test_other_2(self) -> None:
        matrix = [[-1, 3]]
        assert not Solution().searchMatrix(matrix, 1)

    def test_other_3(self) -> None:
        matrix = [
            [1, 2, 3, 4, 5],
            [6, 7, 8, 9, 10],
            [11, 12, 13, 14, 15],
            [16, 17, 18, 19, 20],
            [21, 22, 23, 24, 25],
        ]
        assert Solution().searchMatrix(matrix, 5)
