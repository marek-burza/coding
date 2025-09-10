// https://leetcode.com/problems/ransom-note/
// #easy

pub struct Solution;

impl Solution {
    pub fn can_construct(ransom_note: String, magazine: String) -> bool {
        let mut counts = [0; 256];
        for letter in magazine.chars() {
            counts[letter as usize] += 1;
        }
        for letter in ransom_note.chars() {
            counts[letter as usize] -= 1;
            if counts[letter as usize] < 0 {
                return false;
            }
        }
        true
    }
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_example_ab() {
        assert!(!Solution::can_construct("a".into(), "b".into()));
    }

    #[test]
    fn test_example_aa_ab() {
        assert!(!Solution::can_construct("aa".into(), "ab".into()));
    }

    #[test]
    fn test_example_aa_aab() {
        assert!(Solution::can_construct("aa".into(), "aab".into()));
    }
}
