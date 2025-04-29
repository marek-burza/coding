package leetcode.lc232_implement_queue_using_stacks;

import java.util.Stack;

/**
 * https://leetcode.com/problems/implement-queue-using-stacks/ #easy
 */
public final class LC232ImplementQueueUsingStacks {
    private Stack<Integer> stack = new Stack<>();
    private Stack<Integer> buffer = new Stack<>();

    // Push element x to the back of queue.
    public void push(final int x) {
        while (stack.size() != 0) {
            buffer.push(stack.pop());
        }
        stack.push(x);
        while (buffer.size() != 0) {
            stack.push(buffer.pop());
        }
    }

    // Removes the element from in front of queue.
    public void pop() {
        stack.pop();
    }

    // Get the front element.
    public int peek() {
        return stack.peek();
    }

    // Return whether the queue is empty.
    public boolean empty() {
        return stack.size() == 0;
    }
}
package leetcode.lc232_implement_queue_using_stacks;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public final class LC232ImplementQueueUsingStacksTests {
    @Test
    public void testSomething() throws Exception {
        LC232ImplementQueueUsingStacks queue = new LC232ImplementQueueUsingStacks();
        for (int i = 0; i < 6; i++) {
            queue.push(i);
        }
        for (int i = 0; i < 6; i++) {
            assertFalse(queue.empty());
            assertEquals(i, queue.peek());
            queue.pop();
        }
        assertTrue(queue.empty());
    }
}
```rust
use std::collections::{VecDeque, Vec};

struct LC232ImplementQueueUsingStacks {
    main_stack: VecDeque<i32>,
    aux_stack: Vec<i32>,
}

impl LC232ImplementQueueUsingStacks {
    fn new() -> Self {
        LC232ImplementQueueUsingStacks { main_stack: VecDeque::new(), aux_stack: Vec::new() }
    }

    // Push element x to the back of queue.
    fn push(&mut self, x: i32) {
        while !self.main_stack.is_empty() {
            self	aux_stack.push(self.main_stack.pop_back().unwrap());
        }
        self.main_stack.push_back(x);
        while !self_aux_stack.is_empty() {
            self.main_stack.push_front(self_aux_stack.pop().unwrap());
        }
    }

    // Removes the element from in front of queue.
    fn pop(&mut self) {
        self.main_stack.pop_front();
    }

    // Get the front element.
    fn peek(&self) -> i32 {
        *self.main_stack.front().unwrap()
    }

    // Return whether the queue is empty.
    fn empty(&self) -> bool {
        self.main_stack.is_empty()
    }
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_implement_queue_using_stacks() {
        let mut queue = LC232ImplementQueueUsingStacks::new();
        for i in 0..6 {
            queue.push(i);
        }
        for i in 0..6 {
            assert!(!queue.empty());
            assert_eq!(i, *queue.peek());
            queue.pop();
        }
        assert!(queue.empty());
    }
}
```