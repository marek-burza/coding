// https://leetcode.com/problems/single-number/
// #easy

pub struct Solution;

impl Solution {
    pub fn single_number(nums: Vec<i32>) -> i32 {
        let mut result = 0;
        for value in nums {
            result ^= value;
        }
        result
    }
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_1() {
        assert_eq!(Solution::single_number(vec![1]), 1);
    }

    #[test]
    fn test_1_and_2_and_1() {
        assert_eq!(Solution::single_number(vec![1, 2, 1]), 2);
    }
}
