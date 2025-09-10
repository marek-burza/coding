// https://leetcode.com/problems/power-of-three/
// #easy
// To do it without a loop resort to logarithms (but beware of accuracy)

pub struct Solution;

impl Solution {
    pub fn is_power_of_three(mut n: i32) -> bool {
        if n < 1 {
            return false;
        }
        while 1 < n {
            if n % 3 != 0 {
                return false;
            }
            n /= 3;
        }
        true
    }
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_27() {
        assert!(Solution::is_power_of_three(27));
    }

    #[test]
    fn test_11() {
        assert!(!Solution::is_power_of_three(11));
    }

    #[test]
    fn test_1() {
        assert!(Solution::is_power_of_three(1));
    }

    #[test]
    fn test_0() {
        assert!(!Solution::is_power_of_three(0));
    }

    #[test]
    fn test_minus3() {
        assert!(!Solution::is_power_of_three(-3));
    }
}
