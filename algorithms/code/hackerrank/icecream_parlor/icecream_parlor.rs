// https://www.hackerrank.com/challenges/icecream-parlor

use crate::hackerrank::tester::{read_input, write_and_check_output};
use std::collections::HashMap;

pub fn icecream_parlor(m: i32, arr: &[i32]) -> Vec<i32> {
    let n = arr.len() as i32;
    let mut mapped = HashMap::<i32, i32>::new();
    for i in 0..n {
        mapped.insert(arr[i as usize], i);
    }
    for i in 0..n {
        let first = i;
        let value = m - arr[i as usize];
        if let Some(second) = mapped.get(&value) {
            if first == *second {
                continue;
            }
            return vec![first + 1, second + 1];
        }
    }
    vec![0, 0]
}

pub fn main_tested(name: &str) {
    let origin = file!();
    let mut input = read_input(origin, name);
    let mut results: Vec<String> = Vec::new();
    let t = input.next().unwrap().trim().parse::<u32>().unwrap();
    for _ in 0..t {
        let m = input.next().unwrap().trim().parse::<i32>().unwrap();
        input.next().unwrap().trim().parse::<i32>().unwrap(); // n
        let arr: Vec<i32> = input
            .next()
            .unwrap()
            .trim_end()
            .split(' ')
            .map(|s| s.to_string().parse::<i32>().unwrap())
            .collect();
        results.push(
            icecream_parlor(m, &arr)
                .iter()
                .map(|i| i.to_string())
                .collect::<Vec<String>>()
                .join(" "),
        );
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
    fn test02() {
        main_tested("02");
    }

    #[test]
    fn test_same() {
        let expected = vec![3, 4];
        let result = icecream_parlor(6, &[3, 1, 2, 4]);
        assert_eq!(expected, result);
    }

    #[test]
    fn test_none() {
        let expected = vec![0, 0];
        let result = icecream_parlor(10, &[3, 1, 2, 4]);
        assert_eq!(expected, result);
    }
}
