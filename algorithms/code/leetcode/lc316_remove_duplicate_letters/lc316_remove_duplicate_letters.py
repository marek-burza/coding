# https://leetcode.com/problems/remove-duplicate-letters
# #medium


class Solution:
    def removeDuplicateLetters(self, s: str) -> str:
        last_occurence = {}
        for index, character in enumerate(s):
            last_occurence[character] = index
        stack: list[str] = []
        seen: set[str] = set()
        for index, character in enumerate(s):
            if character in seen:
                continue
            while stack and character < stack[-1] and index < last_occurence[stack[-1]]:
                seen.remove(stack.pop())
            seen.add(character)
            stack.append(character)
        return "".join(stack)


class TestCode:
    def test_example_1(self) -> None:
        assert Solution().removeDuplicateLetters("bcabc") == "abc"

    def test_example_2(self) -> None:
        assert Solution().removeDuplicateLetters("cbacdcbc") == "acdb"

    def test_cdadabcc(self) -> None:
        assert Solution().removeDuplicateLetters("cdadabcc") == "adbc"

    def test_ccacbaba(self) -> None:
        assert Solution().removeDuplicateLetters("ccacbaba") == "acb"

    def test_abacb(self) -> None:
        assert Solution().removeDuplicateLetters("abacb") == "abc"
