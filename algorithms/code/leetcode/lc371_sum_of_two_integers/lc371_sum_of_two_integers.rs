// https://leetcode.com/problems/sum-of-two-integers/
// #medium

pub struct Solution;

impl Solution {
    pub fn get_sum(a: i32, b: i32) -> i32 {
        let mut result = 0;
        let mut carry = 0;
        let mut mask = 1;
        while mask != 0 {
            let am = a & mask;
            let bm = b & mask;
            result |= am ^ bm ^ carry;
            carry = (am & bm) | (bm & carry) | (am & carry);
            carry <<= 1;
            mask <<= 1;
        }
        result
    }
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_example() {
        assert_eq!(Solution::get_sum(1, 2), 3);
    }
}
