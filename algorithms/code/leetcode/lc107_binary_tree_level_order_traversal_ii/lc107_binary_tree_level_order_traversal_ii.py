# https://leetcode.com/problems/binary-tree-level-order-traversal-ii/
# #medium

from __future__ import annotations


class TreeNode:
    def __init__(
        self,
        val: int = 0,
        left: TreeNode | None = None,
        right: TreeNode | None = None,
    ) -> None:
        self.val = val
        self.left = left
        self.right = right


class Solution:
    def levelOrderBottom(self, root: TreeNode | None) -> list[list[int]]:
        result = []
        current = []
        if root is not None:
            current.append(root)
        while current:
            level: list[int] = []
            future = []
            for node in current:
                level.append(node.val)
                if node.left is not None:
                    future.append(node.left)
                if node.right is not None:
                    future.append(node.right)
            result.append(level)
            current = future
        length = len(result)
        for i in range(length // 2):
            result[i], result[length - 1 - i] = result[length - 1 - i], result[i]
        return result


class TestCode:
    def test_empty(self) -> None:
        assert len(Solution().levelOrderBottom(None)) == 0

    def test_example(self) -> None:
        n3 = TreeNode(3)
        n9 = TreeNode(9)
        n20 = TreeNode(20)
        n15 = TreeNode(15)
        n7 = TreeNode(7)
        n3.left = n9
        n3.right = n20
        n20.left = n15
        n20.right = n7
        expected = [[15, 7], [9, 20], [3]]
        result = Solution().levelOrderBottom(n3)
        assert expected == result
