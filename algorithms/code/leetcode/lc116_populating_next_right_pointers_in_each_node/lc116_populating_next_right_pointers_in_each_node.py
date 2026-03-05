# https://leetcode.com/problems/populating-next-right-pointers-in-each-node/

from __future__ import annotations

import unittest


class Node:
    def __init__(
        self,
        val: int = 0,
        left: 'Node | None' = None,
        right: 'Node | None' = None,
        following: 'Node | None' = None,
    ) -> None:
        self.val = val
        self.left = left
        self.right = right
        self.next = following


class Solution:
    def connect(self, root: Node | None) -> Node | None:
        current = []
        if root is not None:
            current.append(root)
        while current:
            future = []
            size = len(current)
            for i in range(size):
                node = current[i]
                if node.left is not None:
                    future.append(node.left)
                if node.right is not None:
                    future.append(node.right)
                if i < size - 1:
                    node.next = current[i + 1]
            current = future
        return root


class TestCode(unittest.TestCase):
    def test_empty(self) -> None:
        Solution().connect(None)

    def test_example(self) -> None:
        n4 = Node(4)
        n5 = Node(5)
        n6 = Node(6)
        n7 = Node(7)
        n2 = Node(2)
        n2.left = n4
        n2.right = n5
        n3 = Node(3)
        n3.left = n6
        n3.right = n7
        n1 = Node(1)
        n1.left = n2
        n1.right = n3

        Solution().connect(n1)

        assert n1.val == 1
        assert n1.next is None
        assert n2 == n1.left
        assert n3 == n1.right

        assert n2.val == 2
        assert n3 == n2.next
        assert n4 == n2.left
        assert n5 == n2.right

        assert n3.val == 3
        assert n3.next is None
        assert n6 == n3.left
        assert n7 == n3.right

        assert n4.val == 4
        assert n5 == n4.next
        assert n4.left is None
        assert n4.right is None

        assert n5.val == 5
        assert n6 == n5.next
        assert n5.left is None
        assert n5.right is None

        assert n6.val == 6
        assert n7 == n6.next
        assert n6.left is None
        assert n6.right is None

        assert n7.val == 7
        assert n7.next is None
        assert n7.left is None
        assert n7.right is None
