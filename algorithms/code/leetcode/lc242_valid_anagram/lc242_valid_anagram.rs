// https://leetcode.com/problems/valid-anagram/
// #easy

pub struct Solution;

impl Solution {
    pub fn is_anagram(s: String, t: String) -> bool {
        if s.len() != t.len() {
            return false;
        }
        let mut s_chars = s.chars().collect::<Vec<char>>();
        let mut t_chars = t.chars().collect::<Vec<char>>();
        s_chars.sort();
        t_chars.sort();
        s_chars == t_chars
    }
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_aa_a() {
        assert!(!Solution::is_anagram("aa".into(), "a".into()));
    }

    #[test]
    fn test_a_b() {
        assert!(!Solution::is_anagram("a".into(), "b".into()));
    }

    #[test]
    fn test_anagram_nagaram() {
        assert!(Solution::is_anagram("anagram".into(), "nagaram".into()));
    }

    #[test]
    fn test_rat_car() {
        assert!(!Solution::is_anagram("rat".into(), "car".into()));
    }
}
