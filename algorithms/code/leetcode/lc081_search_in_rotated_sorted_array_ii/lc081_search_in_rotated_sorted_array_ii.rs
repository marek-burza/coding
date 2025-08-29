// https://leetcode.com/problems/search-in-rotated-sorted-array-ii/
// #medium

pub struct Solution;

impl Solution {
    pub fn search(nums: &Vec<i32>, target: i32) -> bool {
        for i in 1..nums.len() {
            if nums[i - 1] > nums[i] {
                let ante = nums[0..i].binary_search(&target).is_ok();
                let post = nums[i..].binary_search(&target).is_ok();
                return ante || post;
            }
        }
        nums.as_slice().binary_search(&target).is_ok()
    }
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_simple_example() {
        let nums = vec![4, 5, 6, 6, 7, 0, 1, 2];
        assert!(Solution::search(&nums, 1));
    }

    #[test]
    fn test_tricky_example() {
        let nums = vec![1, 1, 3, 1, 1, 1, 1, 1];
        assert!(!Solution::search(&nums, 2));
    }

    #[test]
    fn test_one_and_one() {
        let nums = vec![1];
        assert!(Solution::search(&nums, 1));
    }

    #[test]
    fn test_one_and_zero() {
        let nums = vec![1];
        assert!(!Solution::search(&nums, 0));
    }

    #[test]
    fn test_2560012_and_3() {
        let nums = vec![2, 5, 6, 0, 0, 1, 2];
        assert!(!Solution::search(&nums, 3));
    }

    #[test]
    fn test_2560012_and0() {
        let nums = vec![2, 5, 6, 0, 0, 1, 2];
        assert!(Solution::search(&nums, 0));
    }

    #[test]
    fn test_2223222_and3() {
        let nums = vec![2, 2, 2, 3, 2, 2, 2];
        assert!(Solution::search(&nums, 3));
    }
}
