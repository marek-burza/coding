// https://leetcode.com/problems/ugly-number-ii/
// #medium

use std::cmp::Reverse;
use std::collections::BinaryHeap;

pub struct Solution;

impl Solution {
    pub fn nth_ugly_number(n: i32) -> i64 {
        if n == 1 {
            return 1;
        }
        let mut uglies: BinaryHeap<Reverse<i64>> = BinaryHeap::new();
        uglies.push(Reverse(1));
        for _ in 1..n {
            let smallest = uglies.pop().unwrap().0;
            while !uglies.is_empty() && uglies.peek().unwrap().0 == smallest {
                uglies.pop();
            }
            uglies.push(Reverse(smallest * 2));
            uglies.push(Reverse(smallest * 3));
            uglies.push(Reverse(smallest * 5));
        }
        uglies.pop().unwrap().0
    }
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_example() {
        let expected = vec![1, 2, 3, 4, 5, 6, 8, 9, 10, 12];
        for i in 0..expected.len() {
            assert_eq!(expected[i], Solution::nth_ugly_number(i as i32 + 1));
        }
    }

    #[test]
    fn test_bigger() {
        assert_eq!(536870912, Solution::nth_ugly_number(1407));
    }
}
