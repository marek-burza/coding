// https://leetcode.com/problems/gray-code/
// #medium

pub struct Solution;

impl Solution {
    pub fn gray_code(mut bits: i32) -> Vec<i32> {
        if bits == 0 {
            return vec![0];
        }
        let mut list = vec![0, 1];
        let mut shifted = 2;
        while bits > 1 {
            bits -= 1;
            let n = list.len();
            for i in (0..n).rev() {
                let value = list[i];
                list.push(shifted | value);
            }
            shifted <<= 1;
        }
        list
    }
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_4() {
        let expected = vec![0, 1, 3, 2, 6, 7, 5, 4, 12, 13, 15, 14, 10, 11, 9, 8];
        assert_eq!(Solution::gray_code(4), expected);
    }

    #[test]
    fn test_0() {
        let expected = vec![0];
        assert_eq!(Solution::gray_code(0), expected);
    }
}
