// https://leetcode.com/problems/remove-duplicates-from-sorted-array/

pub struct Solution;

impl Solution {
    pub fn remove_duplicates(nums: &mut [i32]) -> i32 {
        if nums.is_empty() {
            return 0;
        }
        let mut counter = 0;
        for i in 1..nums.len() {
            let spot = counter;
            if nums[i] == nums[i - 1 - spot] {
                counter += 1;
            }
            nums[i - spot] = nums[i];
        }
        (nums.len() - counter) as i32
    }
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_1_2_2_3_4_4_7() {
        let mut nums = vec![1, 2, 2, 3, 4, 4, 7];
        let expected = 5;
        let result = Solution::remove_duplicates(&mut nums);
        assert_eq!(result, expected);
        assert_eq!(&nums[..result as usize], &vec![1, 2, 3, 4, 7]);
    }

    #[test]
    fn test_nothing() {
        let mut nums: Vec<i32> = vec![];
        let expected = 0;
        let result = Solution::remove_duplicates(&mut nums);
        assert_eq!(result, expected);
    }
}
