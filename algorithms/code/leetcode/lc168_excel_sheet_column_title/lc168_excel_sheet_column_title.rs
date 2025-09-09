// https://leetcode.com/problems/excel-sheet-column-title/
// #easy

pub struct Solution;

impl Solution {
    pub fn convert_to_title(mut n: i32) -> String {
        let mut buffer = String::new();
        loop {
            n -= 1;
            let digit = (b'A' + (n % 26) as u8) as char;
            buffer.push(digit);
            n -= n % 26;
            n /= 26;
            if n <= 0 {
                break;
            }
        }
        buffer.chars().rev().collect()
    }
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_1() {
        assert_eq!(Solution::convert_to_title(1), "A");
    }

    #[test]
    fn test_2() {
        assert_eq!(Solution::convert_to_title(2), "B");
    }

    #[test]
    fn test_3() {
        assert_eq!(Solution::convert_to_title(3), "C");
    }

    #[test]
    fn test_26() {
        assert_eq!(Solution::convert_to_title(26), "Z");
    }

    #[test]
    fn test_27() {
        assert_eq!(Solution::convert_to_title(27), "AA");
    }

    #[test]
    fn test_28() {
        assert_eq!(Solution::convert_to_title(28), "AB");
    }
}
