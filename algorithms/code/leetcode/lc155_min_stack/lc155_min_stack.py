#!/usr/bin/env python3
# https://leetcode.com/problems/min-stack/

import unittest


class MinStack:
    def __init__(self):
        self.stack = []
        self.min_stack = []

    def push(self, val: int) -> None:
        self.stack.append(val)
        if not self.min_stack or val <= self.min_stack[-1]:
            self.min_stack.append(val)

    def pop(self) -> None:
        if not self.stack:
            return
        if self.stack[-1] == self.min_stack[-1]:
            self.min_stack.pop()
        self.stack.pop()

    def top(self) -> int:
        if not self.stack:
            return -1
        return self.stack[-1]

    def getMin(self) -> int:
        if not self.min_stack:
            return -1
        return self.min_stack[-1]


class TestCode(unittest.TestCase):
    def test_examle(self):
        solution = MinStack()
        solution.pop()
        self.assertEqual(-1, solution.top())
        self.assertEqual(-1, solution.getMin())
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
