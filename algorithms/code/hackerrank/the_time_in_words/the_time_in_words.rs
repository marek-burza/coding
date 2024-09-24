// https://www.hackerrank.com/challenges/the-time-in-words

use crate::hackerrank::tester::{read_input, write_and_check_output};

const LUT: [&str; 31] = [
    "zero",
    "one",
    "two",
    "three",
    "four",
    "five",
    "six",
    "seven",
    "eight",
    "nine",
    "ten",
    "eleven",
    "twelve",
    "thirteen",
    "fourteen",
    "fifteen",
    "sixteen",
    "seventeen",
    "eighteen",
    "nineteen",
    "twenty",
    "twenty one",
    "twenty two",
    "twenty three",
    "twenty four",
    "twenty five",
    "twenty six",
    "twenty seven",
    "twenty eight",
    "twenty nine",
    "thirty",
];

pub fn time_in_words(h: i32, m: i32) -> String {
    let mut h = h;
    let mut m = m;
    let mut result = "".to_string();
    if m != 0 {
        match m {
            30 => result += "half past ",
            15 => result += "quarter past ",
            45 => {
                result += "quarter to ";
                h = (h + 1) % 12;
            }
            _ => {
                if m < 30 {
                    result += LUT[m as usize];
                    if m == 1 {
                        result += " minute past ";
                    } else {
                        result += " minutes past ";
                    }
                } else {
                    m = 60 - m;
                    result += LUT[m as usize];
                    if m == 1 {
                        result += " minute to ";
                    } else {
                        result += " minutes to ";
                    }
                    h = (h + 1) % 12;
                }
            }
        };
    }
    result += LUT[h as usize];
    if m == 0 {
        result += " o' clock";
    }
    result
}

pub fn main_tested(name: &str) {
    let origin = file!();
    let mut input = read_input(origin, name);
    let h = input.next().unwrap().trim().parse::<i32>().unwrap();
    let m = input.next().unwrap().trim().parse::<i32>().unwrap();
    let results: Vec<String> = vec![time_in_words(h, m)];
    write_and_check_output(origin, name, &results);
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_example_0() {
        main_tested("_example_0");
    }

    #[test]
    fn test_example_1() {
        main_tested("_example_1");
    }

    #[test]
    fn test_example_2() {
        main_tested("_example_2");
    }

    #[test]
    fn test0359() {
        let result = time_in_words(3, 59);
        assert_eq!(result, "one minute to four");
    }

    #[test]
    fn test0301() {
        let result = time_in_words(3, 1);
        assert_eq!(result, "one minute past three");
    }

    #[test]
    fn test0345() {
        let result = time_in_words(3, 45);
        assert_eq!(result, "quarter to four");
    }

    #[test]
    fn test0330() {
        let result = time_in_words(3, 30);
        assert_eq!(result, "half past three");
    }

    #[test]
    fn test0320() {
        let result = time_in_words(3, 20);
        assert_eq!(result, "twenty minutes past three");
    }
}
