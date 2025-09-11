// https://leetcode.com/problems/factorial-trailing-zeroes/
// #medium

pub struct Solution;

impl Solution {
    pub fn trailing_zeroes(n: i32) -> i32 {
        let mut step: i64 = 5;
        let mut count: i64 = 0;
        while step <= n as i64 {
            count += n as i64 / step;
            step *= 5;
        }
        count as i32
    }
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_5() {
        assert_eq!(Solution::trailing_zeroes(5), 1);
    }

    #[test]
    fn test_1808548329() {
        assert_eq!(Solution::trailing_zeroes(1808548329), 452137076);
    }

    #[test]
    fn test_2147483647() {
        assert_eq!(Solution::trailing_zeroes(2147483647), 536870902);
    }
}
