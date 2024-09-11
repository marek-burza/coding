// https://leetcode.com/problems/valid-parentheses/

use std::collections::HashMap;

pub struct Solution;

impl Solution {
    pub fn is_valid(s: String) -> bool {
        if s.is_empty() {
            return true;
        }
        let mut stack = vec![];
        let lut: HashMap<char, char> = [(')', '('), ('}', '{'), (']', '[')]
            .iter()
            .cloned()
            .collect();
        for character in s.chars() {
            if lut.values().any(|&value| value == character) {
                stack.push(character);
            } else if stack.is_empty() || *lut.get(&character).unwrap() != stack.pop().unwrap() {
                return false;
            }
        }
        stack.is_empty()
    }
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_garbage() {
        assert!(!Solution::is_valid("*".to_string()));
    }

    #[test]
    fn test_rb() {
        assert!(!Solution::is_valid("(".to_string()));
    }

    #[test]
    fn test_re() {
        assert!(!Solution::is_valid(")".to_string()));
    }

    #[test]
    fn test_rb_re() {
        assert!(Solution::is_valid("()".to_string()));
    }

    #[test]
    fn test_rb_re_sb_se_cb_ce() {
        assert!(Solution::is_valid("()[]{}".to_string()));
    }

    #[test]
    fn test_rb_sb_re_se() {
        assert!(!Solution::is_valid("([)]".to_string()));
    }

    #[test]
    fn test_rb_cb_re_ce() {
        assert!(!Solution::is_valid("({)}".to_string()));
    }

    #[test]
    fn test_sb_rb_se_re() {
        assert!(!Solution::is_valid("[(])".to_string()));
    }

    #[test]
    fn test_cb_rb_ce_re() {
        assert!(!Solution::is_valid("{(})".to_string()));
    }

    #[test]
    fn test_re_se_ce() {
        assert!(!Solution::is_valid(")".to_string()));
        assert!(!Solution::is_valid("]".to_string()));
        assert!(!Solution::is_valid("}".to_string()));
    }

    #[test]
    fn test_nothing() {
        assert!(Solution::is_valid("".to_string()));
    }
}
