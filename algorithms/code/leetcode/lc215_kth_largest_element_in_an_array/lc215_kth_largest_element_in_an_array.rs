// https://leetcode.com/problems/kth-largest-element-in-an-array/
// #medium

use std::cmp::Reverse;
use std::collections::BinaryHeap;

pub struct Solution;

impl Solution {
    pub fn find_kth_largest(nums: Vec<i32>, k: i32) -> i32 {
        let mut heap: BinaryHeap<Reverse<i32>> = BinaryHeap::new();
        for num in nums {
            if (heap.len() as i32) < k || num > *heap.peek().map(|Reverse(num)| num).unwrap() {
                heap.push(Reverse(num));
                if (heap.len() as i32) > k {
                    heap.pop();
                }
            }
        }
        heap.pop().map(|Reverse(num)| num).unwrap()
    }
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_example_1() {
        assert_eq!(5, Solution::find_kth_largest(vec![3, 2, 1, 5, 6, 4], 2));
    }

    #[test]
    fn test_example_2() {
        assert_eq!(1, Solution::find_kth_largest(vec![2, 1], 2));
    }
}
