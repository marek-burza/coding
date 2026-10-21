# https://leetcode.com/problems/group-anagrams/
# #medium

from functools import cmp_to_key


class Solution:
    def groupAnagrams(self, strs: list[str]) -> list[list[str]]:
        seen: dict[str, list[str]] = {}
        for string in strs:
            array = list(string)
            array.sort()
            key = "".join(array)
            if key in seen:
                seen[key].append(string)
            else:
                group = []
                group.append(string)
                seen[key] = group
        result = []
        for group in seen.values():
            group.sort()
            result.append(group)
        return result


class TestCode:
    @staticmethod
    def orderly_comparator(l1: list[str], l2: list[str]) -> int:  # pragma: no cover
        difference = len(l1) - len(l2)
        if difference != 0:
            return difference
        for l1s, l2s in zip(l1, l2, strict=True):
            if l1s < l2s:
                return -1
            if l1s > l2s:
                return 1
        return 0

    def __test(self, expected: list[list[str]], result: list[list[str]]) -> None:
        result = sorted(result, key=cmp_to_key(TestCode.orderly_comparator))
        assert expected == result

    def test_abc_cab_bad_dab_zzz_dot(self) -> None:
        strs = ["abc", "cab", "bad", "dab", "zzz", "dot"]
        expected = [["dot"], ["zzz"], ["abc", "cab"], ["bad", "dab"]]
        result = Solution().groupAnagrams(strs)
        self.__test(expected, result)

    def test_tea_and_ate_eat_den(self) -> None:
        strs = ["tea", "and", "ate", "eat", "den"]
        expected = [["and"], ["den"], ["ate", "eat", "tea"]]
        result = Solution().groupAnagrams(strs)
        self.__test(expected, result)
