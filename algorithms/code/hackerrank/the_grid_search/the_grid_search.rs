// https://www.hackerrank.com/challenges/the-grid-search

use crate::hackerrank::tester::{read_input, write_and_check_output};

pub fn grid_search(g: &[String], p: &[String]) -> String {
    let everything = g.join("");
    let mut at: usize = 0;
    loop {
        if let Some(result) = everything[at..].find(&p[0]) {
            at += result;
        } else {
            return "NO".to_string();
        }
        if at % g[0].len() + p[0].len() > g[0].len() {
            at += 1;
            continue;
        }
        let mut offset = at;
        let mut ok = true;
        for chunk in p {
            if everything[offset..offset + chunk.len()] != *chunk {
                ok = false;
                break;
            }
            offset += g[0].len();
        }
        if ok {
            return "YES".to_string();
        }
        at += 1;
    }
}

pub fn main_tested(name: &str) {
    let origin = file!();
    let input = read_input(origin, name);
    let t = input[0][0].parse::<i32>().unwrap();
    let mut results: Vec<String> = Vec::new();
    let mut offset = 1;
    for _ in 0..t {
        let g_length = input[offset][0].parse::<usize>().unwrap();
        let g: Vec<String> = input[offset + 1..offset + 1 + g_length]
            .iter()
            .map(|line| line[0].clone())
            .collect();
        let p_length = input[offset + 1 + g_length][0].parse::<usize>().unwrap();
        let p: Vec<String> = input[offset + 1 + g_length + 1..offset + 1 + g_length + 1 + p_length]
            .iter()
            .map(|line| line[0].clone())
            .collect();
        offset += 1 + g_length + 1 + p_length;
        results.push(grid_search(&g, &p));
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
    fn test05() {
        main_tested("05");
    }

    #[test]
    fn test07() {
        main_tested("07");
    }

    #[test]
    fn test08() {
        main_tested("08");
    }

    #[test]
    fn test09() {
        main_tested("09");
    }

    #[test]
    fn test15() {
        main_tested("15");
    }
}
