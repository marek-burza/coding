// https://leetcode.com/problems/subsets-ii/
// #medium

pub struct Solution;

impl Solution {
    fn subsets_internal(
        nums: &[i32],
        offset: usize,
        mut current: Vec<i32>,
        list: &mut Vec<Vec<i32>>,
    ) {
        list.push(current.clone());
        let mut i = offset;
        while i < nums.len() {
            let mut count = 1;
            for j in (i + 1)..nums.len() {
                if nums[j - 1] != nums[j] {
                    break;
                }
                count += 1;
            }
            for _ in 0..count {
                current.push(nums[i]);
                Self::subsets_internal(nums, i + count, current.clone(), list);
            }
            for _ in 0..count {
                current.pop();
            }
            i += count;
        }
    }

    pub fn subsets_with_dup(mut nums: Vec<i32>) -> Vec<Vec<i32>> {
        nums.sort_unstable();
        let mut list = Vec::new();
        Self::subsets_internal(&nums, 0, Vec::new(), &mut list);
        list
    }
}

#[cfg(test)]
mod tests {
    use super::*;

    use std::cmp::Ordering;

    pub fn compare_orderly(l1: &Vec<i32>, l2: &Vec<i32>) -> Ordering {
        let difference = l1.len().cmp(&l2.len());
        if difference != std::cmp::Ordering::Equal {
            return difference;
        }
        for (a, b) in l1.iter().zip(l2.iter()) {
            let difference = a.cmp(b);
            if difference != std::cmp::Ordering::Equal {
                return difference;
            }
        }
        std::cmp::Ordering::Equal
    }

    #[test]
    fn test_122() {
        let expected = vec![
            vec![],
            vec![1],
            vec![2],
            vec![1, 2],
            vec![2, 2],
            vec![1, 2, 2],
        ];
        let mut result = Solution::subsets_with_dup(vec![1, 2, 2]);
        result.sort_by(compare_orderly);
        assert_eq!(expected, result);
    }

    #[test]
    fn test_comparator() {
        assert_eq!(compare_orderly(&vec![1, 2], &vec![1, 2]), Ordering::Equal);
    }
}
