// https://leetcode.com/problems/h-index-ii/
// #medium

use std::cmp::Ordering;

pub struct Solution;

impl Solution {
    pub fn h_index(citations: Vec<i32>) -> i32 {
        let n = citations.len() as i32;
        let mut a = 0;
        let mut z = n;
        while a < z {
            let m = (a + z) / 2;
            match citations[m as usize].cmp(&(n - m)) {
                Ordering::Equal => {
                    return n - m;
                }
                Ordering::Less => {
                    a = m + 1;
                }
                Ordering::Greater => {
                    z = m;
                }
            }
        }
        n - a
    }
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_example() {
        assert_eq!(Solution::h_index(vec![0, 1, 3, 5, 6]), 3);
    }

    #[test]
    fn test_none() {
        assert_eq!(Solution::h_index(vec![0, 0, 0, 0, 0]), 0);
    }

    #[test]
    fn test_100() {
        assert_eq!(Solution::h_index(vec![100]), 1);
    }
}
