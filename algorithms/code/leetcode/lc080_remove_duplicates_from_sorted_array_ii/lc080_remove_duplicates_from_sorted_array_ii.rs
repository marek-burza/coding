// https://leetcode.com/problems/remove-duplicates-from-sorted-array-ii/
// #medium

pub struct Solution;

impl Solution {
    #[allow(clippy::ptr_arg)]
    pub fn remove_duplicates(nums: &mut Vec<i32>) -> i32 {
        let mut i = 0;
        for num_index in 0..nums.len() {
            let n = nums[num_index];
            if i < 2 || n > nums[i - 2] {
                nums[i] = n;
                i += 1;
            }
        }
        i as i32
    }
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_example() {
        let mut nums = vec![1, 1, 1, 2, 2, 3];
        let expected = vec![1, 1, 2, 2, 3];
        assert_eq!(Solution::remove_duplicates(&mut nums), 5);
        assert_eq!(nums[..expected.len()], expected[..]);
    }

    #[test]
    fn test_111133() {
        let mut nums = vec![1, 1, 1, 1, 3, 3];
        let expected = vec![1, 1, 3, 3];
        assert_eq!(Solution::remove_duplicates(&mut nums), 4);
        assert_eq!(nums[..expected.len()], expected[..]);
    }

    #[test]
    fn test_11() {
        let mut nums = vec![1, 1];
        let expected = vec![1, 1];
        assert_eq!(Solution::remove_duplicates(&mut nums), 2);
        assert_eq!(nums[..expected.len()], expected[..]);
    }

    #[test]
    fn test_122() {
        let mut nums = vec![1, 2, 2];
        let expected = vec![1, 2, 2];
        assert_eq!(Solution::remove_duplicates(&mut nums), 3);
        assert_eq!(nums[..expected.len()], expected[..]);
    }
}
