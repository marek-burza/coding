#!/usr/bin/env python3
# https://leetcode.com/problems/min-stack/

import heapq
import unittest


class MinStack:
    def __init__(self):
        self.stack = []
        self.heap = []

    def push(self, val: int) -> None:
        self.stack.append(val)
        heapq.heappush(self.heap, val)

    def pop(self) -> None:
        value = self.stack.pop()
        index = self.heap.index(value)
        self.heap[index], self.heap[-1] = self.heap[-1], self.heap[index]
        self.heap.pop()
        heapq.heapify(self.heap)
        return value

    def top(self) -> int:
        return self.stack[-1]

    def getMin(self) -> int:
        return self.heap[0]


class TestCode(unittest.TestCase):
    def test_examle(self):
        solution = MinStack()
        solution.push(5)
        self.assertEqual(5, solution.getMin())
        solution.push(4)
        self.assertEqual(4, solution.getMin())
        solution.pop()
        self.assertEqual(5, solution.getMin())
        solution.push(3)
        self.assertEqual(3, solution.getMin())
        solution.top()
        self.assertEqual(3, solution.getMin())
        solution.push(2)
        self.assertEqual(2, solution.getMin())
        solution.push(1)
        self.assertEqual(1, solution.getMin())
