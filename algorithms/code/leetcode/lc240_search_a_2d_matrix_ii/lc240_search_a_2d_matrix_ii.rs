// https://leetcode.com/problems/search-a-2d-matrix-ii/
// #medium

pub struct Solution;

impl Solution {
    fn search_matrix_inner(
        matrix: &mut Vec<Vec<i32>>,
        target: i32,
        row_a: usize,
        row_z: usize,
        col_a: usize,
        col_z: usize,
    ) -> bool {
        if row_a == row_z && col_a == col_z {
            return matrix[row_z][col_z] == target;
        }

        // if target < matrix[row_a][col_a] || matrix[row_z][col_z] < target {
        //     return false;
        // }

        let row_m = (row_a + row_z) >> 1;
        let col_m = (col_a + col_z) >> 1;
        let cols = col_a < col_z;
        let rows = row_a < row_z;
        if target <= matrix[row_m][col_m]
            && Self::search_matrix_inner(matrix, target, row_a, row_m, col_a, col_m)
        {
            return true;
        }
        if cols
            && target <= matrix[row_m][col_z]
            && Self::search_matrix_inner(matrix, target, row_a, row_m, col_m + 1, col_z)
        {
            return true;
        }
        if rows
            && target <= matrix[row_z][col_m]
            && Self::search_matrix_inner(matrix, target, row_m + 1, row_z, col_a, col_m)
        {
            return true;
        }

        if !(rows && cols) {
            false
        } else {
            Self::search_matrix_inner(matrix, target, row_m + 1, row_z, col_m + 1, col_z)
        }
    }

    pub fn search_matrix(matrix: &mut Vec<Vec<i32>>, target: i32) -> bool {
        Self::search_matrix_inner(matrix, target, 0, matrix.len() - 1, 0, matrix[0].len() - 1)
    }
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_example_1() {
        let mut matrix = vec![
            vec![1, 4, 7, 11, 15],
            vec![2, 5, 8, 12, 19],
            vec![3, 6, 9, 16, 22],
            vec![10, 13, 14, 17, 24],
            vec![18, 21, 23, 26, 30],
        ];
        assert!(Solution::search_matrix(&mut matrix, 5));
    }

    #[test]
    fn test_example_2() {
        let mut matrix = vec![
            vec![1, 4, 7, 11, 15],
            vec![2, 5, 8, 12, 19],
            vec![3, 6, 9, 16, 22],
            vec![10, 13, 14, 17, 24],
            vec![18, 21, 23, 26, 30],
        ];
        assert!(!Solution::search_matrix(&mut matrix, 20));
    }

    #[test]
    fn test_other_1() {
        let mut matrix = vec![vec![1, 4], vec![2, 5]];
        assert!(Solution::search_matrix(&mut matrix, 2));
    }

    #[test]
    fn test_other_2() {
        let mut matrix = vec![vec![-1, 3]];
        assert!(!Solution::search_matrix(&mut matrix, 1));
    }

    #[test]
    fn test_other_3() {
        let mut matrix = vec![
            vec![1, 2, 3, 4, 5],
            vec![6, 7, 8, 9, 10],
            vec![11, 12, 13, 14, 15],
            vec![16, 17, 18, 19, 20],
            vec![21, 22, 23, 24, 25],
        ];
        assert!(Solution::search_matrix(&mut matrix, 5));
    }
}
