// https://leetcode.com/problems/kth-smallest-element-in-a-sorted-matrix/
// #medium

use std::collections::BinaryHeap;

pub struct Solution;

impl Solution {
    pub fn kth_smallest(matrix: Vec<Vec<i32>>, k: i32) -> i32 {
        let mut heap: BinaryHeap<i32> = BinaryHeap::new();
        for row in matrix.iter() {
            for cell in row.iter() {
                heap.push(*cell);
                if heap.len() as i32 > k {
                    heap.pop();
                }
            }
        }
        heap.pop().unwrap()
    }
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_example() {
        let matrix = vec![vec![1, 5, 9], vec![10, 11, 13], vec![12, 13, 15]];
        assert_eq!(13, Solution::kth_smallest(matrix, 8));
    }
}
