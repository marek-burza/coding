// https://leetcode.com/problems/single-number-iii/
// #medium

pub struct Solution;

impl Solution {
    pub fn single_number(nums: Vec<i32>) -> Vec<i32> {
        let mut xor = 0;
        for value in &nums {
            xor ^= *value;
        }
        let mask = xor & !(xor - 1);
        let mut values = vec![0; 2];
        for value in &nums {
            if (value & mask) == 0 {
                values[0] ^= *value;
            } else {
                values[1] ^= *value;
            }
        }
        values
    }
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test() {
        let mut result = Solution::single_number(vec![1, 2, 1, 3, 2, 5]);
        result.sort();
        assert_eq!(result, vec![3, 5]);
    }
}
