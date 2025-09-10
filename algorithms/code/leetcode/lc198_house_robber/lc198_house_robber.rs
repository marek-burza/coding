// https://leetcode.com/problems/house-robber/
// #medium

use std::collections::HashMap;

pub struct Solution;

impl Solution {
    fn rob_internal(nums: &[i32], offset: usize, maxed: &mut HashMap<usize, i32>) -> i32 {
        if nums.len() <= offset {
            return 0;
        }
        if let Some(value) = maxed.get(&offset) {
            *value
        } else {
            let mut result = nums[offset] + Self::rob_internal(nums, offset + 2, maxed);
            if offset + 1 < nums.len() {
                let other = nums[offset + 1] + Self::rob_internal(nums, offset + 3, maxed);
                result = result.max(other);
            }
            maxed.insert(offset, result);
            result
        }
    }

    pub fn rob(nums: Vec<i32>) -> i32 {
        Self::rob_internal(&nums, 0, &mut HashMap::new())
    }
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_6_6_4_8_4_3_3_10() {
        let nums = vec![6, 6, 4, 8, 4, 3, 3, 10];
        assert_eq!(Solution::rob(nums), 27);
    }
}
