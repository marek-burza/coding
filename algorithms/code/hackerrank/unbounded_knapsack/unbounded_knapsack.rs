// https://www.hackerrank.com/challenges/unbounded-knapsack

use crate::hackerrank::tester::{read_input, write_and_check_output};
use std::cmp::max;

pub fn unbounded_knapsack(k: i32, arr: &[i32]) -> i32 {
    let n = arr.len();
    let mut m = vec![0; k as usize + 1];
    for wi in 0..=k {
        for vi in 0..n {
            if arr[vi] <= wi {
                m[wi as usize] = max(m[wi as usize], m[(wi - arr[vi]) as usize] + arr[vi]);
            }
        }
    }
    m[k as usize]
}

pub fn main_tested(name: &str) {
    let origin = file!();
    let mut input = read_input(origin, name);
    let t = input.next().unwrap().trim().parse::<i32>().unwrap();
    let mut results: Vec<String> = Vec::new();
    for _ in 0..t {
        let first_line: Vec<String> = input
            .next()
            .unwrap()
            .split(' ')
            .map(|s| s.to_string())
            .collect();
        // let n = first_line[0].trim().parse::<i32>().unwrap();
        let k = first_line[1].trim().parse::<i32>().unwrap();
        let arr: Vec<i32> = input
            .next()
            .unwrap()
            .trim_end()
            .split(' ')
            .map(|s| s.to_string().parse::<i32>().unwrap())
            .collect();
        let result = unbounded_knapsack(k, &arr);
        results.push(result.to_string());
    }
    write_and_check_output(origin, name, &results);
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_example() {
        main_tested("example");
    }
}
