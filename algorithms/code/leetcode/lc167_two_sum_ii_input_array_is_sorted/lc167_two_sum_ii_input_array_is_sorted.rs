// https://leetcode.com/problems/two-sum-ii-input-array-is-sorted/
// #easy

use std::cmp::Ordering;

pub struct Solution;

impl Solution {
    pub fn two_sum(numbers: Vec<i32>, target: i32) -> Vec<i32> {
        let mut indices = vec![0; 2];
        if numbers.len() < 2 {
            return indices;
        }
        let mut a = 0;
        let mut z = numbers.len() - 1;
        while a < z {
            let v = numbers[a] + numbers[z];
            match v.cmp(&target) {
                Ordering::Equal => {
                    indices[0] = a as i32 + 1;
                    indices[1] = z as i32 + 1;
                    break;
                }
                Ordering::Greater => {
                    z -= 1;
                }
                _ => {
                    a += 1;
                }
            }
        }
        indices
    }
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_example() {
        assert_eq!(Solution::two_sum(vec![2, 7, 11, 15], 9), vec![1, 2]);
    }

    #[test]
    fn test_other_example() {
        assert_eq!(Solution::two_sum(vec![1, 5, 6, 9], 9), vec![0, 0]);
    }

    #[test]
    fn test_nothing() {
        assert_eq!(Solution::two_sum(vec![], 0), vec![0, 0]);
        assert_eq!(Solution::two_sum(vec![0], 0), vec![0, 0]);
    }
}
