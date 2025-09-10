// https://leetcode.com/problems/isomorphic-strings/
// #easy

use std::collections::HashMap;

pub struct Solution;

impl Solution {
    pub fn is_isomorphic(s: String, t: String) -> bool {
        let mut map = HashMap::new();
        for (source, target) in s.chars().zip(t.chars()) {
            if let Some(existing_target) = map.get(&source) {
                if target != *existing_target {
                    return false;
                }
            } else {
                if map.values().any(|some_target| *some_target == target) {
                    return false;
                }
                map.insert(source, target);
            }
        }
        true
    }
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_aa_and_ab() {
        assert!(!Solution::is_isomorphic("aa".to_string(), "ab".to_string()));
    }

    #[test]
    fn test_egg_and_add() {
        assert!(Solution::is_isomorphic(
            "egg".to_string(),
            "add".to_string(),
        ));
    }

    #[test]
    fn test_ac_and_bb() {
        assert!(!Solution::is_isomorphic("ac".to_string(), "bb".to_string()));
    }
}
