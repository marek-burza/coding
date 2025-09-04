// https://leetcode.com/problems/maximum-gap/
// #hard

pub struct Solution;

impl Solution {
    pub fn maximum_gap(nums: Vec<i32>) -> i32 {
        if nums.is_empty() {
            return 0;
        }
        let n = nums.len();
        let max_e = *nums.iter().max().unwrap();
        let min_e = *nums.iter().min().unwrap();
        let len = (max_e - min_e) as f64 / ((n - 1) as f64);
        let mut max_a = vec![i32::MIN; n];
        let mut min_a = vec![i32::MAX; n];
        for num in nums {
            let index = ((num - min_e) as f64 / len) as usize;
            max_a[index] = max_a[index].max(num);
            min_a[index] = min_a[index].min(num);
        }
        let mut gap = 0;
        let mut prev = max_a[0];
        for i in 1..n {
            if min_a[i] == i32::MAX {
                continue;
            }
            gap = gap.max(min_a[i] - prev);
            prev = max_a[i];
        }
        gap
        // Pigeon hole principle:
        // We keep the biggest and smallest pigeon fitting in the hole
        // and that's enough to find the gap in linear way
    }
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_33_and_2_and_100_and_70() {
        let nums1 = vec![33, 2, 100, 70];
        assert_eq!(Solution::maximum_gap(nums1), 37);
    }

    #[test]
    fn test_nothing() {
        assert_eq!(Solution::maximum_gap(vec![]), 0);
        assert_eq!(Solution::maximum_gap(vec![0]), 0);
    }
}
