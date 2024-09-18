// https://leetcode.com/problems/roman-to-integer/

pub struct Solution;

use std::collections::HashMap;

impl Solution {
    pub fn roman_to_int(s: String) -> i32 {
        if s.is_empty() {
            return 0;
        }
        let mut result = 0;
        let mut previous = 0;
        let roman_values: HashMap<char, i32> = vec![
            ('I', 1),
            ('V', 5),
            ('X', 10),
            ('L', 50),
            ('C', 100),
            ('D', 500),
            ('M', 1000),
        ]
        .into_iter()
        .collect();
        for i in (0..s.len()).rev() {
            let mut current = 0;
            if let Some(&value) = roman_values.get(&s.chars().nth(i).unwrap()) {
                current = value;
            }
            if current < previous {
                current = -current;
            }
            result += current;
            previous = current;
        }
        result
    }
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_mcmliv() {
        assert_eq!(Solution::roman_to_int("MCMLIV".to_string()), 1954);
    }

    #[test]
    fn test_nothing() {
        assert_eq!(Solution::roman_to_int(String::new()), 0);
    }
}
