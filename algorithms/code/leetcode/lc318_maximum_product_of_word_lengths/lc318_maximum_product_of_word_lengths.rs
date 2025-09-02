// https://leetcode.com/problems/maximum-product-of-word-lengths/
// #medium

pub struct Solution;

impl Solution {
    pub fn max_product(words: Vec<String>) -> i32 {
        let mut signature: Vec<u32> = vec![0; words.len()];
        for (i, word) in words.iter().enumerate() {
            for c in word.chars() {
                signature[i] |= 1 << (c as usize - 'a' as usize);
            }
        }
        let mut maximum = 0;
        for i in 0..words.len() - 1 {
            for j in (i + 1)..words.len() {
                if (signature[i] & signature[j]) == 0 {
                    maximum = std::cmp::max(maximum, words[i].len() as i32 * words[j].len() as i32);
                }
            }
        }
        maximum
    }
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_example_1() {
        let input = vec![
            "abcw".to_string(),
            "baz".to_string(),
            "foo".to_string(),
            "bar".to_string(),
            "xtfn".to_string(),
            "abcdef".to_string(),
        ];
        assert_eq!(Solution::max_product(input), 16);
    }

    #[test]
    fn test_example_2() {
        let input = vec![
            "a".to_string(),
            "ab".to_string(),
            "abc".to_string(),
            "d".to_string(),
            "cd".to_string(),
            "bcd".to_string(),
            "abcd".to_string(),
        ];
        assert_eq!(Solution::max_product(input), 4);
    }

    #[test]
    fn test_example_3() {
        let input = vec![
            "a".to_string(),
            "aa".to_string(),
            "aaa".to_string(),
            "aaaa".to_string(),
        ];
        assert_eq!(Solution::max_product(input), 0);
    }
}
