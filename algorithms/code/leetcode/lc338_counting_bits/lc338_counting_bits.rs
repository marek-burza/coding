// https://leetcode.com/problems/counting-bits/
// #easy

pub struct Solution;

impl Solution {
    pub fn count_bits(num: i32) -> Vec<i32> {
        let mut result = vec![0; num as usize + 1];
        let mut threshold = 1;
        for i in 0..result.len() {
            if (threshold << 1) <= i {
                threshold <<= 1;
            }
            if i == 0 {
                result[0] = 0;
            } else {
                result[i] = 1 + result[i - threshold];
            }
        }
        result
    }
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_2() {
        assert_eq!(Solution::count_bits(2), vec![0, 1, 1]);
    }

    #[test]
    fn test_5() {
        assert_eq!(Solution::count_bits(5), vec![0, 1, 1, 2, 1, 2]);
    }
}
