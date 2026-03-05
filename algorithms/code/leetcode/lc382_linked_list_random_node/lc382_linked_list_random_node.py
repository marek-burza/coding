# https://leetcode.com/problems/linked-list-random-node/

from __future__ import annotations

import random
import unittest


class ListNode:
    def __init__(
        self, val: int = 0, following: 'ListNode | None' = None
    ) -> None:
        self.val = val
        self.next = following


class Solution:
    def __init__(self, head: ListNode | None) -> None:
        self.__head = head

    def getRandom(self) -> int:
        result = None
        current = self.__head
        i = 1
        while current is not None:
            if random.SystemRandom().randint(0, i - 1) == 0:
                result = current
            i += 1
            current = current.next
        if result is not None:
            return result.val
        raise ValueError('Empty list')


class TestCode(unittest.TestCase):
    def test_example(self) -> None:
        head = ListNode(1)
        head.next = ListNode(2)
        head.next.next = ListNode(3)
        counts = [0] * 3
        solution = Solution(head)
        for _ in range(0, 10000):
            value = solution.getRandom()
            assert 1 <= value <= 3
            counts[value - 1] += 1
        assert counts[0] // 1000 == 3
        assert counts[1] // 1000 == 3
        assert counts[2] // 1000 == 3
        # Should use Chi-squared test

    def test_nothing(self) -> None:
        solution = Solution(None)
        try:
            solution.getRandom()
        except ValueError:
            pass
