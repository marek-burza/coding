// https://www.hackerrank.com/challenges/cut-the-tree

use crate::hackerrank::tester::{read_input, write_and_check_output};
use std::collections::HashSet;

fn build_graph(n: usize, edges: &[Vec<i32>]) -> Vec<Vec<usize>> {
    let mut graph = vec![Vec::new(); n];
    for edge in edges {
        let v1 = (edge[0] - 1) as usize;
        let v2 = (edge[1] - 1) as usize;
        graph[v1].push(v2);
        graph[v2].push(v1);
    }
    graph
}

fn maximum_edge(
    data: &[i32],
    graph: &Vec<Vec<usize>>,
    v: usize,
    total: i32,
    minimum: &mut Option<i32>,
    seen: &mut HashSet<usize>,
) -> i32 {
    if seen.contains(&v) {
        return 0;
    }
    let mut sum = data[v];
    seen.insert(v);
    let adjacent = &graph[v];
    for other in adjacent {
        let partial = maximum_edge(data, graph, *other, total, minimum, seen);
        let candidate = (total - 2 * partial).abs();
        if minimum.is_none() || candidate < minimum.unwrap_or(i32::MAX) {
            *minimum = Some(candidate);
        }
        sum += partial;
    }
    sum
}

pub fn cut_the_tree(data: &[i32], edges: &[Vec<i32>]) -> i32 {
    let graph = build_graph(data.len(), edges);
    let total = data.iter().sum::<i32>();
    let mut minimum: Option<i32> = None;
    let mut seen = HashSet::new();
    maximum_edge(data, &graph, 0, total, &mut minimum, &mut seen);
    minimum.unwrap_or(0)
}

pub fn main_tested(name: &str) {
    let origin = file!();
    let input = read_input(origin, name);
    let mut results: Vec<String> = Vec::new();
    let data: Vec<i32> = input[1]
        .iter()
        .map(|s| s.to_string().parse::<i32>().unwrap())
        .collect();
    let edges: Vec<Vec<i32>> = input[2..]
        .iter()
        .map(|inner| inner.iter().map(|s| s.parse::<i32>().unwrap()).collect())
        .collect();
    results.push(cut_the_tree(&data, &edges).to_string());
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
