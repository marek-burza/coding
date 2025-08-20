// https://leetcode.com/problems/search-a-2d-matrix/
// #medium

pub struct Solution;

impl Solution {
    pub fn search_matrix(matrix: Vec<Vec<i32>>, target: i32) -> bool {
        let mut ra = 0;
        let mut rz = matrix.len() - 1;
        while ra < rz {
            let rm = 1 + (ra + rz) / 2;
            if target >= matrix[rm][0] {
                ra = rm;
            } else {
                rz = rm - 1;
            }
        }
        // if rz < 0 {
        //     return false;
        // }
        let mut ca = 0;
        let mut cz = matrix[ra].len() - 1;
        while ca < cz {
            let cm = 1 + (ca + cz) / 2;
            if target == matrix[ra][cm] {
                return true;
            }
            if target > matrix[ra][cm] {
                ca = cm + 1;
            } else {
                cz = cm - 1;
            }
        }
        ca == cz && target == matrix[ra][ca]
    }
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_example_1() {
        let matrix = vec![vec![1, 3, 5, 7], vec![10, 11, 16, 20], vec![23, 30, 34, 50]];
        assert!(Solution::search_matrix(matrix, 3));
    }

    #[test]
    fn test_example_2() {
        let matrix = vec![vec![1]];
        assert!(Solution::search_matrix(matrix, 1));
    }

    #[test]
    fn test_example_3() {
        let matrix = vec![vec![1]];
        assert!(!Solution::search_matrix(matrix, 0));
    }

    #[test]
    fn test_example_4() {
        let matrix = vec![vec![1, 1]];
        assert!(!Solution::search_matrix(matrix, 0));
    }

    #[test]
    fn test_example_5() {
        let matrix = vec![vec![1, 1]];
        assert!(!Solution::search_matrix(matrix, 2));
    }

    #[test]
    fn test_example_6() {
        let matrix = vec![vec![1], vec![3]];
        assert!(Solution::search_matrix(matrix, 1));
    }

    #[test]
    fn test_other() {
        let matrix = vec![vec![1, 3, 5, 7], vec![10, 11, 16, 20], vec![23, 30, 34, 60]];
        assert!(!Solution::search_matrix(matrix, 13));
    }
}
