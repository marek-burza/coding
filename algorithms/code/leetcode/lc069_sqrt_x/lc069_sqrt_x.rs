// https://leetcode.com/problems/sqrtx/
// #easy

pub struct Solution;

impl Solution {
    pub fn my_sqrt(x: i32) -> i32 {
        let mut a = 0i64;
        let mut z = x as i64;
        while a + 1 < z {
            let m = (a + z) / 2;
            let mm = m * m;
            if mm == x as i64 {
                return m as i32;
            }
            if mm < x as i64 {
                a = m;
            } else {
                z = m - 1;
            }
        }
        if z * z <= x as i64 {
            return z as i32;
        }
        a as i32
    }
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_example_1() {
        assert_eq!(Solution::my_sqrt(4), 2);
    }

    #[test]
    fn test_example_2() {
        assert_eq!(Solution::my_sqrt(8), 2);
    }

    #[test]
    fn test_64() {
        assert_eq!(Solution::my_sqrt(64), 8);
    }

    #[test]
    fn test_2() {
        assert_eq!(Solution::my_sqrt(2), 1);
    }

    #[test]
    fn test_1() {
        assert_eq!(Solution::my_sqrt(1), 1);
    }
}
