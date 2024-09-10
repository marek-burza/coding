// https://leetcode.com/problems/palindrome-number/

pub struct Solution {}

impl Solution {
    pub fn is_palindrome(mut x: i32) -> bool {
        if x < 0 {
            return false;
        }
        let xa: i32 = x;
        let mut xb: i32 = 0;
        while x > 0 {
            xb = xb * 10 + x % 10;
            x /= 10;
        }
        xa == xb
    }
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_213() {
        assert!(!Solution::is_palindrome(213));
    }

    #[test]
    fn test_456() {
        assert!(!Solution::is_palindrome(456));
    }

    #[test]
    fn test_454() {
        assert!(Solution::is_palindrome(454));
    }

    #[test]
    fn test_99() {
        assert!(Solution::is_palindrome(99));
    }

    #[test]
    fn test_1() {
        assert!(Solution::is_palindrome(1));
    }

    #[test]
    fn test_10() {
        assert!(!Solution::is_palindrome(10));
    }

    #[test]
    fn test_minus1() {
        assert!(!Solution::is_palindrome(-1));
    }

    #[test]
    fn test_0() {
        assert!(Solution::is_palindrome(0));
    }
}
