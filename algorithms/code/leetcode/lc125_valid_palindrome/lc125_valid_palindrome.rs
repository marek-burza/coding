// https://leetcode.com/problems/valid-palindrome/
// #easy

pub struct Solution;

impl Solution {
    pub fn is_palindrome(s: String) -> bool {
        if s.is_empty() {
            return true;
        }
        let mut i = 0;
        let mut j = s.len() - 1;
        while i <= j {
            let a = s.chars().nth(i).unwrap();
            if !a.is_ascii_alphanumeric() {
                i += 1;
                continue;
            }
            let b = s.chars().nth(j).unwrap();
            if !b.is_ascii_alphanumeric() {
                j -= 1;
                continue;
            }
            if !a.eq_ignore_ascii_case(&b) {
                return false;
            } else {
                i += 1;
                j -= 1;
            }
        }
        true
    }
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_a_man_a_plan_a_canal_panama() {
        assert!(Solution::is_palindrome(
            "A man, a plan, a canal: Panama".to_string()
        ));
    }

    #[test]
    fn test_race_a_car() {
        assert!(!Solution::is_palindrome("race a car".to_string()));
    }

    #[test]
    fn test_ava() {
        assert!(Solution::is_palindrome("Ava".to_string()));
    }

    #[test]
    fn test_burger() {
        assert!(!Solution::is_palindrome("burger".to_string()));
    }

    #[test]
    fn test_nothing() {
        assert!(Solution::is_palindrome("".to_string()));
    }
}
