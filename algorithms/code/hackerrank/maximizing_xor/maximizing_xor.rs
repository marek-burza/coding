// https://www.hackerrank.com/challenges/maximizing-xor

use crate::hackerrank::tester::{read_input, write_and_check_output};

pub fn maximizing_xor(l: i32, r: i32) -> i32 {
    let mut maxed = l ^ r;
    maxed |= maxed >> 1;
    maxed |= maxed >> 2;
    maxed |= maxed >> 4;
    maxed |= maxed >> 8;
    maxed
}

pub fn main_tested(name: &str) {
    let origin = file!();
    let input = read_input(origin, name);
    let mut results: Vec<String> = Vec::new();
    let l = input[0][0].parse::<i32>().unwrap();
    let r = input[1][0].parse::<i32>().unwrap();
    results.push(maximizing_xor(l, r).to_string());
    write_and_check_output(origin, name, &results);
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test00() {
        main_tested("00");
    }

    #[test]
    fn test01() {
        main_tested("01");
    }

    #[test]
    fn test02() {
        main_tested("02");
    }
}
