// https://leetcode.com/problems/move-zeroes/
// #easy

pub struct Solution;

impl Solution {
    #[allow(clippy::ptr_arg)]
    pub fn move_zeroes(nums: &mut Vec<i32>) {
        let mut target = 0;
        for index in 0..nums.len() {
            nums[target] = nums[index];
            if nums[index] != 0 {
                target += 1;
            }
        }
        for num_i in nums.iter_mut().skip(target) {
            *num_i = 0;
        }
    }
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_example() {
        let mut nums = vec![0, 1, 0, 3, 12];
        Solution::move_zeroes(&mut nums);
        let expected = vec![1, 3, 12, 0, 0];
        assert_eq!(nums, expected);
    }
}
