// https://leetcode.com/problems/bitwise-and-of-numbers-range/
// #medium

pub struct Solution;

impl Solution {
    pub fn range_bitwise_and(m: i32, n: i32) -> i32 {
        let mut result = 0;
        let mut power = 1;
        let mut mask = 0;
        for _ in 0..32 {
            if (m & power) != 0 && (power - (m & mask)) > (n - m) {
                result |= power;
            }
            power <<= 1;
            mask = (mask << 1) | 1;
        }
        result
    }
    // Alternative: Zero all bits after the first difference when checking from
    // highest to lowest bit
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_5_and_7() {
        assert_eq!(Solution::range_bitwise_and(5, 7), 4);
    }
}
