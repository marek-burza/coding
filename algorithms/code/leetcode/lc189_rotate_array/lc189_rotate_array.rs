// https://leetcode.com/problems/rotate-array/
// #medium

pub struct Solution;

impl Solution {
    fn reverse(nums: &mut [i32], mut a: usize, mut b: usize) {
        while a < b {
            (nums[a], nums[b]) = (nums[b], nums[a]);
            a += 1;
            b -= 1;
        }
    }

    #[allow(clippy::ptr_arg)]
    pub fn rotate(nums: &mut Vec<i32>, k: i32) {
        let length = nums.len();
        Self::reverse(nums, 0, length - 1);
        Self::reverse(nums, 0, k as usize - 1);
        Self::reverse(nums, k as usize, length - 1);
    }
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_1234567_and_3() {
        let mut nums = vec![1, 2, 3, 4, 5, 6, 7];
        Solution::rotate(&mut nums, 3);
        let expected = vec![5, 6, 7, 1, 2, 3, 4];
        assert_eq!(nums, expected);
    }

    #[test]
    fn test_123456_and2() {
        let mut nums = vec![1, 2, 3, 4, 5, 6];
        Solution::rotate(&mut nums, 2);
        let expected = vec![5, 6, 1, 2, 3, 4];
        assert_eq!(nums, expected);
    }

    #[test]
    fn test_1_and2() {
        let mut nums = vec![1];
        Solution::rotate(&mut nums, 1);
        let expected = vec![1];
        assert_eq!(nums, expected);
    }
}
