// https://leetcode.com/problems/add-digits/
// #easy

pub struct Solution;

impl Solution {
    pub fn add_digits(mut num: i32) -> i32 {
        while num >= 10 {
            let mut sum = 0;
            while num > 0 {
                let digit = num % 10;
                sum += digit;
                num /= 10;
            }
            num = sum;
        }
        num
        // (num - 9 * ((num - 1) / 9).floor()) as i32;
    }
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_example() {
        assert_eq!(Solution::add_digits(38), 2);
    }
}
