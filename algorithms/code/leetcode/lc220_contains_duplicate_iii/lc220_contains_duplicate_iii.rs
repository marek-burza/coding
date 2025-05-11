// https://leetcode.com/problems/contains-duplicate-iii/
// #medium

use std::collections::BTreeSet;
use std::ops::Bound::{Excluded, Unbounded};

pub struct Solution;

impl Solution {
    fn binary_search(items: &[i32], value: i32) -> Option<usize> {
        items.binary_search(&value).ok()
    }

    pub fn contains_nearby_almost_duplicate(nums: Vec<i32>, k: i32, t: i32) -> bool {
        let mut sorted: BTreeSet<i32> = BTreeSet::new();
        let mut ordered: Vec<i32> = Vec::new();
        for num in nums {
            let ceiling = sorted.range((Excluded(num), Unbounded)).next();
            let floor = sorted.range((Unbounded, Excluded(num))).next_back();
            if let Some(ceiling) = ceiling {
                if ceiling - num <= t {
                    return true;
                }
            }
            if let Some(floor) = floor {
                if num - floor <= t {
                    return true;
                }
            }
            ordered.push(num);
            let sorted_vector: Vec<i32> = sorted.iter().cloned().collect();
            if Self::binary_search(&sorted_vector, num).is_none() {
                sorted.insert(num);
            }
            if ordered.len() as i32 > k {
                let removed = ordered.remove(0);
                sorted.remove(&removed);
            }
        }
        false
    }
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_110202() {
        let nums = vec![1, 10, 20, 2];
        assert!(Solution::contains_nearby_almost_duplicate(nums, 3, 2));
    }

    #[test]
    fn test_110204() {
        let nums = vec![1, 10, 20, 4];
        assert!(!Solution::contains_nearby_almost_duplicate(nums, 3, 2));
    }

    #[test]
    fn test_11020302() {
        let nums = vec![1, 10, 20, 30, 2];
        assert!(!Solution::contains_nearby_almost_duplicate(nums, 3, 2));
    }

    #[test]
    fn test_8715161915_1_3() {
        let nums = vec![8, 7, 15, 1, 6, 1, 9, 15];
        assert!(Solution::contains_nearby_almost_duplicate(nums, 1, 3));
    }

    #[test]
    fn test_21474836402147483641_1_100() {
        let nums = vec![2147483640, 2147483641];
        assert!(Solution::contains_nearby_almost_duplicate(nums, 1, 100));
    }
}
