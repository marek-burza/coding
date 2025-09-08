// https://leetcode.com/problems/maximum-subarray/
// #easy

pub struct Solution;

impl Solution {
    pub fn max_sub_array(nums: Vec<i32>) -> i32 {
        let mut sum = 0;
        let mut min = 0;
        let mut max = i32::MIN;
        for num in nums {
            min = if sum < min { sum } else { min };
            sum += num;
            let delta = sum - min;
            max = if delta > max { delta } else { max };
        }
        max
    }
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_example_1() {
        assert_eq!(
            Solution::max_sub_array(vec![-2, 1, -3, 4, -1, 2, 1, -5, 4]),
            6,
        );
    }

    #[test]
    fn test_example_2() {
        assert_eq!(Solution::max_sub_array(vec![1]), 1);
    }

    #[test]
    fn test_example_3() {
        assert_eq!(Solution::max_sub_array(vec![5, 4, -1, 7, 8]), 23);
    }

    #[test]
    fn test_minus2_and_1() {
        assert_eq!(Solution::max_sub_array(vec![-2, 1]), 1);
    }

    #[test]
    fn test_minus2_and_minus1() {
        assert_eq!(Solution::max_sub_array(vec![-2, -1]), -1);
    }
}
