#!/usr/bin/env python3
# https://leetcode.com/problems/delete-node-in-a-linked-list/

import unittest


class ListNode:
    def __init__(self, x):
        self.val = x
        self.next = None


class Solution:
    def deleteNode(self, node):
        node.val = node.next.val
        node.next = node.next.next


class TestCode(unittest.TestCase):
    def test_example(self):
        n1 = ListNode(1)
        n2 = ListNode(2)
        n3 = ListNode(3)
        n4 = ListNode(4)
        n1.next = n2
        n2.next = n3
        n3.next = n4
        Solution().deleteNode(n3)
        expected = [1, 2, 4]
        n = n1
        for value in expected:
            self.assertIsNotNone(n)
            self.assertEqual(value, n.val)
            n = n.next
        self.assertIsNone(n)
