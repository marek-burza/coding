// https://leetcode.com/problems/excel-sheet-column-number/
// #easy

pub struct Solution;

impl Solution {
    pub fn title_to_number(s: String) -> i32 {
        if s.is_empty() {
            return -1;
        }
        let mut result = 0;
        for c in s.chars() {
            result *= 26;
            result += c as i32 - 'A' as i32 + 1;
        }
        result
    }
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_a() {
        assert_eq!(Solution::title_to_number("A".into()), 1);
    }

    #[test]
    fn test_b() {
        assert_eq!(Solution::title_to_number("B".into()), 2);
    }

    #[test]
    fn test_c() {
        assert_eq!(Solution::title_to_number("C".into()), 3);
    }

    #[test]
    fn test_z() {
        assert_eq!(Solution::title_to_number("Z".into()), 26);
    }

    #[test]
    fn test_aa() {
        assert_eq!(Solution::title_to_number("AA".into()), 27);
    }

    #[test]
    fn test_ab() {
        assert_eq!(Solution::title_to_number("AB".into()), 28);
    }

    #[test]
    fn test_nothing() {
        assert_eq!(Solution::title_to_number("".into()), -1);
    }
}
