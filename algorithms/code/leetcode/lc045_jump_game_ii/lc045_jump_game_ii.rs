// https://leetcode.com/problems/jump-game-ii/
// #medium

pub struct Solution;

impl Solution {
    pub fn jump(nums: Vec<i32>) -> i32 {
        if nums.len() == 1 {
            return 0;
        }
        let mut horizon = nums[0];
        let mut i = 0;
        let mut count = 1;
        while horizon < nums.len() as i32 - 1 {
            let mut replacement = horizon;
            while i <= horizon {
                replacement = replacement.max(i + nums[i as usize]);
                i += 1;
            }
            i -= 1;
            horizon = replacement;
            count += 1;
        }
        count
    }
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_example_1() {
        let nums = vec![2, 3, 1, 1, 4];
        assert_eq!(Solution::jump(nums), 2);
    }

    #[test]
    fn test_nothing() {
        let nums = vec![0];
        assert_eq!(Solution::jump(nums), 0);
    }
}
