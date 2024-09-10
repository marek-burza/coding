// https://leetcode.com/problems/integer-to-roman/

pub struct Solution {}

impl Solution {
    pub fn int_to_roman(mut num: i32) -> String {
        let digits = vec![
            "M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I",
        ];
        let weights = &[1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1];
        let mut roman = String::new();
        for (i, digits_i) in digits.iter().enumerate() {
            let multiple = num / weights[i];
            for _ in 0..multiple {
                roman.push_str(digits_i);
            }
            num -= multiple * weights[i];
        }
        roman
    }
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_1234() {
        assert_eq!(Solution::int_to_roman(1234), "MCCXXXIV".to_string());
    }

    #[test]
    fn test_9() {
        assert_eq!(Solution::int_to_roman(9), "IX".to_string());
    }
}
