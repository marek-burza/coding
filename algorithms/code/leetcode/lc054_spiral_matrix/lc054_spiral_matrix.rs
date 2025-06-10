// https://leetcode.com/problems/spiral-matrix/
// #medium

pub struct Solution;

impl Solution {
    const DELTAS: [[i32; 2]; 4] = [[1, 0], [0, 1], [-1, 0], [0, -1]];

    pub fn spiral_order(matrix: Vec<Vec<i32>>) -> Vec<i32> {
        let mut list = Vec::new();
        if matrix.is_empty() {
            return list;
        }
        let mut index = 0;
        let mut top = 0i32;
        let mut bottom = (matrix.len() - 1) as i32;
        let mut left = 0i32;
        let mut right = (matrix[0].len() - 1) as i32;
        let mut x = 0;
        let mut y = 0;
        while top <= bottom && left <= right {
            if x > right {
                index = 1;
                top += 1;
                y = top;
                x -= 1;
                continue;
            }
            if y > bottom {
                index = 2;
                right -= 1;
                x = right;
                y -= 1;
                continue;
            }
            if x < left {
                index = 3;
                bottom -= 1;
                y = bottom;
                x += 1;
                continue;
            }
            if y < top {
                index = 0;
                left += 1;
                x = left;
                y += 1;
                continue;
            }
            list.push(matrix[y as usize][x as usize]);
            x += Self::DELTAS[index][0];
            y += Self::DELTAS[index][1];
        }
        list
    }
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_258_and_40_minus_1() {
        let matrix = vec![vec![2, 5, 8], vec![4, 0, -1]];
        let expected = vec![2, 5, 8, -1, 0, 4];
        assert_eq!(Solution::spiral_order(matrix), expected);
    }

    #[test]
    fn test_25_and_840_minus_1() {
        let matrix = vec![vec![2, 5], vec![8, 4], vec![0, -1]];
        let expected = vec![2, 5, 4, -1, 0, 8];
        assert_eq!(Solution::spiral_order(matrix), expected);
    }

    #[test]
    fn test_nothing() {
        let matrix: Vec<Vec<i32>> = vec![];
        let expected: Vec<i32> = vec![];
        assert_eq!(Solution::spiral_order(matrix), expected);
    }
}
