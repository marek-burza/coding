// https://leetcode.com/problems/contains-duplicate/
// #easy

use std::collections::HashSet;

pub struct Solution;

impl Solution {
    pub fn contains_duplicate(nums: Vec<i32>) -> bool {
        let mut seen = HashSet::new();
        for num in nums {
            if seen.contains(&num) {
                return true;
            }
            seen.insert(num);
        }
        false
    }
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_0_5_7() {
        assert!(!Solution::contains_duplicate(vec![0, 5, 7]));
    }

    #[test]
    fn test_0_5_7_5_10() {
        assert!(Solution::contains_duplicate(vec![0, 5, 7, 5, 10]));
    }
}
