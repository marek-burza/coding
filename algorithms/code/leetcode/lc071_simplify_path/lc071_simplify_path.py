# https://leetcode.com/problems/simplify-path/

import unittest


class Solution:
    def simplifyPath(self, path: str) -> str:
        filtered: list[str] = []
        for item in path.split('/'):
            if item in ['', '.']:
                continue
            if item == '..':
                if filtered:
                    filtered.pop()
            else:
                filtered.append(item)
        return '/' + '/'.join(filtered)


class TestCode(unittest.TestCase):
    def test_example_1(self) -> None:
        assert Solution().simplifyPath('/home/') == '/home'

    def test_example_2(self) -> None:
        assert Solution().simplifyPath('/home//foo/') == '/home/foo'

    def test_example_3(self) -> None:
        assert (
            Solution().simplifyPath('/home/user/Documents/../Pictures')
            == '/home/user/Pictures'
        )

    def test_example_4(self) -> None:
        assert Solution().simplifyPath('/../') == '/'

    def test_example_5(self) -> None:
        assert Solution().simplifyPath('/.../a/../b/c/../d/./') == '/.../b/d'
