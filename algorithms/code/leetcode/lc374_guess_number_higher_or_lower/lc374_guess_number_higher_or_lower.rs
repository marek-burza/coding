// https://leetcode.com/problems/guess-number-higher-or-lower/
// #easy

static mut LC374_X: i32 = 0;

pub struct Solution;

impl Solution {
    unsafe fn guess(n: i32) -> i32 {
        if LC374_X < n {
            return -1;
        }
        if LC374_X > n {
            return 1;
        }
        0
    }

    #[allow(clippy::missing_safety_doc)]
    pub unsafe fn guess_number(n: i32) -> i32 {
        let mut a = 1;
        let mut z = n;
        while a != z {
            let checking = ((a as i64 + z as i64) / 2) as i32;
            match Self::guess(checking) {
                1 => {
                    a = checking + 1;
                }
                -1 => {
                    z = checking - 1;
                }
                _ => {
                    return checking;
                }
            }
        }
        a
    }
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_2_in_10() {
        unsafe {
            LC374_X = 2;
            assert_eq!(Solution::guess_number(10), 2);
        }
    }

    #[test]
    fn test_8_in_10() {
        unsafe {
            LC374_X = 8;
            assert_eq!(Solution::guess_number(10), 8);
        }
    }

    #[test]
    fn test_65789() {
        unsafe {
            LC374_X = 65789;
            assert_eq!(Solution::guess_number(i32::MAX), 65789);
        }
    }

    #[test]
    fn test_1() {
        unsafe {
            LC374_X = 1;
            assert_eq!(Solution::guess_number(1), 1);
        }
    }
}
