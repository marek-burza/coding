package leetcode.lc225_implement_stack_using_queues;

import java.util.LinkedList;
import java.util.Queue;

/**
 * https://leetcode.com/problems/implement-stack-using-queues/ #easy
 */
public final class LC225ImplementStackUsingQueues {
    private Queue<Integer> active = new LinkedList<>();
    private Queue<Integer> other = new LinkedList<>();

    private void swap() {
        Queue<Integer> swap = other;
        other = active;
        active = swap;
    }

    // Push element x onto stack.
    public void push(final int x) {
        // 1 - active
        other.add(x);
        while (active.size() > 0) { // ! empty
            other.add(active.poll());
        }
        swap();
    }

    // Removes the element on top of the stack.
    public void pop() {
        active.poll();
    }

    // Get the top element.
    public int top() {
        return active.peek();
    }

    // Return whether the stack is empty.
    public boolean empty() {
        return active.isEmpty();
    }

    // It's also possible with just one stack
}
package leetcode.lc225_implement_stack_using_queues;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public final class LC225ImplementStackUsingQueuesTests {
    @Test
    public void testExample() throws Exception {
        LC225ImplementStackUsingQueues solution = new LC225ImplementStackUsingQueues();
        solution.push(5);
        solution.push(2);
        assertEquals(2, solution.top());
        solution.pop();
        assertEquals(5, solution.top());
        assertFalse(solution.empty());
        solution.pop();
        assertTrue(solution.empty());
    }
}
```rust
use std::collections::{VecDeque, BinaryHeap};

pub struct LC225ImplementStackUsingQueues {
    active: VecDeque<i32>,
    other: BinaryHeap<i32>,
}

impl LC225ImplementStackUsingQueues {
    pub fn new() -> Self {
        LC225ImplementStackUsingQueues { active: VecDeque::new(), other: BinaryHeap::new() }
    }

    // Push element x onto stack.
    pub fn push(&mut self, x: i32) {
        // 1 - active
        self.active.push_back(x);
        while !self.active.is_empty() {
            self.other.push(self.active.pop_back().unwrap());
        }
    }

    // Removes the element on top of the stack.
    pub fn pop(&mut self) {
        if let Some(top) = self.active.pop_back() {
            self.other.push(top);
        }
    }

    // Get the top element.
    pub fn top(&self) -> i32 {
        *self.active.back().unwrap()
    }

    // Return whether the stack is empty.
    pub fn empty(&self) -> bool {
        self.active.is_empty()
    }
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_example() {
        let mut solution = LC225ImplementStackUsingQueues::new();
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
```