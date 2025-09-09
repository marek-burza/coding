// https://leetcode.com/problems/longest-increasing-subsequence/
// #medium

pub struct Solution;

impl Solution {
    pub fn length_of_lis(nums: Vec<i32>) -> i32 {
        if nums.is_empty() {
            return 0;
        }
        let mut lis = vec![1; nums.len()];
        for i in 1..nums.len() {
            for j in 0..i {
                if nums[i] > nums[j] {
                    lis[i] = std::cmp::max(lis[i], lis[j] + 1);
                }
            }
        }
        let mut max = i32::MIN;
        for value in lis {
            max = std::cmp::max(max, value);
        }
        max
    }
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_example() {
        let nums = vec![10, 9, 2, 5, 3, 7, 101, 18];
        assert_eq!(Solution::length_of_lis(nums), 4);
    }

    #[test]
    fn test_nothing() {
        assert_eq!(Solution::length_of_lis(vec![]), 0);
    }
}
