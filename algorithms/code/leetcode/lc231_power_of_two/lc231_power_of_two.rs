// https://leetcode.com/problems/power-of-two/
// #easy

pub struct Solution;

impl Solution {
    pub fn is_power_of_two(n: i32) -> bool {
        if n <= 0 {
            return false;
        }
        let mut count = 0;
        let mut mask = 1;
        while mask != 0 {
            count += if (n & mask) == 0 { 0 } else { 1 };
            mask <<= 1;
        }
        count == 1
    }
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_minus10() {
        assert!(!Solution::is_power_of_two(-10));
    }

    #[test]
    fn test_0() {
        assert!(!Solution::is_power_of_two(0));
    }

    #[test]
    fn test_1() {
        assert!(Solution::is_power_of_two(1));
    }

    #[test]
    fn test_2() {
        assert!(Solution::is_power_of_two(2));
    }

    #[test]
    fn test_5() {
        assert!(!Solution::is_power_of_two(5));
    }

    #[test]
    fn test_1024() {
        assert!(Solution::is_power_of_two(1024));
    }
}
