// https://leetcode.com/problems/set-matrix-zeroes/
// #medium

pub struct Solution;

impl Solution {
    pub fn set_zeroes(matrix: &mut [Vec<i32>]) {
        let mut row0 = false;
        #[allow(clippy::needless_range_loop)]
        for row in 0..matrix.len() {
            if matrix[row][0] == 0 {
                row0 = true;
            }
        }
        let mut col0 = false;
        for col in 0..matrix[0].len() {
            if matrix[0][col] == 0 {
                col0 = true;
            }
        }
        for row in 0..matrix.len() {
            for col in 1..matrix[row].len() {
                if matrix[row][col] == 0 {
                    matrix[row][0] = 0;
                    matrix[0][col] = 0;
                }
            }
        }
        for row in 1..matrix.len() {
            for col in 1..matrix[row].len() {
                if matrix[row][0] == 0 || matrix[0][col] == 0 {
                    matrix[row][col] = 0;
                }
            }
        }
        if row0 {
            #[allow(clippy::needless_range_loop)]
            for row in 0..matrix.len() {
                matrix[row][0] = 0;
            }
        }
        if col0 {
            for col in 0..matrix[0].len() {
                matrix[0][col] = 0;
            }
        }
    }
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_smaller_example_1() {
        let mut matrix = vec![vec![1, 0]];
        let expected = vec![vec![0, 0]];
        Solution::set_zeroes(&mut matrix);
        assert_eq!(expected, matrix);
    }

    #[test]
    fn test_smaller_example_2() {
        let mut matrix = vec![vec![0, 1]];
        let expected = vec![vec![0, 0]];
        Solution::set_zeroes(&mut matrix);
        assert_eq!(expected, matrix);
    }

    #[test]
    fn test_bigger_example() {
        let mut matrix = vec![
            vec![0, 0, 0, 5],
            vec![4, 3, 1, 4],
            vec![0, 1, 1, 4],
            vec![1, 2, 1, 3],
            vec![0, 0, 1, 1],
        ];
        let expected = vec![
            vec![0, 0, 0, 0],
            vec![0, 0, 0, 4],
            vec![0, 0, 0, 0],
            vec![0, 0, 0, 3],
            vec![0, 0, 0, 0],
        ];
        Solution::set_zeroes(&mut matrix);
        assert_eq!(expected, matrix);
    }

    #[test]
    fn test_other() {
        let mut matrix = vec![vec![1, 1, 1], vec![1, 0, 1], vec![1, 1, 1]];
        let expected = vec![vec![1, 0, 1], vec![0, 0, 0], vec![1, 0, 1]];
        Solution::set_zeroes(&mut matrix);
        assert_eq!(expected, matrix);
    }
}
