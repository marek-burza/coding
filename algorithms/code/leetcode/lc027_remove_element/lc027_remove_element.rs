// https://leetcode.com/problems/remove-element/

pub struct Solution;

impl Solution {
    pub fn remove_element(nums: &mut [i32], val: i32) -> i32 {
        if nums.is_empty() {
            return 0;
        }
        let mut index = 0;
        for i in 0..nums.len() {
            nums[index] = nums[i];
            if nums[i] != val {
                index += 1;
            }
        }
        index as i32
    }
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_0_42_1_2_42_3_4_and_42() {
        let mut nums = vec![0, 42, 1, 2, 42, 3, 4];
        let length = Solution::remove_element(&mut nums, 42) as usize;
        assert_eq!(length, 5);
        assert_eq!([0, 1, 2, 3, 4], &nums[..length]);
    }

    #[test]
    fn test_nothing() {
        let mut nums: Vec<i32> = vec![];
        let length = Solution::remove_element(&mut nums, 42) as usize;
        assert_eq!(length, 0);
    }
}
