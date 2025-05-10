// https://leetcode.com/problems/top-k-frequent-elements/
// #medium

use std::collections::HashMap;

pub struct Solution;

impl Solution {
    pub fn top_k_frequent(nums: Vec<i32>, k: i32) -> Vec<i32> {
        let mut frequencies = HashMap::new();
        for num in nums {
            *frequencies.entry(num).or_insert(0) += 1;
        }
        let mut keys: Vec<i32> = frequencies.keys().cloned().collect();
        keys.sort_by(|a, b| frequencies[b].cmp(&frequencies[a]));
        keys.truncate(k as usize);
        keys
    }
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_example() {
        let nums = vec![1, 1, 1, 2, 2, 3];
        let expected = vec![1, 2];
        assert_eq!(Solution::top_k_frequent(nums, 2), expected);
    }
}
