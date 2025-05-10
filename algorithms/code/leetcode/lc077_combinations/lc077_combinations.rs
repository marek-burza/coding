// https://leetcode.com/problems/combinations/
// #medium

pub struct Solution;

impl Solution {
    fn combine_internal(m: i32, n: i32, k: i32, prefix: &mut Vec<i32>, found: &mut Vec<Vec<i32>>) {
        for i in m..=n - (k - 1) + prefix.len() as i32 {
            prefix.push(i);
            if prefix.len() as i32 == k {
                found.push(prefix.clone());
            } else {
                Self::combine_internal(i + 1, n, k, prefix, found);
            }
            prefix.pop();
        }
    }

    pub fn combine(n: i32, k: i32) -> Vec<Vec<i32>> {
        let mut found = Vec::new();
        Self::combine_internal(1, n, k, &mut Vec::new(), &mut found);
        found
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
        let expected = vec![
            vec![1, 2],
            vec![1, 3],
            vec![1, 4],
            vec![2, 3],
            vec![2, 4],
            vec![3, 4],
        ];
        let mut result = Solution::combine(4, 2);
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
