// https://leetcode.com/problems/product-of-array-except-self/
// #medium

pub struct Solution;

impl Solution {
    pub fn product_except_self(nums: Vec<i32>) -> Vec<i32> {
        let mut result = vec![0; nums.len()];
        result[0] = 1;
        for i in 1..nums.len() {
            result[i] = result[i - 1] * nums[i - 1];
        }
        let mut other = 1;
        for i in (0..=(nums.len() - 2)).rev() {
            other *= nums[i + 1];
            result[i] *= other;
        }
        result
    }
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_1234() {
        let nums = vec![1, 2, 3, 4];
        let expected = vec![24, 12, 8, 6];
        let actual = Solution::product_except_self(nums);
        assert_eq!(actual, expected);
    }

    #[test]
    fn test_90_minus2() {
        let nums = vec![9, 0, -2];
        let expected = vec![0, -18, 0];
        let actual = Solution::product_except_self(nums);
        assert_eq!(actual, expected);
    }
}
