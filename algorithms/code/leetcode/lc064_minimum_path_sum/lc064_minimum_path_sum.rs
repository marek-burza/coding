// https://leetcode.com/problems/minimum-path-sum/
// #medium

use std::collections::VecDeque;

pub struct Solution;

impl Solution {
    pub fn min_path_sum(grid: Vec<Vec<i32>>) -> i32 {
        let rows = grid.len();
        let cols = grid[0].len();
        let mut visited = vec![vec![false; cols]; rows];
        let mut sums = vec![vec![i32::MAX; cols]; rows];
        sums[0][0] = grid[0][0];
        let mut queue = VecDeque::new();
        queue.push_back((0, 0));
        while !queue.is_empty() {
            let at = queue.pop_front().unwrap();
            if !visited[at.0][at.1] {
                visited[at.0][at.1] = true;
                if at.0 + 1 < rows {
                    let right = (at.0 + 1, at.1);
                    queue.push_back(right);
                    let sum = sums[at.0][at.1] + grid[right.0][right.1];
                    if sum < sums[right.0][right.1] {
                        sums[right.0][right.1] = sum;
                    }
                }
                if at.1 + 1 < cols {
                    let down = (at.0, at.1 + 1);
                    queue.push_back(down);
                    let sum = sums[at.0][at.1] + grid[down.0][down.1];
                    // if sum < sums[down.0][down.1] {
                    sums[down.0][down.1] = sum;
                    // }
                }
            }
        }
        sums[sums.len() - 1][sums[sums.len() - 1].len() - 1]
    }
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_example() {
        let grid = vec![
            vec![1, 1, 2, 2],
            vec![2, 1, 2, 2],
            vec![2, 1, 1, 2],
            vec![2, 2, 1, 1],
        ];
        assert_eq!(Solution::min_path_sum(grid), 7);
    }
}
