// https://leetcode.com/problems/permutations/
// #medium

use std::collections::HashSet;

pub struct Solution;

impl Solution {
    fn permute_internal(
        mut prefix: Vec<i32>,
        remaining: HashSet<i32>,
        permutations: &mut Vec<Vec<i32>>,
    ) {
        if remaining.is_empty() {
            permutations.push(prefix.clone());
        } else {
            for value in remaining.iter() {
                prefix.push(*value);
                let mut reduced = remaining.clone();
                reduced.remove(value);
                Self::permute_internal(prefix.clone(), reduced, permutations);
                prefix.pop();
            }
        }
    }

    pub fn permute(nums: Vec<i32>) -> Vec<Vec<i32>> {
        let mut permutations = vec![];
        let mut remaining = HashSet::new();
        for value in nums.iter() {
            remaining.insert(*value);
        }
        Self::permute_internal(Vec::new(), remaining, &mut permutations);
        permutations
    }
}

#[cfg(test)]
mod tests {
    use super::*;

    use std::cmp::Ordering;

    pub fn compare_integer_vectors(l1: &Vec<i32>, l2: &Vec<i32>) -> Ordering {
        if l1.len() < l2.len() {
            return Ordering::Less;
        }
        if l1.len() > l2.len() {
            return Ordering::Greater;
        }
        for (a, b) in l1.iter().zip(l2.iter()) {
            if a < b {
                return Ordering::Less;
            }
            if a > b {
                return Ordering::Greater;
            }
        }
        Ordering::Equal
    }

    #[test]
    fn test_example() {
        let nums = vec![1, 2, 3];
        let expected = vec![
            vec![1, 2, 3],
            vec![1, 3, 2],
            vec![2, 1, 3],
            vec![2, 3, 1],
            vec![3, 1, 2],
            vec![3, 2, 1],
        ];
        let mut result = Solution::permute(nums);
        result.sort_by(compare_integer_vectors);
        assert_eq!(expected, result);
    }

    #[test]
    fn test_comparator() {
        assert_eq!(
            compare_integer_vectors(&vec![1, 2], &vec![1, 2, 3]),
            Ordering::Less
        );
        assert_eq!(
            compare_integer_vectors(&vec![1, 2, 3], &vec![1, 2]),
            Ordering::Greater
        );
        assert_eq!(
            compare_integer_vectors(&vec![1, 2], &vec![1, 2]),
            Ordering::Equal
        );
        assert_eq!(
            compare_integer_vectors(&vec![0, 2], &vec![1, 2]),
            Ordering::Less
        );
        assert_eq!(
            compare_integer_vectors(&vec![2, 3], &vec![1, 2]),
            Ordering::Greater
        );
    }
}
