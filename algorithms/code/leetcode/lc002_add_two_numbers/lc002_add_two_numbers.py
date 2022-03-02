#!/usr/bin/env python3
# https://leetcode.com/problems/add-two-numbers/

import unittest
from typing import List


class ListNode:
    def __init__(self, val=0, next_item=None):
        self.val = val
        self.next = next_item


class Solution:
    def addTwoNumbers(self, l1: ListNode, l2: ListNode) -> ListNode:
        handle = ListNode(0)
        handle.next = None
        tail = handle
        carry = 0
        while l1 is not None or l2 is not None or carry != 0:
            if l1 is None:
                value1 = 0
            else:
                value1 = l1.val
                l1 = l1.next
            if l2 is None:
                value2 = 0
            else:
                value2 = l2.val
                l2 = l2.next
            summed = carry + value1 + value2
            tail.next = ListNode(summed % 10)
            tail.next.next = None
            tail = tail.next
            carry = summed // 10
        return handle.next


def thaw(array: List[int]) -> ListNode:
    handle = ListNode()
    following = handle
    previous = ListNode()
    for value in array:
        previous = following
        following = ListNode()
        previous.val = value
        previous.next = following
    previous.next = None
    return handle


def freeze(listed: ListNode) -> List[int]:
    count = 0
    copy = listed
    while copy is not None:
        count += 1
        copy = copy.next
    frozen = [0] * count
    copy = listed
    for i in range(0, count):
        frozen[i] = copy.val
        copy = copy.next
    return frozen


class TestCode(unittest.TestCase):
    def test_example(self):
        array1 = [2, 4, 3]
        array2 = [5, 6, 4]
        listed = Solution().addTwoNumbers(thaw(array1), thaw(array2))
        expected = [7, 0, 8]
        self.assertEqual(freeze(listed), expected)

    def test_uneven(self):
        array1 = [2, 4]
        array2 = [5, 6, 4]
        listed = Solution().addTwoNumbers(thaw(array1), thaw(array2))
        expected = [7, 0, 5]
        self.assertEqual(freeze(listed), expected)

    def test_carry(self):
        array1 = [2, 4]
        array2 = [5, 6]
        listed = Solution().addTwoNumbers(thaw(array1), thaw(array2))
        expected = [7, 0, 1]
        self.assertEqual(freeze(listed), expected)
