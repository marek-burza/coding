// https://leetcode.com/problems/shuffle-an-array/
// #medium

use rand::Rng;

pub struct Solution {
    nums: Vec<i32>,
}

impl Solution {
    pub fn new(nums: Vec<i32>) -> Self {
        Self { nums }
    }

    pub fn reset(&self) -> Vec<i32> {
        self.nums.clone()
    }

    pub fn shuffle(&self) -> Vec<i32> {
        let mut result = self.nums.clone();
        for i in (1..result.len()).rev() {
            let j = rand::rng().random_range(0..(i + 1));
            result.swap(i, j);
        }
        result
    }
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_example() {
        let mut nums = vec![1, 2, 3];
        let solution = Solution::new(nums.clone());
        let mut result = solution.shuffle();
        let reset = solution.reset();
        assert_eq!(reset, nums);
        nums.sort();
        result.sort();
        assert_eq!(result, nums);
    }
}
