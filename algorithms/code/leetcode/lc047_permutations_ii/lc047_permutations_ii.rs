// https://leetcode.com/problems/permutations-ii/
// #medium

use std::collections::HashMap;

pub struct Solution;

impl Solution {
    pub fn permute_unique(nums: Vec<i32>) -> Vec<Vec<i32>> {
        // Count each number
        let mut counted = HashMap::new();
        for &num in nums.iter() {
            *counted.entry(num).or_insert(0) += 1;
        }
        // Generate the permutations
        let mut permutations = vec![];
        Self::generate(Vec::new(), nums.len(), &mut counted, &mut permutations);
        permutations
    }

    fn generate(
        mut permutation: Vec<i32>,
        limit: usize,
        counted: &mut HashMap<i32, i32>,
        permutations: &mut Vec<Vec<i32>>,
    ) {
        if permutation.len() == limit {
            permutations.push(permutation.clone());
            return;
        }
        let keys = counted.keys().cloned().collect::<Vec<i32>>();
        for key in keys {
            let count = *counted.get(&key).unwrap();
            if count != 0 {
                permutation.push(key);
                counted.insert(key, count - 1);
                Self::generate(permutation.clone(), limit, counted, permutations);
                counted.insert(key, count);
                permutation.pop();
            }
        }
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
        let nums = vec![1, 1, 2];
        let expected = vec![vec![1, 1, 2], vec![1, 2, 1], vec![2, 1, 1]];
        let mut result = Solution::permute_unique(nums);
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
