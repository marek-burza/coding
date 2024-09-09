// https://leetcode.com/problems/reverse-integer/

pub struct Solution {}

impl Solution {
    pub fn reverse(mut x: i32) -> i32 {
        if x == i32::MIN {
            return 0;
        }
        let negative = x < 0;
        if negative {
            x = -x;
        }
        let mut digits: Vec<i32> = vec![];
        while x > 0 {
            digits.push(x % 10);
            x /= 10;
        }
        let limits = [2, 1, 4, 7, 4, 8, 3, 6, 4, 7];
        let length = limits.len();
        let padding = length - digits.len();
        let mut prefix: Vec<i32> = vec![0; padding];
        prefix.extend(digits);
        digits = prefix;
        for i in 0..length {
            if digits[i] > limits[i] {
                return 0;
            }
            if digits[i] < limits[i] {
                break;
            }
        }
        let mut result: i32 = 0;
        for digit in digits.iter() {
            result = result * 10 + *digit;
        }
        if negative {
            result = -result;
        }
        result
    }
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_minus2000000002() {
        assert_eq!(Solution::reverse(2000000002), 2000000002);
    }

    #[test]
    fn test_minus2147483648() {
        assert_eq!(Solution::reverse(-2147483648), 0);
    }

    #[test]
    fn test_1000000003() {
        assert_eq!(Solution::reverse(1000000003), 0);
    }

    #[test]
    fn test_1534236469() {
        assert_eq!(Solution::reverse(1534236469), 0);
    }

    #[test]
    fn test_minus321() {
        assert_eq!(Solution::reverse(-321), -123);
    }
}
