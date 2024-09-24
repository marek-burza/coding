// https://www.hackerrank.com/challenges/manasa-and-stones

use crate::hackerrank::tester::{read_input, write_and_check_output};

pub fn stones(n: i32, a: i32, b: i32) -> Vec<i32> {
    let mut result = vec![];
    let mut a = a;
    let mut b = b;
    if a > b {
        std::mem::swap(&mut a, &mut b);
    }
    let mut current = a * (n - 1);
    let delta = b - a;
    result.push(current);
    if a != b {
        for _ in 0..(n - 1) {
            current += delta;
            result.push(current);
        }
    }
    result
}

pub fn main_tested(name: &str) {
    let origin = file!();
    let input = read_input(origin, name);
    let mut results: Vec<String> = Vec::new();
    let t = input[0][0].parse::<u32>().unwrap();
    let mut offset = 0;
    for _ in 0..t {
        let n = input[offset + 1][0].parse::<i32>().unwrap();
        let a = input[offset + 2][0].parse::<i32>().unwrap();
        let b = input[offset + 3][0].parse::<i32>().unwrap();
        offset += 3;
        results.push(
            stones(n, a, b)
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
    fn test03() {
        main_tested("03");
    }
}
