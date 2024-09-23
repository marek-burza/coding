// https://www.hackerrank.com/challenges/library-fine

use crate::hackerrank::tester::{read_input, write_and_check_output};

pub fn library_fine(d1: i32, m1: i32, y1: i32, d2: i32, m2: i32, y2: i32) -> i32 {
    if y1 > y2 {
        return 10000;
    }
    if y1 == y2 {
        if m1 > m2 {
            return (m1 - m2) * 500;
        }
        if m1 == m2 && d1 > d2 {
            return (d1 - d2) * 15;
        }
    }
    0
}

pub fn main_tested(name: &str) {
    let origin = file!();
    let mut input = read_input(origin, name);
    let mut results: Vec<String> = Vec::new();
    let first_line: Vec<String> = input
        .next()
        .unwrap()
        .split(' ')
        .map(|s| s.to_string())
        .collect();
    let d1 = first_line[0].trim().parse::<i32>().unwrap();
    let m1 = first_line[1].trim().parse::<i32>().unwrap();
    let y1 = first_line[2].trim().parse::<i32>().unwrap();
    let second_line: Vec<String> = input
        .next()
        .unwrap()
        .split(' ')
        .map(|s| s.to_string())
        .collect();
    let d2 = second_line[0].trim().parse::<i32>().unwrap();
    let m2 = second_line[1].trim().parse::<i32>().unwrap();
    let y2 = second_line[2].trim().parse::<i32>().unwrap();
    results.push(library_fine(d1, m1, y1, d2, m2, y2).to_string());
    write_and_check_output(origin, name, &results);
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_example() {
        main_tested("_example");
    }

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

    #[test]
    fn test03() {
        main_tested("03");
    }

    #[test]
    fn test07() {
        main_tested("07");
    }

    #[test]
    fn test09() {
        main_tested("09");
    }

    #[test]
    fn test14() {
        main_tested("14");
    }

    #[test]
    fn test15() {
        main_tested("15");
    }

    #[test]
    fn test16() {
        main_tested("16");
    }
}
