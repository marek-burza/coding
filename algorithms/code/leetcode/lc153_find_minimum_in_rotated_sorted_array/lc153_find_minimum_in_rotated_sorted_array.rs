// https://leetcode.com/problems/find-minimum-in-rotated-sorted-array/
// #medium

pub struct Solution;

impl Solution {
    pub fn find_min(nums: Vec<i32>) -> i32 {
        let mut a = 0;
        let mut z = nums.len() - 1;
        while a != z {
            // For sorted array return the smallest
            if nums[a] < nums[z] {
                return nums[a];
            }
            // For only two elements pick the smaller
            if z - a == 1 {
                return std::cmp::min(nums[a], nums[z]);
            }
            // Otherwise halve the search space
            let m = (a + z) / 2;
            if nums[a] < nums[m] {
                a = m;
            }
            if nums[m] < nums[z] {
                z = m;
            }
        }
        nums[a]
    }
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_0124567() {
        let nums = vec![0, 1, 2, 4, 5, 6, 7];
        assert_eq!(Solution::find_min(nums), 0);
    }

    #[test]
    fn test_4567012() {
        let nums = vec![4, 5, 6, 7, 0, 1, 2];
        assert_eq!(Solution::find_min(nums), 0);
    }

    #[test]
    fn test_12() {
        let nums = vec![1, 2];
        assert_eq!(Solution::find_min(nums), 1);
    }

    #[test]
    fn test_21() {
        let nums = vec![2, 1];
        assert_eq!(Solution::find_min(nums), 1);
    }

    #[test]
    fn test_1() {
        let nums = vec![1];
        assert_eq!(Solution::find_min(nums), 1);
    }
}
