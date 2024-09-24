// https://www.hackerrank.com/challenges/non-divisible-subset

use crate::hackerrank::tester::{read_input, write_and_check_output};
use std::collections::{HashMap, HashSet};

pub fn non_divisible_subset(k: i32, s: &[i32]) -> i32 {
    let mut counted = HashMap::<i32, i32>::new();
    for value in s {
        let rest = value % k;
        *counted.entry(rest).or_insert(0) += 1;
    }
    let mut ok = HashSet::<i32>::new();
    for (a, count_a) in &counted {
        let b = k - a;
        if *a == 0 || *a == b {
            continue;
        }
        if let Some(count_b) = counted.get(&b) {
            if count_a > count_b {
                ok.insert(*a);
            } else {
                ok.insert(b);
            }
        } else {
            ok.insert(*a);
        }
    }
    let mut total = 0;
    for a in &ok {
        total += counted.get(a).unwrap();
    }
    if counted.contains_key(&0) {
        total += 1;
    }
    if k & 1 == 0 && counted.contains_key(&(k >> 1)) {
        total += 1;
    }
    total
}

pub fn main_tested(name: &str) {
    let origin = file!();
    let input = read_input(origin, name);
    let mut results: Vec<String> = Vec::new();
    let k = input[0][1].trim().parse::<i32>().unwrap();
    let s: Vec<i32> = input[1]
        .iter()
        .map(|s| s.to_string().parse::<i32>().unwrap())
        .collect();
    results.push(non_divisible_subset(k, &s).to_string());
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
    fn test07() {
        main_tested("07");
    }

    #[test]
    fn test16() {
        main_tested("16");
    }
}
