// https://leetcode.com/problems/intersection-of-two-arrays-ii/
// #easy

pub struct Solution;

impl Solution {
    pub fn intersect(mut nums1: Vec<i32>, mut nums2: Vec<i32>) -> Vec<i32> {
        nums1.sort();
        nums2.sort();
        let mut found = Vec::new();
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
            found.push(nums1[i1]);
            i1 += 1;
            i2 += 1;
        }
        found
    }
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_example() {
        let expected = vec![2, 2];
        let mut result = Solution::intersect(vec![1, 2, 2, 1], vec![2, 2]);
        result.sort();
        assert_eq!(result, expected);
    }

    #[test]
    fn test_example_flipped() {
        let expected = vec![2, 2];
        let mut result = Solution::intersect(vec![2, 2], vec![1, 2, 2, 1]);
        result.sort();
        assert_eq!(result, expected);
    }

    #[test]
    fn test_1_and_1() {
        let expected = vec![1];
        let mut result = Solution::intersect(vec![1], vec![1]);
        result.sort();
        assert_eq!(result, expected);
    }

    #[test]
    fn test_12_and_11() {
        let expected = vec![1];
        let mut result = Solution::intersect(vec![1, 2], vec![1, 1]);
        result.sort();
        assert_eq!(result, expected);
    }

    #[test]
    fn test_495_and_94984() {
        let expected = vec![4, 5, 9];
        let mut result = Solution::intersect(vec![4, 9, 5], vec![9, 4, 9, 8, 5]);
        result.sort();
        assert_eq!(result, expected);
    }
}
