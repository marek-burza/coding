// https://leetcode.com/problems/first-unique-character-in-a-string/
// #easy

pub struct Solution;

impl Solution {
    pub fn first_uniq_char(s: String) -> i32 {
        const SIZE: usize = 'z' as usize - 'a' as usize + 1;
        let mut count = [0; SIZE];
        let mut index = [0; SIZE];
        let length = s.len();
        for i in (0..length).rev() {
            let key = s.chars().nth(i).unwrap() as usize - 'a' as usize;
            index[key] = i;
            count[key] += 1;
        }
        let mut min = None;
        for i in 0..SIZE {
            if count[i] == 1 && (min.is_none() || index[i] < min.unwrap()) {
                min = Some(index[i]);
            }
        }
        if let Some(min) = min {
            min as i32
        } else {
            -1
        }
    }
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_leetcode() {
        assert_eq!(Solution::first_uniq_char("leetcode".to_string()), 0);
    }

    #[test]
    fn test_loveleetcode() {
        assert_eq!(Solution::first_uniq_char("loveleetcode".to_string()), 2);
    }

    #[test]
    fn test_empty() {
        assert_eq!(Solution::first_uniq_char("".into()), -1);
    }
}
