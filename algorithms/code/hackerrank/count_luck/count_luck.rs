// https://www.hackerrank.com/challenges/count-luck

use std::collections::VecDeque;

use crate::hackerrank::tester::{read_input, write_and_check_output};

#[derive(Clone, Copy)]
struct Here {
    row: i32,
    col: i32,
}

fn look_around(forest: &[Vec<char>], at: &Here) -> Vec<Here> {
    let deltas = [(0, 1), (0, -1), (1, 0), (-1, 0)];
    let mut ways: Vec<Here> = Vec::new();
    for delta in deltas.iter() {
        let dr = at.row + delta.0;
        let dc = at.col + delta.1;
        if dr < 0 || forest.len() <= dr as usize {
            continue;
        }
        if dc < 0 || forest[dr as usize].len() <= dc as usize {
            continue;
        }
        if 'X' == forest[dr as usize][dc as usize] {
            continue;
        }
        ways.push(Here { row: dr, col: dc });
    }
    ways
}

pub fn count_luck(matrix: &[String], k: i32) -> String {
    let mut queue: VecDeque<Here> = VecDeque::new();
    let mut counts: Vec<i32> = vec![0];
    let mut forest: Vec<Vec<char>> = matrix.iter().map(|s| s.chars().collect()).collect();
    for (r, row) in forest.iter().enumerate() {
        for (c, _) in row.iter().enumerate() {
            if 'M' == row[c] {
                queue.push_back(Here {
                    row: r as i32,
                    col: c as i32,
                });
            }
        }
    }
    while let Some(at) = queue.pop_front() {
        let count = counts[0];
        counts.remove(0);
        if '*' == forest[at.row as usize][at.col as usize] {
            if count == k {
                return "Impressed".to_string();
            }
            return "Oops!".to_string();
        }
        forest[at.row as usize][at.col as usize] = 'X';
        let ways = look_around(&forest, &at);
        for way in ways.iter() {
            queue.push_back(*way);
            if ways.len() > 1 {
                counts.push(count + 1);
            } else {
                counts.push(count);
            }
        }
    }
    "Oops!".to_string()
}

pub fn main_tested(name: &str) {
    let origin = file!();
    let input = read_input(origin, name);
    let tests = input[0][0].parse::<usize>().unwrap();
    let mut results: Vec<String> = Vec::new();
    let mut offset = 1;
    for _ in 0..tests {
        let n = input[offset][0].parse::<usize>().unwrap();
        let matrix: Vec<String> = input[offset + 1..offset + 1 + n]
            .iter()
            .map(|line| line[0].clone())
            .collect();
        let k = input[offset + 1 + n][0].parse::<i32>().unwrap();
        offset += 1 + n + 1;
        results.push(count_luck(&matrix, k));
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
    fn test_empty() {
        let result = count_luck(&[], 0);
        assert_eq!(result, "Oops!");
    }
}
