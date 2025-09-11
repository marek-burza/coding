// https://leetcode.com/problems/reverse-string/
// #easy

pub struct Solution;

impl Solution {
    #[allow(clippy::ptr_arg)]
    pub fn reverse_string(s: &mut Vec<char>) {
        let length = s.len();
        for i in 0..((length as f32 / 2.0).round() as usize) {
            s.swap(i, length - 1 - i);
        }
    }
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    pub fn test_example() {
        let mut s = vec!['h', 'e', 'l', 'l', 'o'];
        Solution::reverse_string(&mut s);
        assert_eq!(s, vec!['o', 'l', 'l', 'e', 'h']);
    }
}
