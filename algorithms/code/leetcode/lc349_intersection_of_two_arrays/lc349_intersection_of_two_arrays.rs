// https://leetcode.com/problems/intersection-of-two-arrays/
// #easy

use std::collections::HashSet;

pub struct Solution;

impl Solution {
    pub fn intersection(nums1: &mut [i32], nums2: &mut [i32]) -> Vec<i32> {
        nums1.sort_unstable();
        nums2.sort_unstable();
        let mut found = HashSet::new();
        let mut i1 = 0;
        let mut i2 = 0;
        while i1 < nums1.len() && i2 < nums2.len() {
            if nums1[i1] < nums2[i2] {
                i1 += 1;
                continue;
            }
            if nums1[i1] > nums2[i2] {
                i2 += 1;
                continue;
            }
            found.insert(nums1[i1]);
            i1 += 1;
            i2 += 1;
        }
        let mut result = Vec::new();
        for value in found {
            result.push(value);
        }
        result
    }
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_example() {
        let mut nums1 = vec![1, 2, 2, 1];
        let mut nums2 = vec![2, 2];
        let mut result = Solution::intersection(&mut nums1, &mut nums2);
        result.sort_unstable();
        let expected = vec![2];
        assert_eq!(result, expected);
    }

    #[test]
    fn test_example_flipped() {
        let mut nums1 = vec![2, 2];
        let mut nums2 = vec![1, 2, 2, 1];
        let mut result = Solution::intersection(&mut nums1, &mut nums2);
        result.sort_unstable();
        assert_eq!(result, vec![2]);
    }

    #[test]
    fn test_12_and_11() {
        let mut nums1 = vec![1, 2];
        let mut nums2 = vec![1, 1];
        let mut result = Solution::intersection(&mut nums1, &mut nums2);
        result.sort_unstable();
        let expected = vec![1];
        assert_eq!(result, expected);
    }

    #[test]
    fn test_495_and_94984() {
        let mut nums1 = vec![4, 9, 5];
        let mut nums2 = vec![9, 4, 9, 8, 5];
        let mut result = Solution::intersection(&mut nums1, &mut nums2);
        result.sort_unstable();
        let expected = vec![4, 5, 9];
        assert_eq!(result, expected);
    }
}
