// https://leetcode.com/problems/powx-n/
// #medium

pub struct Solution;

impl Solution {
    pub fn my_pow(x: f64, n: i32) -> f64 {
        if n == 0 {
            return 1.0;
        }
        let count = if n < 0 { -(n as i64) } else { n as i64 };
        let mut result = x;
        let mut power = 1;
        let mut powers = Vec::new();
        while (power << 1) <= count {
            powers.push(result);
            result *= result;
            power <<= 1;
        }
        let mut previous = power >> 1;
        for i in (0..powers.len()).rev() {
            let repeat = (count - power) / previous;
            let value = powers[i];
            for _ in 0..repeat {
                result *= value;
            }
            power += repeat * previous;
            previous >>= 1;
        }
        if n < 0 { 1.0 / result } else { result }
    }
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_smaller() {
        let x = 34.00515;
        let n = -3;
        let expected = f64::powf(x, n as f64);
        assert_eq!(Solution::my_pow(x, n), expected);
    }

    #[test]
    fn test_bigger() {
        let x = 0.00001;
        let n = 2147483647;
        let expected = f64::powf(x, n as f64);
        assert_eq!(Solution::my_pow(x, n), expected);
    }

    #[test]
    fn test_0() {
        assert_eq!(Solution::my_pow(0.0, 0), 1.0);
    }
}
