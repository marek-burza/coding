// https://leetcode.com/problems/count-numbers-with-unique-digits/
// #medium

pub struct Solution;

impl Solution {
    fn count(prefix: String, n: i32) -> i32 {
        if prefix.len() as i32 == n {
            return 1;
        }
        let mut sum = 1;
        const DIGITS: [&str; 10] = ["0", "1", "2", "3", "4", "5", "6", "7", "8", "9"];
        let first = if prefix.is_empty() { 1 } else { 0 };
        for digit in DIGITS.iter().skip(first) {
            if !prefix.contains(digit) {
                sum += Self::count(format!("{}{}", prefix, digit), n);
            }
        }
        sum
    }

    pub fn count_numbers_with_unique_digits(n: i32) -> i32 {
        Self::count("".into(), n)
    }
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_example() {
        assert_eq!(Solution::count_numbers_with_unique_digits(2), 91);
    }

    #[test]
    fn test_0() {
        assert_eq!(Solution::count_numbers_with_unique_digits(0), 1);
    }

    #[test]
    fn test_1() {
        assert_eq!(Solution::count_numbers_with_unique_digits(1), 10);
    }
}
