# https://leetcode.com/problems/hamming-distance/


class Solution:
    def hammingDistance(self, x: int, y: int) -> int:
        return (x ^ y).bit_count()


class TestCode:
    def test_example_1(self) -> None:
        assert Solution().hammingDistance(1, 4) == 2

    def test_example_2(self) -> None:
        assert Solution().hammingDistance(3, 1) == 1
