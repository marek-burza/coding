// https://www.hackerrank.com/challenges/missing-numbers

use crate::hackerrank::tester::{read_input, write_and_check_output};
use std::collections::HashSet;

fn missing_numbers(arr: &mut [i32], brr: &mut [i32]) -> Vec<i32> {
    let mut missing = HashSet::<i32>::new();
    arr.sort();
    brr.sort();
    let n = arr.len();
    // let m = brr.len();
    let mut i = 0;
    for brr_item in brr {
        if i < n {
            if arr[i] == *brr_item {
                i += 1;
            } else {
                missing.insert(*brr_item);
            }
        } else {
            missing.insert(*brr_item);
        }
    }
    let mut keys: Vec<i32> = missing.into_iter().collect();
    keys.sort();
    keys
}

pub fn main_tested(name: &str) {
    let origin = file!();
    let mut input = read_input(origin, name);
    let mut results: Vec<String> = Vec::new();
    input.next();
    let mut arr: Vec<i32> = input
        .next()
        .unwrap()
        .trim_end()
        .split(' ')
        .map(|s| s.to_string().parse::<i32>().unwrap())
        .collect();
    input.next();
    let mut brr: Vec<i32> = input
        .next()
        .unwrap()
        .trim_end()
        .split(' ')
        .map(|s| s.to_string().parse::<i32>().unwrap())
        .collect();
    results.push(
        missing_numbers(&mut arr, &mut brr)
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
    fn test01() {
        main_tested("01");
    }

    #[test]
    fn test03() {
        main_tested("03");
    }
}
