// https://leetcode.com/problems/reverse-bits/
// #easy

pub struct Solution;

impl Solution {
    pub fn reverse_bits(mut n: i32) -> i32 {
        let mut r = 0;
        for _ in 0..32 {
            r <<= 1;
            r |= n & 1;
            n >>= 1;
        }
        r
    }
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_43261596() {
        assert_eq!(Solution::reverse_bits(43261596), 964176192);
    }
}
