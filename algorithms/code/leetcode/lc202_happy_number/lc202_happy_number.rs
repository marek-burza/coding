// https://leetcode.com/problems/happy-number/
// #easy

pub struct Solution;

impl Solution {
    fn re(mut n: i32) -> i32 {
        let mut result = 0;
        while n != 0 {
            let digit = n % 10;
            n /= 10;
            result += digit * digit;
        }
        result
    }

    pub fn is_happy(mut n: i32) -> bool {
        let mut seen = std::collections::HashSet::new();
        seen.insert(n);
        while n != 1 {
            n = Self::re(n);
            if seen.contains(&n) {
                return false;
            }
            seen.insert(n);
        }
        true
    }
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_19() {
        assert!(Solution::is_happy(19));
    }

    #[test]
    fn test2() {
        assert!(!Solution::is_happy(2));
    }
}
