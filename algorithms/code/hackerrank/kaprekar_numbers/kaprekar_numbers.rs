// https://www.hackerrank.com/challenges/kaprekar-numbers

use crate::hackerrank::tester::{read_input, write_and_check_output};

pub fn kaprekar_numbers(p: i32, q: i32) -> Vec<String> {
    let mut found = vec![];
    for n in p..=q {
        let digits_count = 1 + (n as f64).log10() as u32;
        let nn = n as i64 * n as i64;
        let splitter = 10_i64.pow(digits_count);
        let r = nn / splitter;
        let l = nn % splitter;
        if n as i64 == r + l {
            found.push(n.to_string());
        }
    }
    if found.is_empty() {
        vec!["INVALID".to_string(), "RANGE".to_string()]
    } else {
        found
    }
}

pub fn main_tested(name: &str) {
    let origin = file!();
    let mut input = read_input(origin, name);
    let mut results: Vec<String> = Vec::new();
    let p = input.next().unwrap().trim().parse::<i32>().unwrap();
    let q = input.next().unwrap().trim().parse::<i32>().unwrap();
    results.push(
        kaprekar_numbers(p, q)
            .iter()
            .map(|i| i.to_string())
            .collect::<Vec<String>>()
            .join(" "),
    );
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
    fn test06() {
        main_tested("06");
    }

    #[test]
    fn test6() {
        main_tested("6");
    }
}
