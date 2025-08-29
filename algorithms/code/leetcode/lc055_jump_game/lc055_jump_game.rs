// https://leetcode.com/problems/jump-game/
// #medium
pub struct Solution;

impl Solution {
    pub fn can_jump(nums: Vec<i32>) -> bool {
        if nums.is_empty() {
            return false;
        }
        if nums.len() == 1 {
            return true;
        }
        let mut front = 0i32;
        let mut i = 0i32;
        loop {
            if front >= (nums.len() - 1) as i32 {
                return true;
            }
            if i == front && nums[front as usize] == 0 {
                return false;
            }
            if front < i + nums[i as usize] {
                front = i + nums[i as usize];
            }
            i += 1;
        }
    }
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_25002_integers() {
        let mut nums1 = vec![0; 25003];
        for i in 0..nums1.len() {
            nums1[i] = 25000i32 - i as i32;
        }
        nums1[25000] = 1;
        nums1[25001] = 0;
        nums1[25002] = 0;
        assert!(!Solution::can_jump(nums1));
    }

    #[test]
    fn test_123() {
        let nums2 = vec![1, 2, 3];
        assert!(Solution::can_jump(nums2));
    }

    #[test]
    fn test_nothing() {
        assert!(!Solution::can_jump(vec![]));
        assert!(Solution::can_jump(vec![0]));
    }
}
