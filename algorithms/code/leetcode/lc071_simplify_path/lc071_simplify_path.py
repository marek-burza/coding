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
        self.assertEqual('/home', Solution().simplifyPath('/home/'))

    def test_example_2(self) -> None:
        self.assertEqual('/home/foo', Solution().simplifyPath('/home//foo/'))

    def test_example_3(self) -> None:
        self.assertEqual(
            '/home/user/Pictures',
            Solution().simplifyPath('/home/user/Documents/../Pictures'),
        )

    def test_example_4(self) -> None:
        self.assertEqual('/', Solution().simplifyPath('/../'))

    def test_example_5(self) -> None:
        self.assertEqual(
            '/.../b/d', Solution().simplifyPath('/.../a/../b/c/../d/./')
        )
