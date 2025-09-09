// https://leetcode.com/problems/perfect-squares/
// #medium

pub struct Solution;

impl Solution {
    pub fn num_squares(n: i32) -> i32 {
        let mut lut = vec![i32::MAX; n as usize + 1];
        lut[0] = 0;
        let mut i = 1;
        let mut ii = 1;
        while ii <= n {
            for j in (ii as usize)..lut.len() {
                lut[j] = std::cmp::min(lut[j], lut[j - ii as usize] + 1);
            }
            i += 1;
            ii = i * i;
        }
        lut[n as usize]
    }
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_12() {
        assert_eq!(Solution::num_squares(12), 3);
    }

    #[test]
    fn test_13() {
        assert_eq!(Solution::num_squares(13), 2);
    }

    #[test]
    fn test_9975() {
        assert_eq!(Solution::num_squares(9975), 4);
    }

    #[test]
    fn test_9732() {
        assert_eq!(Solution::num_squares(9732), 3);
    }

    #[test]
    fn test_5756() {
        assert_eq!(Solution::num_squares(5756), 4);
    }

    #[test]
    fn test_6255() {
        assert_eq!(Solution::num_squares(6255), 4);
    }
}
