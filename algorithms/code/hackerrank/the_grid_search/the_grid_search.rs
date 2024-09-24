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
    let mut input = read_input(origin, name);
    let t = input.next().unwrap().trim().parse::<i32>().unwrap();
    let mut results: Vec<String> = Vec::new();
    for _ in 0..t {
        let first_header: Vec<String> = input
            .next()
            .unwrap()
            .split(' ')
            .map(|s| s.to_string())
            .collect();
        let mut g: Vec<String> = Vec::new();
        for _ in 0..first_header[0].trim().parse::<i32>().unwrap() {
            g.push(input.next().unwrap());
        }
        let second_header: Vec<String> = input
            .next()
            .unwrap()
            .split(' ')
            .map(|s| s.to_string())
            .collect();
        let mut p: Vec<String> = Vec::new();
        for _ in 0..second_header[0].trim().parse::<i32>().unwrap() {
            p.push(input.next().unwrap());
        }
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
