// https://leetcode.com/problems/spiral-matrix-ii/
// #medium

pub struct Solution;

impl Solution {
    pub fn generate_matrix(n: i32) -> Vec<Vec<i32>> {
        let mut limits = [n - 1, n - 1, 0, 0];
        let restrict = [1, -1, -1, 1];
        let delta = [[0, 1], [1, 0], [0, -1], [-1, 0]];
        let mut indices = [0, -1];
        let mut matrix: Vec<Vec<i32>> = vec![vec![0; n as usize]; n as usize];
        let mut value = 1;
        let mut stage = 0;
        let mut index = 1;
        while limits[0] >= limits[2] && limits[1] >= limits[3] {
            loop {
                indices[0] += delta[stage][0];
                indices[1] += delta[stage][1];
                matrix[indices[0] as usize][indices[1] as usize] = value;
                value += 1;
                if indices[index] == limits[stage] {
                    break;
                }
            }
            limits[(stage + 3) % 4] += restrict[stage];
            stage = (stage + 1) % 4;
            index = (index + 1) % 2;
        }
        matrix
    }
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_example() {
        let expected = vec![vec![1, 2, 3], vec![8, 9, 4], vec![7, 6, 5]];
        let result = Solution::generate_matrix(3);
        assert_eq!(result, expected);
    }
}
