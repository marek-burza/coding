# https://leetcode.com/problems/add-two-numbers/

import unittest
from typing import Optional


class ListNode:
    def __init__(
        self, val: int = 0, next_item: Optional['ListNode'] = None
    ) -> None:
        self.val = val
        self.next = next_item


class Solution:
    def addTwoNumbers(self, l1: ListNode, l2: ListNode) -> ListNode:
        handle = ListNode(0)
        handle.next = None
        tail = handle
        carry = 0
        n1: ListNode | None = l1
        n2: ListNode | None = l2
        while n1 is not None or n2 is not None or carry != 0:
            if n1 is None:
                value1 = 0
            else:
                value1 = n1.val
                n1 = n1.next
            if n2 is None:
                value2 = 0
            else:
                value2 = n2.val
                n2 = n2.next
            summed = carry + value1 + value2
            tail.next = ListNode(summed % 10)
            tail.next.next = None
            tail = tail.next
            carry = summed // 10
        assert handle.next is not None
        return handle.next


class TestCode(unittest.TestCase):
    @staticmethod
    def linked_to_listed(linked: ListNode | None) -> list[int]:
        listed: list[int] = []
        while linked is not None:
            listed.append(linked.val)
            linked = linked.next
        return listed

    @staticmethod
    def listed_to_linked(listed: list[int]) -> ListNode:
        linked: ListNode | None = None
        for value in listed[::-1]:
            linked = ListNode(value, linked)
        assert linked is not None
        return linked

    def test_example(self) -> None:
        array1 = [2, 4, 3]
        array2 = [5, 6, 4]
        linked = Solution().addTwoNumbers(
            TestCode.listed_to_linked(array1),
            TestCode.listed_to_linked(array2),
        )
        expected = [7, 0, 8]
        assert TestCode.linked_to_listed(linked) == expected

    def test_uneven(self) -> None:
        array1 = [2, 4]
        array2 = [5, 6, 4]
        linked = Solution().addTwoNumbers(
            TestCode.listed_to_linked(array1),
            TestCode.listed_to_linked(array2),
        )
        expected = [7, 0, 5]
        assert TestCode.linked_to_listed(linked) == expected

    def test_carry(self) -> None:
        array1 = [2, 4]
        array2 = [5, 6]
        linked = Solution().addTwoNumbers(
            TestCode.listed_to_linked(array1),
            TestCode.listed_to_linked(array2),
        )
        expected = [7, 0, 1]
        assert TestCode.linked_to_listed(linked) == expected
