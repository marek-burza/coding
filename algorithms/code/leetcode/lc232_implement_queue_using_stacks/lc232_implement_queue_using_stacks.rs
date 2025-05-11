// https://leetcode.com/problems/implement-queue-using-stacks/
// #easy

#[derive(Default)]
pub struct MyQueue {
    stack: Vec<i32>,
    buffer: Vec<i32>,
}

impl MyQueue {
    pub fn new() -> Self {
        Self {
            stack: Vec::new(),
            buffer: Vec::new(),
        }
    }

    // Push element x to the back of queue.
    pub fn push(&mut self, x: i32) {
        while let Some(item) = self.stack.pop() {
            self.buffer.push(item);
        }
        self.stack.push(x);
        while let Some(item) = self.buffer.pop() {
            self.stack.push(item);
        }
    }

    // Removes the element from in front of queue.
    pub fn pop(&mut self) {
        self.stack.pop();
    }

    // Get the front element.
    pub fn peek(&self) -> i32 {
        *self.stack.last().unwrap()
    }

    // Return whether the queue is empty.
    pub fn empty(&self) -> bool {
        self.stack.is_empty()
    }
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_something() {
        let mut queue = MyQueue::new();
        for i in 0..6 {
            queue.push(i);
        }
        for i in 0..6 {
            assert!(!queue.empty());
            assert_eq!(i, queue.peek());
            queue.pop();
        }
        assert!(queue.empty());
    }
}
