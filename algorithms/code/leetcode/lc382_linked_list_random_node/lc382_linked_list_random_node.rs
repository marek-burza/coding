// https://leetcode.com/problems/linked-list-random-node/
// #medium

use rand::{Rng, rng, rngs::ThreadRng};

#[derive(PartialEq, Eq, Clone, Debug)]
pub struct ListNode {
    pub val: i32,
    pub next: Option<Box<ListNode>>,
}

pub struct Solution {
    rng: ThreadRng,
    head: Option<Box<ListNode>>,
}

impl Solution {
    pub fn new(head: Option<Box<ListNode>>) -> Self {
        Solution { rng: rng(), head }
    }

    pub fn get_random(&mut self) -> i32 {
        let mut result = None;
        let mut current = &self.head;
        for i in 1.. {
            match current {
                Some(node) => {
                    if self.rng.random_range(0..i) == 0 {
                        result = Some(node.val);
                    }
                    current = &node.next;
                }
                None => break,
            }
        }
        result.unwrap_or(0)
    }
}

#[cfg(test)]
mod tests {
    use super::*;

    fn vector_to_list(vector: Vec<i32>) -> Option<Box<ListNode>> {
        let mut list: Option<Box<ListNode>> = None;
        for &value in vector.iter().rev() {
            let node = Box::new(ListNode {
                val: value,
                next: list,
            });
            list = Some(node);
        }
        list
    }

    #[test]
    fn test_example() {
        let list = vector_to_list(vec![1, 2, 3]);
        let mut solution = Solution::new(list);
        let mut counts = [0; 3];
        for _ in 0..10_000 {
            let value = solution.get_random();
            assert!(1 <= value && value <= 3);
            counts[(value - 1) as usize] += 1;
        }
        assert_eq!(3, counts[0] / 1000);
        assert_eq!(3, counts[1] / 1000);
        assert_eq!(3, counts[2] / 1000);
        // Should use Chi-squared test
    }
}
