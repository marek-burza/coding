// https://leetcode.com/problems/longest-substring-without-repeating-characters/

use std::collections::HashSet;

pub struct Solution;

impl Solution {
    pub fn length_of_longest_substring(s: String) -> i32 {
        let mut seen = HashSet::new();
        let mut longest = 0;
        let mut count = 0;
        for (i, found) in s.chars().enumerate() {
            while count > 0 && seen.contains(&found) {
                let other = s.chars().nth(i - count).unwrap();
                seen.remove(&other);
                count -= 1;
            }
            count += 1;
            seen.insert(found);
            longest = std::cmp::max(longest, count as i32);
        }
        longest
    }
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_substring_1() {
        assert_eq!(
            Solution::length_of_longest_substring("bcabcbb".to_string()),
            3
        );
    }

    #[test]
    fn test_substring_2() {
        assert_eq!(
            Solution::length_of_longest_substring("bbbbb".to_string()),
            1
        );
    }

    #[test]
    fn test_substring_3() {
        assert_eq!(Solution::length_of_longest_substring("dvdf".to_string()), 3);
    }
}
