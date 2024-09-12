// https://leetcode.com/problems/generate-parentheses/

pub struct Solution;

impl Solution {
    fn generate(prefix: &str, standing: i32, n: i32, found: &mut Vec<String>) {
        if n == 0 && standing == 0 {
            found.push(prefix.to_string());
            return;
        }
        // open
        if n > 0 {
            let new_prefix = format!("{}(", prefix);
            Self::generate(&new_prefix, standing + 1, n - 1, found)
        }
        // close
        if standing > 0 {
            let new_prefix = format!("{})", prefix);
            Self::generate(&new_prefix, standing - 1, n, found)
        }
    }

    pub fn generate_parenthesis(n: i32) -> Vec<String> {
        let mut found = Vec::new();
        if n != 0 {
            Self::generate("(", 1, n - 1, &mut found);
        }
        found
    }
}

#[cfg(test)]
mod tests {
    use super::*;
    use std::cmp::max;

    fn check(mut expected: Vec<String>, n: i32) {
        let mut result = Solution::generate_parenthesis(n);
        result.sort();
        expected.sort();
        for i in 0..max(result.len(), expected.len()) {
            assert_eq!(result.get(i), Some(&expected[i]));
        }
    }

    #[test]
    fn test_0() {
        let expected = Vec::new();
        check(expected, 0);
    }

    #[test]
    fn test_1() {
        let expected = vec!["()".to_string()];
        check(expected, 1);
    }

    #[test]
    fn test_2() {
        let expected = vec!["()()".to_string(), "(())".to_string()];
        check(expected, 2);
    }

    #[test]
    fn test_3() {
        let expected = vec![
            "((()))".to_string(),
            "(()())".to_string(),
            "(())()".to_string(),
            "()(())".to_string(),
            "()()()".to_string(),
        ];
        check(expected, 3);
    }

    #[test]
    fn test_4() {
        let expected = vec![
            "(((())))".to_string(),
            "((()()))".to_string(),
            "((())())".to_string(),
            "((()))()".to_string(),
            "(()(()))".to_string(),
            "(()()())".to_string(),
            "(()())()".to_string(),
            "(())(())".to_string(),
            "(())()()".to_string(),
            "()((()))".to_string(),
            "()(()())".to_string(),
            "()(())()".to_string(),
            "()()(())".to_string(),
            "()()()()".to_string(),
        ];
        check(expected, 4);
    }
}
