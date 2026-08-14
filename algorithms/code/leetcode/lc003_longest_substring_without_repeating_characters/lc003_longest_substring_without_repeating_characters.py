# https://leetcode.com/problems/longest-substring-without-repeating-characters/


class Solution:
    def lengthOfLongestSubstring(self, s: str) -> int:
        seen: set[str] = set()
        longest = count = 0
        for i, found in enumerate(s):
            while count > 0 and found in seen:
                seen.remove(s[i - count])
                count -= 1
            count += 1
            seen.add(found)
            longest = max(longest, count)
        return longest


class TestCode:
    def test_abcabcbb(self) -> None:
        assert Solution().lengthOfLongestSubstring("abcabcbb") == 3

    def test_bbbbb(self) -> None:
        assert Solution().lengthOfLongestSubstring("bbbbb") == 1

    def test_dvdf(self) -> None:
        assert Solution().lengthOfLongestSubstring("dvdf") == 3
