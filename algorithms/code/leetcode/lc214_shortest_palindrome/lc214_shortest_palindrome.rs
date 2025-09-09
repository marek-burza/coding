// https://leetcode.com/problems/shortest-palindrome/
// #hard

pub struct Solution;

impl Solution {
    pub fn shortest_palindrome(s: String) -> String {
        if s.is_empty() {
            return s;
        }
        let a = format!("{}{}", s, s.chars().rev().collect::<String>());
        let mut cont = vec![0; a.len()];
        cont[0] = 0;
        for i in 1..a.len() {
            let mut index = cont[i - 1];
            while index > 0 && a.chars().nth(index) != a.chars().nth(i) {
                index = cont[index - 1];
            }
            cont[i] = index
                + if a.chars().nth(index) == a.chars().nth(i) {
                    1
                } else {
                    0
                };
        }
        format!(
            "{}{}",
            s[cont[cont.len() - 1]..].chars().rev().collect::<String>(),
            s,
        )
    }
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_aacecaaa() {
        assert_eq!(
            Solution::shortest_palindrome("aacecaaa".into()),
            "aaacecaaa",
        );
    }

    #[test]
    fn test_abcd() {
        assert_eq!(Solution::shortest_palindrome("abcd".into()), "dcbabcd");
    }

    #[test]
    fn test_nothing() {
        assert_eq!(Solution::shortest_palindrome("".into()), "");
    }
}
