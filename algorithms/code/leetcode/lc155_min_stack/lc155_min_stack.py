# https://leetcode.com/problems/min-stack/

import unittest


class MinStack:
    def __init__(self) -> None:
        self.stack: list[int] = []
        self.min_stack: list[int] = []

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
    def test_examle(self) -> None:
        solution = MinStack()
        solution.pop()
        assert solution.top() == -1
        assert solution.getMin() == -1
        solution.push(5)
        assert solution.getMin() == 5
        solution.push(4)
        assert solution.getMin() == 4
        solution.pop()
        assert solution.getMin() == 5
        solution.push(3)
        assert solution.getMin() == 3
        solution.top()
        assert solution.getMin() == 3
        solution.push(2)
        assert solution.getMin() == 2
        solution.push(1)
        assert solution.getMin() == 1
