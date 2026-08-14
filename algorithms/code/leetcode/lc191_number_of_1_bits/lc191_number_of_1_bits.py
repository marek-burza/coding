# https://leetcode.com/problems/number-of-1-bits/


class Solution:
    def hammingWeight(self, n: int) -> int:
        count = 0
        for _ in range(0, 32):
            count += n % 2  # Faster than "& 1" in Python
            n >>= 1
        return count


class TestCode:
    def test_11(self) -> None:
        assert Solution().hammingWeight(11) == 3
