// https://leetcode.com/problems/word-pattern/
// #easy

use std::collections::HashMap;

pub struct Solution;

impl Solution {
    fn check(first: String, second: String, mapping: &mut HashMap<String, String>) -> bool {
        #[allow(clippy::map_entry)]
        if mapping.contains_key(&first) {
            if *mapping.get(&first).unwrap() != second {
                return false;
            }
        } else {
            mapping.insert(first, second);
        }
        true
    }

    pub fn word_pattern(pattern: String, s: String) -> bool {
        let words = s.split(" ");
        if pattern.len() != words.clone().count() {
            return false;
        }
        let mut mapping_ps = HashMap::new();
        let mut mapping_sp = HashMap::new();
        for (i, words_i) in words.enumerate() {
            let key = &pattern[i..i + 1];
            if !Self::check(key.into(), words_i.into(), &mut mapping_ps)
                || !Self::check(words_i.into(), key.into(), &mut mapping_sp)
            {
                return false;
            }
        }
        true
    }
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_abba_dog_cat_cat_dog() {
        assert!(Solution::word_pattern(
            "abba".into(),
            "dog cat cat dog".into()
        ));
    }

    #[test]
    fn test_abba_dog_cat_cat_fish() {
        assert!(!Solution::word_pattern(
            "abba".into(),
            "dog cat cat fish".into()
        ));
    }

    #[test]
    fn test_aaaa_dog_cat_cat_dog() {
        assert!(!Solution::word_pattern(
            "aaaa".into(),
            "dog cat cat dog".into()
        ));
    }

    #[test]
    fn test_abba_dog_dog_dog_dog() {
        assert!(!Solution::word_pattern(
            "abba".into(),
            "dog dog dog dog".into()
        ));
    }

    #[test]
    fn test_ab_bc() {
        assert!(Solution::word_pattern("ab".into(), "b c".into()));
    }

    #[test]
    fn test_mismatched() {
        assert!(!Solution::word_pattern("ab".into(), "c".into()));
    }
}
