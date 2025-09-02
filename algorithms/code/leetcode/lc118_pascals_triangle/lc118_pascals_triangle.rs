// https://leetcode.com/problems/pascals-triangle/
// #easy

pub struct Solution;

impl Solution {
    pub fn generate(num_rows: i32) -> Vec<Vec<i32>> {
        if num_rows < 0 {
            return Vec::new();
        }
        let mut triangle = Vec::new();
        for i in 0..(num_rows as usize) {
            let mut row = Vec::new();
            row.push(1);
            if 0 < i {
                let above: &Vec<i32> = &triangle[i - 1];
                for j in 0..(i - 1) {
                    row.push(above[j] + above[j + 1]);
                }
                row.push(1);
            }
            triangle.push(row);
        }
        triangle
    }
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_5() {
        let expected = vec![
            vec![1],
            vec![1, 1],
            vec![1, 2, 1],
            vec![1, 3, 3, 1],
            vec![1, 4, 6, 4, 1],
        ];
        let result = Solution::generate(5);
        assert_eq!(result, expected);
    }

    #[test]
    fn test_nothing() {
        assert!(Solution::generate(-1).is_empty());
    }
}
