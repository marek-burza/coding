// https://leetcode.com/problems/ugly-number/
// #easy

pub struct Solution;

impl Solution {
    pub fn is_ugly(mut num: i32) -> bool {
        if num <= 0 {
            return false;
        }
        if num == 1 {
            return true;
        }
        let original = num;
        while num % 2 == 0 {
            num /= 2;
        }
        while num % 3 == 0 {
            num /= 3;
        }
        while num % 5 == 0 {
            num /= 5;
        }
        num != original && num == 1
    }
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_minus() {
        assert!(!Solution::is_ugly(-1));
    }

    #[test]
    fn test_0() {
        assert!(!Solution::is_ugly(0));
    }

    #[test]
    fn test_1() {
        assert!(Solution::is_ugly(1));
    }

    #[test]
    fn test_2() {
        assert!(Solution::is_ugly(2));
    }

    #[test]
    fn test_3() {
        assert!(Solution::is_ugly(3));
    }

    #[test]
    fn test_7() {
        assert!(!Solution::is_ugly(7));
    }

    #[test]
    fn test_11() {
        assert!(!Solution::is_ugly(11));
    }

    #[test]
    fn test_14() {
        assert!(!Solution::is_ugly(14));
    }

    #[test]
    fn test_16() {
        assert!(Solution::is_ugly(16));
    }

    #[test]
    fn test_27() {
        assert!(Solution::is_ugly(27));
    }

    #[test]
    fn test_937351770() {
        assert!(!Solution::is_ugly(937351770));
    }

    #[test]
    fn test_905391974() {
        assert!(!Solution::is_ugly(905391974));
    }
}
