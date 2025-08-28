// https://leetcode.com/problems/merge-sorted-array/
// #easy

pub struct Solution;

impl Solution {
    #[allow(clippy::ptr_arg)]
    pub fn merge(nums1: &mut Vec<i32>, m: i32, nums2: &mut Vec<i32>, n: i32) {
        let mut m = m;
        let mut n = n;
        let mut i = m + n - 1;
        m -= 1;
        n -= 1;
        while m >= 0 && n >= 0 {
            if nums1[m as usize] > nums2[n as usize] {
                nums1[i as usize] = nums1[m as usize];
                m -= 1;
            } else {
                nums1[i as usize] = nums2[n as usize];
                n -= 1;
            }
            i -= 1;
        }
        while n >= 0 {
            nums1[i as usize] = nums2[n as usize];
            n -= 1;
            i -= 1;
        }
    }
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_example_1() {
        let mut nums1 = vec![1, 2, 3, 0, 0, 0];
        let mut nums2 = vec![2, 5, 6];
        let expected = vec![1, 2, 2, 3, 5, 6];
        Solution::merge(&mut nums1, 3, &mut nums2, 3);
        assert_eq!(nums1, expected);
    }

    #[test]
    fn test_example_2() {
        let mut nums1 = vec![1];
        let mut nums2 = vec![];
        let expected = vec![1];
        Solution::merge(&mut nums1, 1, &mut nums2, 0);
        assert_eq!(nums1, expected);
    }

    #[test]
    fn test_example_3() {
        let mut nums1 = vec![0];
        let mut nums2 = vec![1];
        let expected = vec![1];
        Solution::merge(&mut nums1, 0, &mut nums2, 1);
        assert_eq!(nums1, expected);
    }

    #[test]
    fn test_13711000_and_4_and_4620_and_3() {
        let mut nums1 = vec![1, 3, 7, 11, 0, 0, 0];
        let mut nums2 = vec![4, 6, 20];
        let expected = vec![1, 3, 4, 6, 7, 11, 20];
        Solution::merge(&mut nums1, 4, &mut nums2, 3);
        assert_eq!(nums1, expected);
    }
}
