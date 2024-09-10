// https://leetcode.com/problems/letter-combinations-of-a-phone-number/

pub struct Solution {}

impl Solution {
    pub fn letter_combinations(digits: String) -> Vec<String> {
        if digits.is_empty() {
            return vec![];
        }
        let mapping = [
            " ", "1", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz",
        ];
        let mut mapped: Vec<String> = vec!["".to_string()];
        for digit in digits.chars() {
            let index = (digit as u32 - '0' as u32) as usize;
            let mut longer: Vec<String> = Vec::new();
            for prefix in &mapped {
                for suffix in mapping[index].chars() {
                    longer.push(prefix.clone() + &suffix.to_string());
                }
            }
            mapped = longer;
        }
        mapped
    }
}

#[cfg(test)]
mod tests {
    use super::*;
    #[test]
    fn test_empty() {
        let result = Solution::letter_combinations("".to_string());
        assert!(result.is_empty());
    }

    #[test]
    fn test_example() {
        let expected = vec![
            "ad".to_string(),
            "ae".to_string(),
            "af".to_string(),
            "bd".to_string(),
            "be".to_string(),
            "bf".to_string(),
            "cd".to_string(),
            "ce".to_string(),
            "cf".to_string(),
        ];
        let result = Solution::letter_combinations("23".to_string());
        assert_eq!(result, expected);
    }
}
