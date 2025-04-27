// https://leetcode.com/problems/min-stack/
// #easy

pub struct MinStack {
    pub stack: Vec<i32>,
    pub min_stack: Vec<i32>,
}

impl Default for MinStack {
    fn default() -> Self {
        Self::new()
    }
}

impl MinStack {
    pub fn new() -> Self {
        Self {
            stack: vec![],
            min_stack: vec![],
        }
    }

    pub fn push(&mut self, val: i32) {
        self.stack.push(val);
        if self.min_stack.is_empty() || val <= *self.min_stack.last().unwrap() {
            self.min_stack.push(val);
        }
    }

    pub fn pop(&mut self) {
        if self.stack.is_empty() {
            return;
        }
        if self.stack.last() == self.min_stack.last() {
            self.min_stack.pop();
        }
        self.stack.pop();
    }

    pub fn top(&self) -> i32 {
        if self.stack.is_empty() {
            return -1;
        }
        *self.stack.last().unwrap()
    }

    pub fn get_min(&self) -> i32 {
        if self.min_stack.is_empty() {
            return -1;
        }
        *self.min_stack.last().unwrap()
    }
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_example() {
        let mut stack = MinStack::default();
        stack.pop();
        assert_eq!(stack.top(), -1);
        assert_eq!(stack.get_min(), -1);
        stack.push(5);
        assert_eq!(stack.get_min(), 5);
        stack.push(4);
        assert_eq!(stack.get_min(), 4);
        stack.pop();
        assert_eq!(stack.get_min(), 5);
        stack.push(3);
        assert_eq!(stack.get_min(), 3);
        stack.top();
        assert_eq!(stack.get_min(), 3);
        stack.push(2);
        assert_eq!(stack.get_min(), 2);
        stack.push(1);
        assert_eq!(stack.get_min(), 1);
    }
}
