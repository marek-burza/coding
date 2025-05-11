// https://leetcode.com/problems/contains-duplicate-ii/
// #easy

use std::collections::{HashSet, VecDeque};

pub struct Solution;

impl Solution {
    pub fn contains_nearby_duplicate(nums: &[i32], k: usize) -> bool {
        let mut set = HashSet::new();
        let mut ordered = VecDeque::new();
        for num in nums {
            if set.contains(num) {
                return true;
            }
            ordered.push_back(num);
            set.insert(num);
            if ordered.len() > k {
                set.remove(ordered.pop_front().unwrap());
            }
        }
        false
    }
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_57_and_2() {
        assert!(!Solution::contains_nearby_duplicate(&[0, 5, 7], 2));
    }

    #[test]
    fn test_575_and_2() {
        assert!(Solution::contains_nearby_duplicate(&[0, 5, 7, 5], 2));
    }

    #[test]
    fn test_5705_and_2() {
        assert!(!Solution::contains_nearby_duplicate(&[0, 5, 7, 10, 5], 2));
    }
}
