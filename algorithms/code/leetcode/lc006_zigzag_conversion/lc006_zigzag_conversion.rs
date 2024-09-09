// https://leetcode.com/problems/zigzag-conversion/

pub struct Solution {}

impl Solution {
    pub fn convert(s: String, num_rows: i32) -> String {
        if s.is_empty() || num_rows < 1 {
            return "".to_string();
        }
        let mut buffer = String::new();
        let n = s.len();
        let rows = num_rows as usize;
        let mut scan = (rows - 1) * 2;
        if scan == 0 {
            scan = 1;
        }
        for row in 0..rows {
            for i in (row..n).step_by(scan) {
                buffer.push(s.chars().nth(i).unwrap());
                if 0 < row && row < rows - 1 {
                    let offset = (rows - 1 - row) * 2;
                    if i + offset >= n {
                        break;
                    }
                    buffer.push(s.chars().nth(i + offset).unwrap());
                }
            }
        }
        buffer
    }
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_1() {
        assert_eq!(
            Solution::convert("PAYPALISHIRING".to_string(), 3),
            "PAHNAPLSIIGYIR".to_string(),
        );
    }

    #[test]
    fn test_2() {
        assert_eq!(
            Solution::convert("PAYPALISHIRING".to_string(), 4),
            "PINALSIGYAHRPI".to_string(),
        );
    }

    #[test]
    fn test_3() {
        assert_eq!(Solution::convert("A".to_string(), 1), "A".to_string());
    }

    #[test]
    fn test_abcd() {
        assert_eq!(Solution::convert("ABCD".to_string(), 3), "ABDC".to_string());
    }

    #[test]
    fn test_abc() {
        assert_eq!(Solution::convert("ABC".to_string(), 2), "ACB".to_string());
    }

    #[test]
    fn test_nothing() {
        assert_eq!(Solution::convert(String::new(), 2), String::new());
    }

    #[test]
    fn test_zero() {
        assert_eq!(Solution::convert("A".to_string(), 0), "".to_string());
    }
}
