// https://leetcode.com/problems/combination-sum-iii/
// #medium

pub struct Solution;

impl Solution {
    fn traverse(contains: i32, sum: i32, left: i32, n: i32, found: &mut Vec<i32>, start: i32) {
        if left == 0 && sum == n {
            found.push(contains);
        } else {
            for i in start..=9 {
                let mask = 1 << i;
                // if (contains & mask) == 0 {
                Self::traverse(contains | mask, sum + i, left - 1, n, found, i + 1);
                // }
            }
        }
    }

    pub fn combination_sum_iii(k: i32, n: i32) -> Vec<Vec<i32>> {
        let mut found = Vec::new();
        Self::traverse(0, 0, k, n, &mut found, 1);
        let mut all = Vec::new();
        for contains in found {
            let mut entry = Vec::new();
            for i in 1..=9 {
                let mask = 1 << i;
                if (contains & mask) != 0 {
                    entry.push(i);
                }
            }
            all.push(entry);
        }
        all
    }
}

#[cfg(test)]
mod tests {
    use super::*;

    fn check(expected: Vec<Vec<i32>>, mut result: Vec<Vec<i32>>) {
        for entry in &mut result {
            entry.sort();
        }
        assert_eq!(expected, result);
    }

    #[test]
    fn test37() {
        let expected = vec![vec![1, 2, 4]];
        check(expected, Solution::combination_sum_iii(3, 7));
    }

    #[test]
    fn test39() {
        let expected = vec![vec![1, 2, 6], vec![1, 3, 5], vec![2, 3, 4]];
        check(expected, Solution::combination_sum_iii(3, 9));
    }
}
