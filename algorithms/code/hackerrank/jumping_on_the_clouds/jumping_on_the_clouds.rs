// https://www.hackerrank.com/challenges/jumping-on-the-clouds

use crate::hackerrank::tester::{read_input, write_and_check_output};

pub fn jumping_on_clouds(c: &[i32]) -> i32 {
    let n = c.len();
    let mut count = 0;
    let mut i: usize = 0;
    while i < n - 1 {
        if i + 2 > n - 1 {
            count += 1;
            break;
        }
        count += 1 + c[i + 2];
        i += (2 + c[i + 2]) as usize;
    }
    count
}

pub fn main_tested(name: &str) {
    let origin = file!();
    let input = read_input(origin, name);
    let mut results: Vec<String> = Vec::new();
    let arr: Vec<i32> = input[1]
        .iter()
        .map(|s| s.to_string().parse::<i32>().unwrap())
        .collect();
    results.push(jumping_on_clouds(&arr).to_string());
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
    fn test_missing_example() {
        let expected = 1;
        let result = jumping_on_clouds(&[0, 0]);
        assert_eq!(expected, result);
    }
}
