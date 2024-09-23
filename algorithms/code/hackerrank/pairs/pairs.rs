// https://www.hackerrank.com/challenges/pairs

use crate::hackerrank::tester::{read_input, write_and_check_output};
use std::cmp::Ordering;

fn binary_search(array: &[i32], from_index: usize, to_index: usize, key: i32) -> i32 {
    let mut from_index = from_index;
    let mut to_index = to_index;
    while from_index <= to_index {
        let middle = (from_index + to_index) / 2;
        match array[middle].cmp(&key) {
            Ordering::Less => from_index = middle + 1,
            Ordering::Greater => to_index = middle - 1,
            Ordering::Equal => return middle as i32,
        }
    }
    -1
}

pub fn pairs(k: i32, arr: &mut [i32]) -> i32 {
    arr.sort();
    let mut count = 0;
    for i in 0..arr.len() - 1 {
        if binary_search(arr, i + 1, arr.len() - 1, arr[i] + k) > 0 {
            count += 1;
        }
    }
    count
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
    let k = first_line[1].trim().parse::<i32>().unwrap();
    let mut arr: Vec<i32> = input
        .next()
        .unwrap()
        .trim_end()
        .split(' ')
        .map(|s| s.to_string().parse::<i32>().unwrap())
        .collect();
    results.push(pairs(k, &mut arr).to_string());
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
    fn test_other() {
        assert_eq!(4, pairs(1, &mut [1, 5, 3, 4, 2]));
    }
}
