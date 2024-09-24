// https://www.hackerrank.com/challenges/cavity-map

use crate::hackerrank::tester::{read_input, write_and_check_output};

pub fn cavity_map(grid: &[String]) -> Vec<String> {
    let deltas = [(0, 1), (1, 0), (0, -1), (-1, 0)];
    let n = grid.len();
    let mut cells = vec![vec![' '; n]; n];
    for i in 0..n {
        let line = grid[i].chars().collect::<Vec<_>>();
        for (j, line_j) in line.iter().enumerate() {
            cells[i][j] = *line_j;
        }
    }
    for i in 1..(n - 1) {
        for j in 1..(n - 1) {
            let mut deeper = true;
            for delta in deltas.iter() {
                if cells[(i as i32 + delta.0) as usize][(j as i32 + delta.1) as usize]
                    >= cells[i][j]
                {
                    deeper = false;
                }
            }
            if deeper {
                cells[i][j] = 'X';
            }
        }
    }
    let mut result = vec!["".to_string(); n];
    for i in 0..n {
        result[i] = cells[i].clone().into_iter().collect();
    }
    result
}

pub fn main_tested(name: &str) {
    let origin = file!();
    let input = read_input(origin, name);
    let n = input[0][0].parse::<usize>().unwrap();
    let grid: Vec<String> = input[1..1 + n].iter().map(|line| line[0].clone()).collect();
    let results = cavity_map(&grid);
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
