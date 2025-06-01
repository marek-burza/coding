// https://leetcode.com/problems/integer-to-english-words/
// #hard

pub struct Solution;

impl Solution {
    const MAGNITUDE: [&str; 10] = [
        "",
        " Thousand",
        " Million",
        " Billion",
        " Trillion",
        " Quadrillion",
        " Quintillion",
        " Sextillion",
        " Septillion",
        " Octillion",
    ];

    const TENS: [&str; 10] = [
        "", "Ten", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety",
    ];

    const DIGITS: [&str; 20] = [
        "",
        "One",
        "Two",
        "Three",
        "Four",
        "Five",
        "Six",
        "Seven",
        "Eight",
        "Nine",
        "Ten",
        "Eleven",
        "Twelve",
        "Thirteen",
        "Fourteen",
        "Fifteen",
        "Sixteen",
        "Seventeen",
        "Eighteen",
        "Nineteen",
    ];

    fn triple_to_word(mut i: i32) -> String {
        let mut result = String::new();
        if i >= 100 {
            result.push_str(Self::DIGITS[(i / 100) as usize]);
            result.push_str(" Hundred");
            i %= 100;
        }
        if 0 != i && !result.is_empty() {
            result.push(' ');
        }
        if i <= 19 {
            result.push_str(Self::DIGITS[i as usize]);
        } else {
            result.push_str(Self::TENS[(i / 10) as usize]);
            i %= 10;
            if 0 != i {
                result.push(' ');
                result.push_str(Self::DIGITS[i as usize]);
            }
        }
        result
    }

    pub fn number_to_words(mut i: i32) -> String {
        if i == 0 {
            return "Zero".to_string();
        }
        let mut result = String::new();
        let mut position = 0;
        while 0 != i {
            let vocalization = Self::triple_to_word(i % 1000);
            if !vocalization.is_empty() {
                if !result.is_empty() {
                    result.insert(0, ' ');
                }
                result.insert_str(0, Self::MAGNITUDE[position]);
                result.insert_str(0, &vocalization);
            }
            i /= 1000;
            position += 1;
        }
        result
    }
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_123() {
        assert_eq!(
            "One Hundred Twenty Three".to_string(),
            Solution::number_to_words(123)
        );
    }

    #[test]
    fn test_12345() {
        assert_eq!(
            "Twelve Thousand Three Hundred Forty Five".to_string(),
            Solution::number_to_words(12345)
        );
    }

    #[test]
    fn test_1234567() {
        assert_eq!(
            "One Million Two Hundred Thirty Four Thousand Five Hundred Sixty Seven".to_string(),
            Solution::number_to_words(1234567)
        );
    }

    #[test]
    fn test_91() {
        assert_eq!("Ninety One".to_string(), Solution::number_to_words(91));
    }

    #[test]
    fn test_19() {
        assert_eq!("Nineteen".to_string(), Solution::number_to_words(19));
    }

    #[test]
    fn test_100() {
        assert_eq!("One Hundred".to_string(), Solution::number_to_words(100));
    }

    #[test]
    fn test_0() {
        assert_eq!("Zero".to_string(), Solution::number_to_words(0));
    }

    #[test]
    fn test_1000() {
        assert_eq!("One Thousand".to_string(), Solution::number_to_words(1000));
    }

    #[test]
    fn test_20() {
        assert_eq!("Twenty".to_string(), Solution::number_to_words(20));
    }
}
