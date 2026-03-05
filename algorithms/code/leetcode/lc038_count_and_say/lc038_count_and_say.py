# https://leetcode.com/problems/count-and-say/

import unittest


class Solution:
    def countAndSay(self, n: int) -> str:
        if n < 1:
            return ''
        result = '1'
        while n > 1:
            current = ''
            check = '0'
            count = 0
            for character in result:
                if check != character:
                    if count > 0:
                        current += str(count)
                        current += check
                    count = 1
                    check = character
                else:
                    count += 1
            current += str(count)
            current += check
            n -= 1
            result = current
        return result


class TestCode(unittest.TestCase):
    def test_1(self) -> None:
        assert Solution().countAndSay(1) == '1'

    def test_2(self) -> None:
        assert Solution().countAndSay(2) == '11'

    def test_3(self) -> None:
        assert Solution().countAndSay(3) == '21'

    def test_4(self) -> None:
        assert Solution().countAndSay(4) == '1211'

    def test_5(self) -> None:
        assert Solution().countAndSay(5) == '111221'

    def test_0(self) -> None:
        assert Solution().countAndSay(0) == ''
