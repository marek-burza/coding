# https://leetcode.com/problems/longest-palindrome/

from collections import Counter


class Solution:
    def longestPalindrome(self, s: str) -> int:
        longest = 0
        counted = Counter(s)
        odd = 0
        for count in counted.values():
            if count % 2 == 1:
                count -= 1
                odd = 1
            longest += count
        longest += odd
        return longest


class TestCode:
    def test_example_1(self) -> None:
        assert Solution().longestPalindrome("abccccdd") == 7

    def test_example_2(self) -> None:
        assert Solution().longestPalindrome("a") == 1
