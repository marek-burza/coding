// https://leetcode.com/problems/bulb-switcher/
// #medium

pub struct Solution;

impl Solution {
    pub fn bulb_switch(n: i32) -> i32 {
        (n as f64).sqrt() as i32
    }
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    pub fn test_1_to_16() {
        let expected = [0, 1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 3, 3, 4];
        for (i, expected_i) in expected.iter().enumerate() {
            assert_eq!(*expected_i, Solution::bulb_switch(i as i32));
        }
    }
}
