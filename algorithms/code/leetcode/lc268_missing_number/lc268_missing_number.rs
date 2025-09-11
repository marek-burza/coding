// https://leetcode.com/problems/missing-number/
// #easy

pub struct Solution;

impl Solution {
    pub fn missing_number(nums: Vec<i32>) -> i32 {
        let expected = (nums.len() as i64 * (nums.len() as i64 + 1)) / 2;
        let mut sum = 0i64;
        for value in nums {
            sum += value as i64;
        }
        (expected - sum) as i32
    }
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_example() {
        assert_eq!(Solution::missing_number(vec![0, 1, 3]), 2);
    }
}
