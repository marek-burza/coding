// https://leetcode.com/problems/increasing-triplet-subsequence/
// #medium

pub struct Solution;

impl Solution {
    pub fn increasing_triplet(nums: Vec<i32>) -> bool {
        if nums.is_empty() || nums.len() < 3 {
            return false;
        }
        let mut min_before = vec![0; nums.len()];
        let mut max_after = vec![0; nums.len()];
        min_before[0] = nums[0];
        max_after[nums.len() - 1] = nums[nums.len() - 1];
        for i in 1..(nums.len() - 1) {
            min_before[i] = std::cmp::min(min_before[i - 1], nums[i - 1]);
            max_after[nums.len() - 1 - i] =
                std::cmp::max(max_after[nums.len() - i], nums[nums.len() - i]);
        }
        for i in 1..(nums.len() - 1) {
            if min_before[i] < nums[i] && nums[i] < max_after[i] {
                return true;
            }
        }
        false
    }
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_empty() {
        assert!(!Solution::increasing_triplet(vec![]));
    }

    #[test]
    fn test_example_1() {
        assert!(Solution::increasing_triplet(vec![1, 2, 3, 4, 5]));
    }

    #[test]
    fn test_example_2() {
        assert!(!Solution::increasing_triplet(vec![5, 4, 3, 2, 1]));
    }

    #[test]
    fn test_other() {
        assert!(Solution::increasing_triplet(vec![1, 2, 3, 1, 2, 1]));
    }

    #[test]
    fn test_nothing() {
        assert!(!Solution::increasing_triplet(vec![0, 1]));
    }

    #[test]
    fn test_5_1_6() {
        assert!(!Solution::increasing_triplet(vec![5, 1, 6]));
    }

    #[test]
    fn test_2_4_minus2_minus3() {
        assert!(!Solution::increasing_triplet(vec![2, 4, -2, -3]));
    }
}
