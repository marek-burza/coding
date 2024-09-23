// https://www.hackerrank.com/challenges/funny-string

use crate::hackerrank::tester::{read_input, write_and_check_output};

fn abs_diff(a: u16, b: u16) -> u16 {
    if a < b {
        return b - a;
    }
    a - b
}

fn funny_string(s: String) -> String {
    let sr: Vec<u16> = s.chars().map(|c| c as u16).collect();
    let n = sr.len();
    for i in 1..n {
        let forward = abs_diff(sr[i], sr[i - 1]);
        let backward = abs_diff(sr[n - i - 1], sr[n - i]);
        if forward != backward {
            return "Not Funny".to_string();
        }
    }
    "Funny".to_string()
}

pub fn main_tested(name: &str) {
    let origin = file!();
    let mut input = read_input(origin, name);
    let n = input.next().unwrap().trim().parse::<u32>().unwrap();
    let mut results: Vec<String> = Vec::new();
    for _ in 0..n {
        results.push(funny_string(input.next().unwrap().trim().to_string()));
    }
    write_and_check_output(origin, name, &results);
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_example() {
        main_tested("_example");
    }
}
