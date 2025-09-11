// https://leetcode.com/problems/sort-colors/
// #medium

pub struct Solution;

impl Solution {
    #[allow(clippy::ptr_arg)]
    pub fn sort_colors(nums: &mut Vec<i32>) {
        let mut counters = [0; 3];
        for value in nums.iter() {
            counters[*value as usize] += 1;
        }
        let mut j = 0;
        for (i, counter_i) in counters.iter().enumerate() {
            for _ in 0..*counter_i {
                nums[j] = i as i32;
                j += 1;
            }
        }
    }
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_2() {
        let mut nums = vec![2];
        let expected = vec![2];
        Solution::sort_colors(&mut nums);
        assert_eq!(nums, expected);
    }

    #[test]
    fn test_10() {
        let mut nums = vec![1, 0];
        let expected = vec![0, 1];
        Solution::sort_colors(&mut nums);
        assert_eq!(nums, expected);
    }
}
