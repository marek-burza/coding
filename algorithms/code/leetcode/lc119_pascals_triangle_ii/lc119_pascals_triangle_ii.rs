// https://leetcode.com/problems/pascals-triangle-ii/
// #easy

pub struct Solution;

impl Solution {
    pub fn get_row(row_index: i32) -> Vec<i64> {
        let mut index = row_index;
        index += 1;
        if index < 0 {
            return Vec::new();
        }
        let mut previous = vec![];
        let mut current = vec![];
        for i in 0..index {
            current = vec![];
            current.push(1);
            if 0 < i {
                for j in 0..(i - 1) {
                    current.push(previous[j as usize] + previous[(j + 1) as usize]);
                }
                current.push(1);
            }
            previous = current.clone();
        }
        current
    }
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_3() {
        let expected = vec![1, 3, 3, 1];
        let result = Solution::get_row(3);
        assert_eq!(result, expected);
    }

    #[test]
    fn test_nothing() {
        assert!(Solution::get_row(-2).is_empty());
    }
}
