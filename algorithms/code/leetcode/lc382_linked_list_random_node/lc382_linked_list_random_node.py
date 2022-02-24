#!/usr/bin/env python3
# https://leetcode.com/problems/linked-list-random-node/

import random
import unittest
from typing import Optional


class ListNode:
    def __init__(self, val=0, following=None):
        self.val = val
        self.next = following


class Solution:
    def __init__(self, head: Optional[ListNode]):
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
        return result.val


class TestCode(unittest.TestCase):
    def test_example(self):
        head = ListNode(1)
        head.next = ListNode(2)
        head.next.next = ListNode(3)
        counts = [0] * 3
        solution = Solution(head)
        for _ in range(0, 10000):
            value = solution.getRandom()
            self.assertTrue(1 <= value <= 3)
            counts[value - 1] += 1
        self.assertEqual(3, counts[0] // 1000)
        self.assertEqual(3, counts[1] // 1000)
        self.assertEqual(3, counts[2] // 1000)
        # Should use Chi-squared test
