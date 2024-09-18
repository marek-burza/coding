// https://www.hackerrank.com/challenges/connected-cell-in-a-grid

use crate::hackerrank::tester::{read_input, write_and_check_output};

struct Here {
    row: i32,
    col: i32,
}

fn traverse(matrix: &mut [Vec<i32>], r: i32, c: i32) -> i32 {
    let deltas = [
        (-1, -1),
        (0, -1),
        (1, -1),
        (-1, 0),
        (1, 0),
        (-1, 1),
        (0, 1),
        (1, 1),
    ];
    let mut size: i32 = 0;
    let mut queue = Vec::new();
    queue.push(Here { row: r, col: c });
    while let Some(at) = queue.pop() {
        let r = at.row;
        let c = at.col;
        if matrix[r as usize][c as usize] == 1 {
            matrix[r as usize][c as usize] = -1;
            size += 1;
            for delta in deltas.iter() {
                let rd = delta.0;
                let cd = delta.1;
                let rn = r + rd;
                let cn = c + cd;
                if rn >= 0
                    && cn >= 0
                    && rn < matrix.len() as i32
                    && cn < matrix[rn as usize].len() as i32
                {
                    queue.push(Here { row: rn, col: cn });
                }
            }
        }
    }
    size
}

pub fn connected_cell(matrix: &mut [Vec<i32>]) -> i32 {
    let mut result: i32 = 0;
    for r in 0..matrix.len() {
        for c in 0..matrix[r].len() {
            let size = traverse(matrix, r as i32, c as i32);
            if size > result {
                result = size;
            }
        }
    }
    result
}

pub fn main_tested(name: &str) {
    let origin = file!();
    let mut input = read_input(origin, name);
    let n = input.next().unwrap().trim().parse::<i32>().unwrap();
    let m = input.next().unwrap().trim().parse::<i32>().unwrap();
    let mut matrix: Vec<Vec<i32>> = Vec::with_capacity(n as usize);
    for i in 0..n {
        matrix.push(Vec::with_capacity(m as usize));
        matrix[i as usize] = input
            .next()
            .unwrap()
            .trim_end()
            .split(' ')
            .map(|s| s.to_string().parse::<i32>().unwrap())
            .collect();
    }
    let results = [connected_cell(&mut matrix).to_string()];
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
