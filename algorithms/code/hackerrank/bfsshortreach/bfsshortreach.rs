// https://www.hackerrank.com/challenges/bfsshortreach

use crate::hackerrank::tester::{read_input, write_and_check_output};

struct Entry {
    vertex: i32,
    distance: i32,
}

pub fn bfs(n: i32, _m: i32, edges: &[Vec<i32>], s: i32) -> Vec<i32> {
    let mut adjacency = vec![vec![false; n as usize]; n as usize];
    for edge in edges {
        adjacency[(edge[0] - 1) as usize][(edge[1] - 1) as usize] = true;
        adjacency[(edge[1] - 1) as usize][(edge[0] - 1) as usize] = true;
    }
    let mut queue = Vec::new();
    queue.push(Entry {
        vertex: s - 1,
        distance: 0,
    });
    let mut distances = vec![-1; n as usize];
    while let Some(item) = queue.pop() {
        if distances[item.vertex as usize] == -1 {
            distances[item.vertex as usize] = item.distance;
            for i in 0..n as usize {
                if adjacency[item.vertex as usize][i] {
                    queue.push(Entry {
                        vertex: i as i32,
                        distance: item.distance + 6,
                    });
                }
            }
        }
    }
    let mut result = Vec::new();
    for i in 0..n {
        if i != (s - 1) {
            result.push(distances[i as usize]);
        }
    }
    result
}

pub fn main_tested(name: &str) {
    let origin = file!();
    let input = read_input(origin, name);
    let tests = input[0][0].parse::<i32>().unwrap();
    let mut results: Vec<String> = Vec::new();
    let mut offset = 1;
    for _ in 0..tests {
        let n = input[offset][0].parse::<i32>().unwrap();
        let m = input[offset][1].parse::<i32>().unwrap();
        let edges: Vec<Vec<i32>> = input[(offset + 1)..(offset + 1 + m as usize)]
            .iter()
            .map(|inner| inner.iter().map(|s| s.parse::<i32>().unwrap()).collect())
            .collect();
        let s = input[offset + 1 + m as usize][0].parse::<i32>().unwrap();
        offset += 1 + m as usize + 1;
        let result = bfs(n, m, &edges, s);
        results.push(
            result
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
}
