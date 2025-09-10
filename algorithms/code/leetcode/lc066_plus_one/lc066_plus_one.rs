// https://leetcode.com/problems/plus-one/
// #easy

pub struct Solution;

impl Solution {
    pub fn plus_one(mut digits: Vec<i32>) -> Vec<i32> {
        let mut carry = 1;
        for i in (0..digits.len()).rev() {
            digits[i] += carry;
            carry = digits[i] / 10;
            digits[i] %= 10;
        }
        if carry > 0 {
            let mut bigger = vec![0; digits.len() + 1];
            bigger[0] = carry;
            bigger[1..].copy_from_slice(&digits);
            return bigger;
        }
        digits
    }
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_1_and_9() {
        let expected = vec![2, 0];
        assert_eq!(Solution::plus_one(vec![1, 9]), expected);
    }

    #[test]
    fn test_9_and_9() {
        let expected = vec![1, 0, 0];
        assert_eq!(Solution::plus_one(vec![9, 9]), expected);
    }
}
