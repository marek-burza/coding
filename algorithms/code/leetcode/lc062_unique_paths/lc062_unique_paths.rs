// https://leetcode.com/problems/unique-paths/
// #medium

pub struct Solution;

impl Solution {
    pub fn nck(n: i64, mut k: i64) -> i64 {
        if k > n {
            return 0;
        }
        if k * 2 > n {
            k = n - k;
        }
        if k == 0 {
            return 1;
        }
        let mut r = n;
        for i in 2..=k {
            r *= n + 1 - i;
            r /= i;
        }
        r
    }

    pub fn unique_paths(mut m: i32, n: i32) -> i32 {
        m -= 1;
        Self::nck((m + n - 1) as i64, m as i64) as i32
    }
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_37() {
        assert_eq!(Solution::unique_paths(3, 7), 28);
    }

    #[test]
    fn test_595() {
        assert_eq!(Solution::unique_paths(59, 5), 557845);
    }

    #[test]
    fn test_110() {
        assert_eq!(Solution::unique_paths(1, 10), 1);
    }

    #[test]
    fn test_nothing() {
        assert_eq!(Solution::unique_paths(1, 0), 0);
    }
}
