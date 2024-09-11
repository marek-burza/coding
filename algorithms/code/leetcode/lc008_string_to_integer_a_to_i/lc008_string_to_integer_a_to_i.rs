// https://leetcode.com/problems/string-to-integer-atoi/

pub struct Solution;

impl Solution {
    pub fn my_atoi(s: String) -> i32 {
        let mut result: i64 = 0;
        let mut sign: i64 = 1;
        let mut index = 0;
        while index < s.len() {
            if !s.chars().nth(index).unwrap().is_whitespace() {
                break;
            }
            index += 1;
        }
        if index < s.len() {
            match s.chars().nth(index).unwrap() {
                '-' => {
                    sign = -1;
                    index += 1;
                }
                '+' => index += 1,
                _ => {}
            };
            while index < s.len() && s.chars().nth(index).unwrap().is_ascii_digit() {
                result = result * 10 + s.chars().nth(index).unwrap() as i64 - '0' as i64;
                if result > i32::MAX.into() {
                    break;
                }
                index += 1;
            }
        }
        result *= sign;
        result.clamp(i32::MIN.into(), i32::MAX.into()) as i32
    }
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_minusminus3241() {
        assert_eq!(Solution::my_atoi(String::from("--3241")), 0);
    }

    #[test]
    fn test_plusplus3241() {
        assert_eq!(Solution::my_atoi(String::from("++3241")), 0);
    }

    #[test]
    fn test_minusplus3241() {
        assert_eq!(Solution::my_atoi(String::from("-+3241")), 0);
    }

    #[test]
    fn test_minus3241() {
        assert_eq!(Solution::my_atoi(String::from("-3241")), -3241);
    }

    #[test]
    fn test_spaceminusplus3241a() {
        assert_eq!(Solution::my_atoi(String::from(" -3241a")), -3241);
    }

    #[test]
    fn test_9223372036854775809() {
        assert_eq!(
            Solution::my_atoi(String::from("9223372036854775809")),
            i32::MAX,
        );
    }

    #[test]
    fn test_minus9223372036854775809() {
        assert_eq!(
            Solution::my_atoi(String::from("-9223372036854775809")),
            i32::MIN,
        );
    }

    #[test]
    fn test_nothing() {
        assert_eq!(Solution::my_atoi(String::from("nothing")), 0);
    }
}
