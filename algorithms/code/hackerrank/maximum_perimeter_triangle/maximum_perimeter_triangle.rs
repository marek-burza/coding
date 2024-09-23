// https://www.hackerrank.com/challenges/maximum-perimeter-triangle

use crate::hackerrank::tester::{read_input, write_and_check_output};

pub fn maximum_perimeter_triangle(sticks: &mut [i32]) -> Vec<i32> {
    sticks.sort_by(|a, b| b.cmp(a));
    for i in 2..sticks.len() {
        for j in 0..i - 1 {
            for k in (j + 1)..i {
                let a = sticks[i];
                let b = sticks[k];
                let c = sticks[j];
                if !(a + b <= c || a + c <= b) {
                    return vec![a, b, c];
                }
            }
        }
    }
    vec![-1]
}

pub fn main_tested(name: &str) {
    let origin = file!();
    let mut input = read_input(origin, name);
    let mut results: Vec<String> = Vec::new();
    input.next().unwrap().trim().parse::<i32>().unwrap(); // length
    let mut sticks: Vec<i32> = input
        .next()
        .unwrap()
        .trim_end()
        .split(' ')
        .map(|s| s.to_string().parse::<i32>().unwrap())
        .collect();
    results.push(
        maximum_perimeter_triangle(&mut sticks)
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
    fn test02() {
        main_tested("02");
    }

    #[test]
    fn test_degenerate() {
        let mut degenerate = vec![];
        let result = maximum_perimeter_triangle(&mut degenerate);
        assert_eq!(1, result.len());
        assert_eq!(-1, result[0]);
    }
}
