// https://leetcode.com/problems/rectangle-area/
// #medium

pub struct Solution;

impl Solution {
    fn area(left: i32, bottom: i32, right: i32, top: i32) -> i32 {
        (right - left) * (top - bottom)
    }

    #[allow(clippy::too_many_arguments)]
    pub fn compute_area(a: i32, b: i32, c: i32, d: i32, e: i32, f: i32, g: i32, h: i32) -> i32 {
        let mut total = Self::area(a, b, c, d) + Self::area(e, f, g, h);
        let top = std::cmp::min(d, h);
        let bottom = std::cmp::max(b, f);
        let left = std::cmp::max(a, e);
        let right = std::cmp::min(c, g);
        if bottom < top && left < right {
            total -= Self::area(left, bottom, right, top);
        }
        total
    }
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_minus3_0_3_4_0_minus1_9_2() {
        assert_eq!(Solution::compute_area(-3, 0, 3, 4, 0, -1, 9, 2), 45);
    }

    #[test]
    fn test_minus2_minus2_2_2_minus1_4_1_6() {
        assert_eq!(Solution::compute_area(-2, -2, 2, 2, -1, 4, 1, 6), 20);
    }

    #[test]
    fn test_minus5_minus5_minus4_0_minus3_minus3_3_3() {
        assert_eq!(Solution::compute_area(-5, -5, -4, 0, -3, -3, 3, 3), 41);
    }
}
