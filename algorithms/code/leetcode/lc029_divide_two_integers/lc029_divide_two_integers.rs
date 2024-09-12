// https://leetcode.com/problems/divide-two-integers/

use num_traits::abs;

pub struct Solution;

impl Solution {
    pub fn divide(dividend: i32, divisor: i32) -> i32 {
        if divisor == 0 {
            return i32::MAX;
        }
        if dividend == 0 {
            return 0;
        }
        let mut sign: i64 = 1;
        if (dividend >= 0 && divisor < 0) || (dividend < 0 && divisor >= 0) {
            sign = -1;
        }
        let mut dividend = abs(dividend as i64);
        let divisor = abs(divisor as i64);
        let mut counter: i64 = 0;
        while dividend >= divisor {
            let mut subtractor = divisor;
            let mut incrementor = 1;
            while dividend - subtractor - subtractor >= 0 {
                subtractor += subtractor;
                incrementor += incrementor;
            }
            dividend -= subtractor;
            counter += incrementor;
        }
        let result = sign * counter;
        if result > i32::MAX.into() {
            return i32::MAX;
        }
        result as i32
    }
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_minus1010369383_minus2147483648() {
        assert_eq!(
            Solution::divide(-1010369383, i32::MIN),
            -1010369383 / i32::MIN
        );
    }

    #[test]
    fn test_minus2147483648_minus1() {
        assert_eq!(Solution::divide(i32::MIN, -1), i32::MAX);
    }

    #[test]
    fn test_divisor_0() {
        assert_eq!(Solution::divide(1, 0), i32::MAX);
    }

    #[test]
    fn test_dividend_0() {
        assert_eq!(Solution::divide(0, 1), 0);
    }
}
