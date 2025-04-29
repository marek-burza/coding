// https://leetcode.com/problems/word-break/
// #medium

use std::collections::HashSet;

pub struct Solution;

impl Solution {
    fn word_break_internal(
        s: &str,
        word_dict: &HashSet<String>,
        at: usize,
        length: usize,
        checked: &mut [bool],
    ) -> bool {
        if checked[at] {
            return false;
        }
        let limit = s.len().min(at + length);
        for i in at + 1..=limit {
            if word_dict.contains(&s[at..i])
                && (i == s.len() || Self::word_break_internal(s, word_dict, i, length, checked))
            {
                return true;
            }
        }
        checked[at] = true;
        false
    }

    pub fn word_break(s: &str, word_dict: Vec<String>) -> bool {
        let length = word_dict.iter().map(|w| w.len()).max().unwrap();
        let mut checked = vec![false; s.len()];
        Self::word_break_internal(s, &HashSet::from_iter(word_dict), 0, length, &mut checked)
    }
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_a_and_a() {
        assert!(Solution::word_break("a", vec!["a".to_string()]));
    }

    #[test]
    fn test_other() {
        assert!(!Solution::word_break(
            "catsandog",
            vec![
                "cats".to_string(),
                "dog".to_string(),
                "sand".to_string(),
                "and".to_string(),
                "cat".to_string(),
            ],
        ));
    }

    #[test]
    fn test_another() {
        assert!(Solution::word_break(
            "leetcode",
            vec!["leet".to_string(), "code".to_string()],
        ));
    }
}
