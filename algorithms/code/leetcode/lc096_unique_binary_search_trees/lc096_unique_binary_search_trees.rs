// https://leetcode.com/problems/unique-binary-search-trees/
// #medium

pub struct Solution;

impl Solution {
    pub fn num_trees(n: i32) -> i32 {
        let mut cache = vec![0; (n + 1) as usize];
        cache[0] = 1;
        cache[1] = 1;
        for i in 2..=n {
            for j in 0..i {
                cache[i as usize] += cache[j as usize] * cache[(i - j - 1) as usize];
            }
        }
        cache[n as usize]
    }
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_2() {
        assert_eq!(Solution::num_trees(2), 2);
    }

    #[test]
    fn test_3() {
        assert_eq!(Solution::num_trees(3), 5);
    }

    #[test]
    fn test_19() {
        assert_eq!(Solution::num_trees(19), 1767263190);
    }
}
