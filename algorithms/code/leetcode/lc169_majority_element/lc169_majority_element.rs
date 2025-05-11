// https://leetcode.com/problems/majority-element/
// #easy

use std::collections::HashMap;

pub struct Solution;

impl Solution {
    pub fn majority_element(nums: &[i32]) -> i32 {
        let mut frequencies = HashMap::new();
        for &value in nums {
            *frequencies.entry(value).or_insert(0) += 1;
        }
        let mut result = i32::MIN;
        let mut count = i32::MIN;
        for (&value, &other) in frequencies.iter() {
            if count <= other {
                result = value;
                count = other;
            }
        }
        result
    }
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_12315161() {
        let nums = vec![1, 2, 3, 1, 5, 1, 6, 1];
        assert_eq!(Solution::majority_element(&nums), 1);
    }
}
