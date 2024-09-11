// https://leetcode.com/problems/longest-common-prefix/

pub struct Solution;

impl Solution {
    pub fn longest_common_prefix(strs: Vec<String>) -> String {
        if strs.is_empty() {
            return String::new();
        }
        for i in 0..strs[0].len() {
            let char_at_i = strs[0].chars().nth(i).unwrap();
            for str in &strs {
                if str.is_empty() || i >= str.len() || str.chars().nth(i).unwrap() != char_at_i {
                    return strs[0][0..i].to_string();
                }
            }
        }
        strs[0].clone()
    }
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_ala_alamakota() {
        assert_eq!(
            Solution::longest_common_prefix(vec!["Ala".to_string(), "Ala Ma Kota".to_string()]),
            "Ala".to_string()
        );
    }

    #[test]
    fn test_aa_a() {
        assert_eq!(
            Solution::longest_common_prefix(vec!["aa".to_string(), "a".to_string()]),
            "a".to_string()
        );
    }

    #[test]
    fn test_ab_aa_coverage() {
        assert_eq!(
            Solution::longest_common_prefix(vec!["ab".to_string(), "aa".to_string()]),
            "a".to_string()
        );
    }

    #[test]
    fn test_none() {
        assert_eq!(
            Solution::longest_common_prefix(Vec::<String>::new()),
            "".to_string()
        );
    }

    #[test]
    fn test_empty_b() {
        assert_eq!(
            Solution::longest_common_prefix(vec!["".to_string(), "b".to_string()]),
            "".to_string()
        );
    }

    #[test]
    fn test_same() {
        assert_eq!(
            Solution::longest_common_prefix(vec!["same".to_string(), "same".to_string()]),
            "same".to_string()
        );
    }
}
