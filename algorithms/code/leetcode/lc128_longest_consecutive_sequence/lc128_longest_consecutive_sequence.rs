// https://leetcode.com/problems/longest-consecutive-sequence/
// #medium

use std::collections::{HashMap, HashSet};

struct Range {
    a: i32,
    z: i32,
}

pub struct Solution;

impl Solution {
    pub fn longest_consecutive_sequence(nums: Vec<i32>) -> i32 {
        let mut seen = HashSet::new();
        let mut map = HashMap::<i32, Range>::new();
        let mut length = 0;
        for num in nums {
            if seen.contains(&num) {
                continue;
            }
            seen.insert(num);
            let less = num > i32::MIN && map.contains_key(&(num - 1));
            let more = num < i32::MAX && map.contains_key(&(num + 1));
            let ante = if less { map.get(&(num - 1)) } else { None };
            let post = if more { map.get(&(num + 1)) } else { None };
            let mut a = num;
            let mut z = num;
            if let (Some(ante), Some(post)) = (ante, post) {
                a = ante.a;
                z = post.z;
            }
            if let Some(ante) = ante {
                a = ante.a;
            }
            if let Some(post) = post {
                z = post.z;
            }
            map.insert(a, Range { a, z });
            map.insert(z, Range { a, z });
            let span = z - a + 1;
            if span > length {
                length = span
            }
        }
        length
        // This can be simplified by storing only the length of the range in the hash
        // table instead of range itself
    }
}
#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_100_4_200_1_3_2() {
        let nums = vec![100, 4, 200, 1, 3, 2];
        assert_eq!(Solution::longest_consecutive_sequence(nums), 4);
    }

    #[test]
    fn test_longer() {
        let nums = vec![
            4, 2, 2, -4, 0, -2, 4, -3, -4, -4, -5, 1, 4, -9, 5, 0, 6, -8, -1, -3, 6, 5, -8, -1, -5,
            -1, 2, -9, 1,
        ];
        assert_eq!(Solution::longest_consecutive_sequence(nums), 8);
    }

    #[test]
    fn test_max() {
        let nums = vec![100, 4, i32::MAX, 1, 3, 2];
        assert_eq!(Solution::longest_consecutive_sequence(nums), 4);
    }

    #[test]
    fn test_min() {
        let nums = vec![100, 4, i32::MIN, 1, 3, 2];
        assert_eq!(Solution::longest_consecutive_sequence(nums), 4);
    }
}
