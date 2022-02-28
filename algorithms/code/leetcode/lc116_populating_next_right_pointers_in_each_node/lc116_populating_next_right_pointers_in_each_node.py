#!/usr/bin/env python3
# https://leetcode.com/problems/populating-next-right-pointers-in-each-node/

import unittest
from typing import Optional


class Node:
    # pylint: disable=C0301
    def __init__(
        self,
        val: int = 0,
        left: 'Node' = None,
        right: 'Node' = None,
        following: 'Node' = None,
    ):  # noqa
        self.val = val
        self.left = left
        self.right = right
        self.next = following


class Solution:
    def connect(self, root: Optional[Node]) -> Optional[Node]:
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
    def test_empty(self):
        Solution().connect(None)

    def test_example(self):
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

        self.assertEqual(1, n1.val)
        self.assertIsNone(n1.next)
        self.assertEqual(n2, n1.left)
        self.assertEqual(n3, n1.right)

        self.assertEqual(2, n2.val)
        self.assertEqual(n3, n2.next)
        self.assertEqual(n4, n2.left)
        self.assertEqual(n5, n2.right)

        self.assertEqual(3, n3.val)
        self.assertEqual(None, n3.next)
        self.assertEqual(n6, n3.left)
        self.assertEqual(n7, n3.right)

        self.assertEqual(4, n4.val)
        self.assertEqual(n5, n4.next)
        self.assertEqual(None, n4.left)
        self.assertEqual(None, n4.right)

        self.assertEqual(5, n5.val)
        self.assertEqual(n6, n5.next)
        self.assertEqual(None, n5.left)
        self.assertEqual(None, n5.right)

        self.assertEqual(6, n6.val)
        self.assertEqual(n7, n6.next)
        self.assertEqual(None, n6.left)
        self.assertEqual(None, n6.right)

        self.assertEqual(7, n7.val)
        self.assertEqual(None, n7.next)
        self.assertEqual(None, n7.left)
        self.assertEqual(None, n7.right)
