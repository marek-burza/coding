# https://leetcode.com/problems/pascals-triangle-ii/

import unittest


class Solution:
    def getRow(self, rowIndex: int) -> list[int]:
        rowIndex += 1
        if rowIndex < 0:
            return []
        previous: list[int] = []
        current = None
        for i in range(rowIndex):
            current = []
            current.append(1)
            if i > 0:
                for j in range(i - 1):
                    current.append(previous[j] + previous[j + 1])
                current.append(1)
            previous = current
        assert current is not None
        return current


class TestCode(unittest.TestCase):
    def test_3(self) -> None:
        expected = [1, 3, 3, 1]
        result = Solution().getRow(3)
        assert expected == result

    def test_nothing(self) -> None:
        assert not Solution().getRow(-2)
