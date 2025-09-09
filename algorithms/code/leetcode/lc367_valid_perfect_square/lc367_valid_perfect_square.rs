// https://leetcode.com/problems/valid-perfect-square/
// #easy

pub struct Solution;

impl Solution {
    pub fn is_perfect_square(num: i32) -> bool {
        let mut a = 0;
        let mut z = 1 + num / 2;
        while a <= z {
            let m = (a + z) / 2;
            let mm = m as i64 * m as i64;
            if mm == num as i64 {
                return true;
            }
            if mm > num as i64 {
                z = m - 1;
            } else {
                a = m + 1;
            }
        }
        false
    }
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_1() {
        assert!(Solution::is_perfect_square(1));
    }

    #[test]
    fn test_14() {
        assert!(!Solution::is_perfect_square(14));
    }

    #[test]
    fn test_16() {
        assert!(Solution::is_perfect_square(16));
    }

    #[test]
    fn test_maximum() {
        assert!(!Solution::is_perfect_square(i32::MAX));
    }
}
