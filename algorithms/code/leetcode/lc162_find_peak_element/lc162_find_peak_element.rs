// https://leetcode.com/problems/find-peak-element/
// #medium

pub struct Solution;

impl Solution {
    pub fn find_peak_element(nums: Vec<i32>) -> i32 {
        for i in 1..=nums.len() {
            let post_falling = i == nums.len() || nums[i - 1] > nums[i];
            if post_falling {
                return (i - 1) as i32;
            }
        }
        return -1;
    }
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_1_2_3_1() {
        assert_eq!(Solution::find_peak_element(vec![1, 2, 3, 1]), 2);
    }

    #[test]
    fn test_1_2_3_4() {
        assert_eq!(Solution::find_peak_element(vec![1, 2, 3, 4]), 3);
    }

    #[test]
    fn test_nothing() {
        assert_eq!(Solution::find_peak_element(vec![]), -1);
    }
}
