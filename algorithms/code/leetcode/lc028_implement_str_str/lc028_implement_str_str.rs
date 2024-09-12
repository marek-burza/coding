// https://leetcode.com/problems/implement-strstr/

pub struct Solution;

impl Solution {
    pub fn str_str(haystack: String, needle: String) -> i32 {
        if haystack.len() < needle.len() {
            return -1;
        }
        for i in 0..=haystack.len() - needle.len() {
            if haystack[i..i + needle.len()] == needle {
                return i as i32;
            }
        }
        -1
    }
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_empty() {
        assert_eq!(Solution::str_str("".to_string(), "".to_string()), 0);
    }

    #[test]
    fn test_mississippi_a() {
        assert_eq!(
            Solution::str_str("mississippi".to_string(), "a".to_string()),
            -1,
        );
    }

    #[test]
    fn test_mississippi_si() {
        assert_eq!(
            Solution::str_str("mississippi".to_string(), "si".to_string()),
            3,
        );
    }

    #[test]
    fn test_bigger_in_smaller() {
        assert_eq!(Solution::str_str("".to_string(), "si".to_string()), -1);
    }
}
