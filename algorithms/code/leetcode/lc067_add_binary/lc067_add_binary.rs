// https://leetcode.com/problems/add-binary/
// #easy

pub struct Solution;

impl Solution {
    pub fn add_binary(a: &str, b: &str) -> String {
        let ar = a.chars().rev().collect::<Vec<char>>();
        let br = b.chars().rev().collect::<Vec<char>>();
        let mut result = String::new();
        let mut carry = 0;
        for i in 0..std::cmp::max(ar.len(), br.len()) {
            let mut sum = carry;
            sum += if i < ar.len() {
                (ar[i] as i32) - ('0' as i32)
            } else {
                0
            };
            sum += if i < br.len() {
                (br[i] as i32) - ('0' as i32)
            } else {
                0
            };
            carry = sum >> 1;
            result.push(if (sum & 1) == 0 { '0' } else { '1' });
        }
        if carry == 1 {
            result.push('1');
        }
        result.chars().rev().collect::<String>()
    }
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_example_1() {
        assert_eq!(Solution::add_binary("11", "1"), "100");
    }

    #[test]
    fn test_example_2() {
        assert_eq!(Solution::add_binary("1010", "1011"), "10101");
    }

    #[test]
    fn test_example_1_reversed() {
        assert_eq!(Solution::add_binary("1", "11"), "100");
    }

    #[test]
    fn test_no_carry() {
        assert_eq!(Solution::add_binary("1", "0"), "1");
    }
}
