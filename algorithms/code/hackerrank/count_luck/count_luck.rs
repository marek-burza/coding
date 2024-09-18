// https://www.hackerrank.com/challenges/count-luck

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
    let mut queue: Vec<Here> = Vec::new();
    let mut counts: Vec<i32> = vec![0];
    let mut forest: Vec<Vec<char>> = matrix.iter().map(|s| s.chars().collect()).collect();
    for (r, row) in forest.iter().enumerate() {
        for (c, _) in row.iter().enumerate() {
            if 'M' == row[c] {
                queue.push(Here {
                    row: r as i32,
                    col: c as i32,
                });
            }
        }
    }
    while let Some(at) = queue.pop() {
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
            queue.push(*way);
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
        let n = first_line[0].trim().parse::<i32>().unwrap();
        // let m = first_line[1].trim().parse::<i32>().unwrap();
        let mut matrix: Vec<String> = Vec::with_capacity(n as usize);
        for _ in 0..n {
            let matrix_item = input.next().unwrap();
            matrix.push(matrix_item);
        }
        let k = input.next().unwrap().trim().parse::<i32>().unwrap();
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
