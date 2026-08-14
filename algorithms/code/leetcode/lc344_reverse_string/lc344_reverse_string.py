# https://leetcode.com/problems/reverse-string/


class Solution:
    def reverseString(self, s: list[str]) -> None:
        for i in range(round(len(s) / 2)):
            exchange = s[i]
            s[i] = s[len(s) - 1 - i]
            s[len(s) - 1 - i] = exchange


class TestCode:
    def test_example(self) -> None:
        s = list("hello")
        Solution().reverseString(s)
        assert list("olleh") == s
