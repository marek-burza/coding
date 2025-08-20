// https://leetcode.com/problems/triangle/
// #medium

pub struct Solution;

impl Solution {
    pub fn minimum_total(triangle: Vec<Vec<i32>>) -> i32 {
        if triangle.is_empty() || triangle[0].is_empty() {
            return 0;
        }
        let height = triangle.len();
        let mut sums: Vec<i32> = vec![0; triangle[height - 1].len()];
        sums[0] = triangle[0][0];
        for line in triangle.iter().take(height).skip(1) {
            let n = line.len();
            for i in (0..n).rev() {
                let left = if i == 0 { i32::MAX } else { sums[i - 1] };
                let right = if i == n - 1 { i32::MAX } else { sums[i] };
                sums[i] = std::cmp::min(left, right) + line[i];
            }
        }
        *sums.iter().min().unwrap()
    }
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_example() {
        let triangle = vec![vec![2], vec![3, 4], vec![6, 5, 7], vec![4, 1, 8, 3]];
        assert_eq!(Solution::minimum_total(triangle), 11);
    }

    #[test]
    fn test_nothing() {
        assert_eq!(Solution::minimum_total(vec![]), 0);
        assert_eq!(Solution::minimum_total(vec![vec![]]), 0);
    }
}
