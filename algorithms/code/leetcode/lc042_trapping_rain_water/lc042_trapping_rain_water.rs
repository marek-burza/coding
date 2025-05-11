// https://leetcode.com/problems/trapping-rain-water/
// #hard

use std::cmp::Ordering;

pub struct Solution;

impl Solution {
    fn mapping_comparator(i1: &usize, i2: &usize, map: &[i32]) -> Ordering {
        if map[*i1] > map[*i2] {
            return Ordering::Less;
        }
        if map[*i1] < map[*i2] {
            return Ordering::Greater;
        }
        Ordering::Equal
    }

    fn amount(height: &[i32], from: usize, to: usize) -> i32 {
        let amount = height[from].min(height[to]) * (to - from - 1) as i32;
        amount - height[from + 1..to].iter().sum::<i32>()
    }

    pub fn trap(height: &[i32]) -> i32 {
        if height.len() < 3 {
            return 0;
        }
        // Sort the terrain
        let mut sorted = Vec::new();
        for i in 0..height.len() {
            sorted.push(i);
        }
        sorted.sort_by(|i1, i2| Self::mapping_comparator(i1, i2, height));
        // Fill from the top
        // (pick highest and then extend "exclusion zone")
        let mut count = 0;
        let mut left = sorted[0];
        let mut right = sorted[0];
        for i in sorted {
            if right < i {
                count += Self::amount(height, right, i);
                right = i;
            }
            if i < left {
                count += Self::amount(height, i, left);
                left = i;
            }
        }
        count
    }
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_example() {
        let terrain = vec![0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1];
        assert_eq!(Solution::trap(&terrain), 6);
    }

    #[test]
    fn test_nothing() {
        assert_eq!(Solution::trap(&vec![0, 1]), 0);
    }

    #[test]
    fn test_comparator() {
        let height = vec![0i32, 1i32];
        assert_eq!(
            Solution::mapping_comparator(&1usize, &0usize, &height),
            Ordering::Less
        );
        assert_eq!(
            Solution::mapping_comparator(&0usize, &1usize, &height),
            Ordering::Greater
        );
        assert_eq!(
            Solution::mapping_comparator(&0usize, &0usize, &height),
            Ordering::Equal
        );
    }
}
