// https://leetcode.com/problems/length-of-last-word/
// #easy

pub struct Solution;

impl Solution {
    pub fn length_of_last_word(s: String) -> i32 {
        if s.is_empty() {
            return 0;
        }
        let mut n = s.len() as i32;
        while n > 0 && s.chars().nth((n - 1) as usize).unwrap() == ' ' {
            n -= 1;
        }
        for i in (0..n).rev() {
            if s.chars().nth(i as usize).unwrap() == ' ' {
                return n - i - 1i32;
            }
        }
        n
    }
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_hello_world() {
        assert_eq!(Solution::length_of_last_word("Hello World".into()), 5);
    }

    #[test]
    fn test_nothing() {
        assert_eq!(Solution::length_of_last_word("".into()), 0);
    }

    #[test]
    fn test_almost_nothing() {
        assert_eq!(Solution::length_of_last_word(" ".into()), 0);
    }

    #[test]
    fn test_trailing_space() {
        assert_eq!(Solution::length_of_last_word("Hello World  ".into()), 5);
    }

    #[test]
    fn test_single_word() {
        assert_eq!(Solution::length_of_last_word("HelloWorld".into()), 10);
    }
}
