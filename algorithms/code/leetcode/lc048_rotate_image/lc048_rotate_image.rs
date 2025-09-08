// https://leetcode.com/problems/rotate-image/
// #medium

pub struct Solution;

impl Solution {
    #[allow(clippy::ptr_arg)]
    pub fn rotate(matrix: &mut Vec<Vec<i32>>) {
        let matrix_length = matrix.len();
        for row in 0..((matrix_length / 2) + (matrix_length & 1)) {
            for column in row..(matrix_length - 1 - row) {
                let exchange = matrix[row][column];
                matrix[row][column] = matrix[matrix_length - 1 - column][row];
                matrix[matrix_length - 1 - column][row] =
                    matrix[matrix_length - 1 - row][matrix_length - 1 - column];
                matrix[matrix_length - 1 - row][matrix_length - 1 - column] =
                    matrix[column][matrix_length - 1 - row];
                matrix[column][matrix_length - 1 - row] = exchange;
            }
        }
    }
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_even() {
        let mut matrix = vec![vec![0, 1], vec![2, 3]];
        let expected = vec![vec![2, 0], vec![3, 1]];
        Solution::rotate(&mut matrix);
        assert_eq!(matrix, expected);
    }

    #[test]
    fn test_odd() {
        let mut matrix = vec![vec![0, 1, 2], vec![3, 4, 5], vec![6, 7, 8]];
        let expected = vec![vec![6, 3, 0], vec![7, 4, 1], vec![8, 5, 2]];
        Solution::rotate(&mut matrix);
        assert_eq!(matrix, expected);
    }
}
