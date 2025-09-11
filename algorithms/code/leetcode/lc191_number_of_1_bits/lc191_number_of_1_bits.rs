// https://leetcode.com/problems/number-of-1-bits/
// #easy

pub struct Solution;

impl Solution {
    pub fn hamming_weight(mut n: i32) -> i32 {
        let mut count = 0;
        for _ in 0..32 {
            count += n & 1;
            n >>= 1;
        }
        count
    }
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_11() {
        assert_eq!(Solution::hamming_weight(11), 3);
    }
}
