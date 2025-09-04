// https://leetcode.com/problems/integer-break/
// #medium

pub struct Solution;

impl Solution {
    pub fn integer_break(n: i32) -> i32 {
        if n == 2 {
            return 1;
        }
        if n == 3 {
            return 2;
        }
        if n == 5 {
            return 6;
        }
        let threes = n / 3;
        let rest = n - 3 * (threes - 1);
        let rest = if rest == 5 { 6 } else { rest };
        3i32.pow((threes - 1) as u32) * rest
        // let mut product = 1;
        // while n > 4 {
        //     product *= 3;
        //     n -= 3;
        // }
        // product * n
    }
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_2() {
        assert_eq!(Solution::integer_break(2), 1);
    }

    #[test]
    fn test_3() {
        assert_eq!(Solution::integer_break(3), 2);
    }

    #[test]
    fn test_4() {
        assert_eq!(Solution::integer_break(4), 4);
    }

    #[test]
    fn test_5() {
        assert_eq!(Solution::integer_break(5), 6);
    }

    #[test]
    fn test_6() {
        assert_eq!(Solution::integer_break(6), 9);
    }

    #[test]
    fn test_7() {
        assert_eq!(Solution::integer_break(7), 12);
    }

    #[test]
    fn test_8() {
        assert_eq!(Solution::integer_break(8), 18);
    }

    #[test]
    fn test_9() {
        assert_eq!(Solution::integer_break(9), 27);
    }

    #[test]
    fn test_10() {
        assert_eq!(Solution::integer_break(10), 36);
    }
}
