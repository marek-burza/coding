// https://leetcode.com/problems/number-of-islands/
// #medium

use std::collections::VecDeque;

pub struct Solution;

struct Item {
    x: i32,
    y: i32,
}

impl Solution {
    const DELTAS: [[i32; 2]; 4] = [[0, 1], [1, 0], [0, -1], [-1, 0]];

    fn land(grid: &mut [Vec<char>], x: i32, y: i32) -> bool {
        (0 <= x && (x as usize) < grid.len() && 0 <= y && (y as usize) < grid[x as usize].len())
            && grid[x as usize][y as usize] == '1'
    }

    fn traverse(grid: &mut [Vec<char>], x_value: i32, y_value: i32) -> bool {
        let mut queue = VecDeque::new();
        queue.push_back(Item {
            x: x_value,
            y: y_value,
        });
        let mut land = false;
        while !queue.is_empty() {
            let item = queue.pop_front().unwrap();
            let x = item.x;
            let y = item.y;
            let check = Self::land(grid, x, y);
            if check {
                land = true;
                grid[x as usize][y as usize] = '0';
                for delta in Self::DELTAS.iter() {
                    let xx = x + delta[0];
                    let yy = y + delta[1];
                    queue.push_back(Item { x: xx, y: yy });
                }
            }
        }
        land
    }

    pub fn num_islands(mut grid: Vec<Vec<char>>) -> i32 {
        if grid.is_empty() {
            return 0;
        }
        let mut count = 0;
        for x in 0..grid.len() {
            for y in 0..grid[x].len() {
                if Self::traverse(&mut grid, x as i32, y as i32) {
                    count += 1;
                }
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
        let grid = vec![vec!['1']];
        assert_eq!(Solution::num_islands(grid), 1);
    }

    #[test]
    fn test_other() {
        let grid = vec![
            vec!['1', '1', '0', '0', '0'],
            vec!['1', '1', '0', '0', '0'],
            vec!['0', '0', '1', '0', '0'],
            vec!['0', '0', '0', '1', '1'],
        ];
        assert_eq!(Solution::num_islands(grid), 3);
    }

    #[test]
    fn test_nothing() {
        assert_eq!(Solution::num_islands(vec![]), 0);
        assert_eq!(Solution::num_islands(vec![vec![]]), 0);
    }
}
