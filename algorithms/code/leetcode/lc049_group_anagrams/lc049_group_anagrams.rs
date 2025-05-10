// https://leetcode.com/problems/group-anagrams/
// #medium

use std::collections::HashMap;

pub struct Solution;

impl Solution {
    pub fn group_anagrams(strs: Vec<String>) -> Vec<Vec<String>> {
        let mut seen = HashMap::<String, Vec<String>>::new();
        for s in strs {
            let mut array: Vec<char> = s.chars().collect();
            array.sort_unstable();
            let key: String = array.iter().collect();
            if let Some(group) = seen.get_mut(&key) {
                group.push(s);
            } else {
                seen.insert(key, vec![s]);
            }
        }
        seen.into_values()
            .map(|mut group| {
                group.sort();
                group
            })
            .collect()
    }
}

#[cfg(test)]
mod tests {
    use super::*;

    use std::cmp::Ordering;

    pub fn compare_orderly(l1: &Vec<String>, l2: &Vec<String>) -> Ordering {
        let difference = l1.len().cmp(&l2.len());
        if difference != std::cmp::Ordering::Equal {
            return difference;
        }
        for (a, b) in l1.iter().zip(l2.iter()) {
            let difference = a.cmp(b);
            if difference != std::cmp::Ordering::Equal {
                return difference;
            }
        }
        std::cmp::Ordering::Equal
    }

    #[test]
    fn test_abc_cab_bad_dab_zzz_dot() {
        let input = vec![
            "abc".to_string(),
            "cab".to_string(),
            "bad".to_string(),
            "dab".to_string(),
            "zzz".to_string(),
            "dot".to_string(),
        ];
        let expected = vec![
            vec!["dot".to_string()],
            vec!["zzz".to_string()],
            vec!["abc".to_string(), "cab".to_string()],
            vec!["bad".to_string(), "dab".to_string()],
        ];
        let mut result = Solution::group_anagrams(input);
        result.sort_by(compare_orderly);
        assert_eq!(result, expected);
    }

    #[test]
    fn test_tea_and_ate_eat_den() {
        let input = vec![
            "tea".to_string(),
            "and".to_string(),
            "ate".to_string(),
            "eat".to_string(),
            "den".to_string(),
        ];
        let expected = vec![
            vec!["and".to_string()],
            vec!["den".to_string()],
            vec!["ate".to_string(), "eat".to_string(), "tea".to_string()],
        ];
        let mut result = Solution::group_anagrams(input);
        result.sort_by(compare_orderly);
        assert_eq!(result, expected);
    }

    #[test]
    fn test_comparator() {
        assert_eq!(
            compare_orderly(
                &vec!["1".to_string(), "2".to_string()],
                &vec!["1".to_string(), "2".to_string()]
            ),
            Ordering::Equal
        );
    }
}
