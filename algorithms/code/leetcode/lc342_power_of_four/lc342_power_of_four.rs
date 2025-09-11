// https://leetcode.com/problems/power-of-four/
// #easy

pub struct Solution;

impl Solution {
    pub fn is_power_of_four(num: i32) -> bool {
        if num <= 0 {
            return false;
        }
        let value = (num as f64).log2() / 4.0f64.log2();
        value == value.floor()
    }
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_16() {
        assert!(Solution::is_power_of_four(16));
    }

    #[test]
    fn test_5() {
        assert!(!Solution::is_power_of_four(5));
    }

    #[test]
    fn test_non_positive() {
        assert!(!Solution::is_power_of_four(0));
        assert!(!Solution::is_power_of_four(-1));
    }
}
