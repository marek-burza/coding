// https://leetcode.com/problems/reverse-vowels-of-a-string/
// #easy

pub struct Solution;

impl Solution {
    const VOWELS: [char; 10] = ['a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U'];

    fn is_vowel(letter: char) -> bool {
        for vowel in Self::VOWELS {
            if vowel == letter {
                return true;
            }
        }
        false
    }

    pub fn reverse_vowels(s: String) -> String {
        let mut text: Vec<char> = s.chars().collect();
        let mut a: i32 = 0;
        let mut z: i32 = (text.len() - 1) as i32;
        while a < z {
            while a < text.len() as i32 && !Self::is_vowel(text[a as usize]) {
                a += 1;
            }
            while z >= 0 && !Self::is_vowel(text[z as usize]) {
                z -= 1;
            }
            if a < z {
                (text[a as usize], text[z as usize]) = (text[z as usize], text[a as usize]);
                a += 1;
                z -= 1;
            }
        }
        text.iter().collect()
    }
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_example_1() {
        assert_eq!(Solution::reverse_vowels("hello".to_string()), "holle");
    }

    #[test]
    fn test_example_2() {
        assert_eq!(Solution::reverse_vowels("leotcede".to_string()), "leetcode");
    }

    #[test]
    fn test_oe() {
        assert_eq!(Solution::reverse_vowels("OE".to_string()), "EO");
    }

    #[test]
    fn test_zt() {
        assert_eq!(Solution::reverse_vowels("zt".to_string()), "zt");
    }
}
