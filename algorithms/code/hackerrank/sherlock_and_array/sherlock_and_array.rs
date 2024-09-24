// https://www.hackerrank.com/challenges/sherlock-and-array

use crate::hackerrank::tester::{read_input, write_and_check_output};

pub fn balanced_sums(arr: &[i32]) -> String {
    let mut left = vec![0; arr.len()];
    let mut right = vec![0; arr.len()];
    for i in 1..arr.len() {
        left[i] = left[i - 1] + arr[i - 1];
        right[arr.len() - i - 1] = right[arr.len() - i] + arr[arr.len() - i];
    }
    for i in 0..arr.len() {
        if left[i] == right[i] {
            return "YES".to_string();
        }
    }
    "NO".to_string()
}

pub fn main_tested(name: &str) {
    let origin = file!();
    let input = read_input(origin, name);
    let t = input[0][0].parse::<u32>().unwrap();
    let mut results: Vec<String> = Vec::new();
    let mut offset = 2;
    for _ in 0..t {
        let arr: Vec<i32> = input[offset]
            .iter()
            .map(|s| s.to_string().parse::<i32>().unwrap())
            .collect();
        offset += 2;
        results.push(balanced_sums(&arr));
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

    #[test]
    fn test00() {
        main_tested("00");
    }
}
