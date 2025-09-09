// https://leetcode.com/problems/combination-sum-iv/
// #medium
// #dynamic-programming

pub struct Solution;

impl Solution {
    pub fn combination_sum4(nums: Vec<i32>, target: i32) -> i32 {
        let mut cache = vec![0; target as usize + 1];
        cache[0] = 1;
        for i in 0..target {
            if cache[i as usize] == 0 {
                continue;
            }
            for num in &nums {
                if i + num <= target {
                    cache[(i + num) as usize] += cache[i as usize];
                }
            }
        }
        cache[target as usize]
    }
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_example() {
        let nums = vec![1, 2, 3];
        assert_eq!(Solution::combination_sum4(nums, 4), 7);
    }

    #[test]
    fn test_longer_example() {
        let nums = vec![4, 2, 1];
        assert_eq!(Solution::combination_sum4(nums, 32), 39882198);
    }

    #[test]
    fn test_with_gaps() {
        let nums = vec![3, 2];
        assert_eq!(Solution::combination_sum4(nums, 15), 28);
    }
}
