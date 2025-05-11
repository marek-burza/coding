// https://leetcode.com/problems/implement-stack-using-queues/
// #easy

use std::collections::VecDeque;
use std::mem;

#[derive(Default)]
pub struct Solution {
    active: VecDeque<i32>,
    other: VecDeque<i32>,
}

impl Solution {
    pub fn new() -> Self {
        Self {
            active: VecDeque::new(),
            other: VecDeque::new(),
        }
    }

    fn swap(&mut self) {
        mem::swap(&mut self.active, &mut self.other);
    }

    // Push element x onto stack.
    pub fn push(&mut self, x: i32) {
        self.other.push_back(x);
        while !self.active.is_empty() {
            self.other.push_back(self.active.pop_front().unwrap());
        }
        self.swap();
    }

    // Removes the element on top of the stack.
    pub fn pop(&mut self) {
        self.active.pop_front();
    }

    // Get the top element.
    pub fn top(&self) -> i32 {
        *self.active.front().unwrap()
    }

    // Return whether the stack is empty.
    pub fn empty(&self) -> bool {
        self.active.is_empty()
    }

    // It's also possible with just one stack
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_example() {
        let mut solution = Solution::new();
        solution.push(5);
        solution.push(2);
        assert_eq!(solution.top(), 2);
        solution.pop();
        assert_eq!(solution.top(), 5);
        assert!(!solution.empty());
        solution.pop();
        assert!(solution.empty());
    }
}
