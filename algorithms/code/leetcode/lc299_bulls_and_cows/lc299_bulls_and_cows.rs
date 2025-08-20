// https://leetcode.com/problems/bulls-and-cows/
// #medium

use std::collections::HashMap;

pub struct Solution;

impl Solution {
    pub fn get_hint(secret: String, guess: String) -> String {
        let mut count_known = HashMap::<char, i32>::new();
        let mut count_asked = HashMap::<char, i32>::new();
        let mut bulls = 0;
        let mut cows = 0;
        for i in 0..std::cmp::min(secret.chars().count(), guess.chars().count()) {
            // Count characters of each type
            let known = secret.chars().nth(i).unwrap();
            let asked = guess.chars().nth(i).unwrap();
            *count_known.entry(known).or_insert(0) += 1;
            *count_asked.entry(asked).or_insert(0) += 1;
            // Check for a bull
            if known == asked {
                bulls += 1;
            }
        }
        // Count the cows
        for asked in count_asked.keys() {
            if count_known.contains_key(asked) {
                cows += std::cmp::min(
                    count_known.get(asked).unwrap(),
                    count_asked.get(asked).unwrap(),
                );
            }
        }
        // Remove the bulls from the cows
        cows -= bulls;
        format!("{bulls}A{cows}B")
        // It would have been faster to have one lookup table and update cows up or down
        // accordingly
    }
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_1807_and_7810() {
        assert_eq!(
            Solution::get_hint("1807".to_string(), "7810".to_string()),
            "1A3B",
        );
    }

    #[test]
    fn test_1123_and_0111() {
        assert_eq!(
            Solution::get_hint("1123".to_string(), "0111".to_string()),
            "1A1B",
        );
    }

    #[test]
    fn test_1122_and_2211() {
        assert_eq!(
            Solution::get_hint("1122".to_string(), "2211".to_string()),
            "0A4B",
        );
    }

    #[test]
    fn test_11_and_10() {
        assert_eq!(
            Solution::get_hint("11".to_string(), "10".to_string()),
            "1A0B",
        );
    }
}
