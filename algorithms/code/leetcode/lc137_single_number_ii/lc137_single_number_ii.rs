// https://leetcode.com/problems/single-number-ii/
// #medium

pub struct Solution;

impl Solution {
    pub fn single_number(nums: Vec<i32>) -> i32 {
        let mut counters = vec![0; 32];
        for num in nums {
            let mut num = num;
            for counter in &mut counters {
                *counter += num & 1;
                num >>= 1;
            }
        }
        let mut result = 0;
        let mut mask = 1;
        for counter in counters {
            if counter % 3 != 0 {
                result |= mask;
            }
            mask <<= 1;
        }
        result
    }
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_1112() {
        let nums = vec![1, 1, 1, 2];
        assert_eq!(Solution::single_number(nums), 2);
    }

    #[test]
    fn test_4344533() {
        let nums = vec![4, 3, 4, 4, 5, 3, 3];
        assert_eq!(Solution::single_number(nums), 5);
    }
}
