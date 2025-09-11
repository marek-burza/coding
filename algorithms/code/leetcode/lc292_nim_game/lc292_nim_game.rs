// https://leetcode.com/problems/nim-game/
// #easy

pub struct Solution;

impl Solution {
    pub fn can_win_nim(n: i32) -> bool {
        n % 4 != 0
    }
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_1_to_10() {
        assert!(Solution::can_win_nim(1));
        assert!(Solution::can_win_nim(2));
        assert!(Solution::can_win_nim(3));
        assert!(!Solution::can_win_nim(4));
        assert!(Solution::can_win_nim(5));
        assert!(Solution::can_win_nim(6));
        assert!(Solution::can_win_nim(7));
        assert!(!Solution::can_win_nim(8));
        assert!(Solution::can_win_nim(9));
        assert!(Solution::can_win_nim(10));
    }
}
