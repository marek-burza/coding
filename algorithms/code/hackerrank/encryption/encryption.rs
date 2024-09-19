// https://www.hackerrank.com/challenges/encryption

use crate::hackerrank::tester::{read_input, write_and_check_output};

pub fn encryption(plain: &str) -> String {
    let p: Vec<char> = plain.chars().collect();
    let length = p.len();
    let floor = (length as f64).sqrt() as usize;
    let ceil = (length as f64).sqrt().ceil() as usize;
    let mut rows: i32 = -1;
    let mut cols: i32 = -1;
    for c in (floor..=ceil).rev() {
        let extend = if length % c > 0 { 1 } else { 0 };
        let r = (length / c) + extend;
        if r * c >= length {
            rows = r as i32;
            cols = c as i32;
            break;
        }
    }
    let mut result = String::new();
    for c in 0..cols {
        if c != 0 {
            result.push(' ');
        }
        for r in 0..rows {
            let index = r * cols + c;
            if index < length as i32 {
                result.push(p[index as usize]);
            }
        }
    }
    result
}

pub fn main_tested(name: &str) {
    let origin = file!();
    let mut input = read_input(origin, name);
    let mut results: Vec<String> = Vec::new();
    let plain = input.next().unwrap();
    results.push(encryption(&plain));
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
    fn test_example_3() {
        main_tested("_example_3");
    }
}
