// https://www.hackerrank.com/challenges/extra-long-factorials

use crate::hackerrank::tester::{read_input, write_and_check_output};
use num_bigint::BigInt;

pub fn extra_long_factorials(n: u32) -> String {
    let mut result = BigInt::from(1);
    for i in 2..=n {
        result *= BigInt::from(i);
    }
    result.to_string()
}

pub fn main_tested(name: &str) {
    let origin = file!();
    let mut input = read_input(origin, name);
    let mut results: Vec<String> = Vec::new();
    for line in &mut input {
        let n = line.trim().parse::<u32>().unwrap();
        results.push(extra_long_factorials(n));
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
