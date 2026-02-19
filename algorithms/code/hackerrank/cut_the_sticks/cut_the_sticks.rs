// https://www.hackerrank.com/challenges/cut-the-sticks

use crate::hackerrank::tester::{read_input, write_and_check_output};

pub fn cut_the_sticks(arr: &mut [i32]) -> Vec<i32> {
    arr.sort();
    let mut cuts = vec![];
    let mut count = 0;
    for i in (0..arr.len()).rev() {
        count += 1;
        if i == 0 || arr[i] != arr[i - 1] {
            cuts.push(count);
        }
    }
    cuts.reverse();
    cuts
}

pub fn main_tested(name: &str) {
    let origin = file!();
    let input = read_input(origin, name);
    let mut results: Vec<String> = Vec::new();
    let mut arr: Vec<i32> = input[1]
        .iter()
        .map(|s| s.to_string().parse::<i32>().unwrap())
        .collect();
    results.push(
        cut_the_sticks(&mut arr)
            .iter()
            .map(|i| i.to_string())
            .collect::<Vec<String>>()
            .join("\n"),
    );
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
}
