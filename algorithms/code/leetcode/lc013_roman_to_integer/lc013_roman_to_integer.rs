// https://leetcode.com/problems/roman-to-integer/

pub struct Solution;

impl Solution {
    pub fn roman_to_int(s: String) -> i32 {
        if s.is_empty() {
            return 0;
        }
        let mut result = 0;
        let mut previous = 0;
        for i in (0..s.len()).rev() {
            let mut current = 0;
            match s.chars().nth(i).unwrap() {
                'I' => {
                    current = 1;
                }
                'V' => {
                    current = 5;
                }
                'X' => {
                    current = 10;
                }
                'L' => {
                    current = 50;
                }
                'C' => {
                    current = 100;
                }
                'D' => {
                    current = 500;
                }
                'M' => {
                    current = 1000;
                }
                _ => {}
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
