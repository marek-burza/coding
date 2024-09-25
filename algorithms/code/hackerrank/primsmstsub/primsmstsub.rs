// https://www.hackerrank.com/challenges/primsmstsub
// #linking

use crate::hackerrank::tester::{read_input, write_and_check_output};
use std::cmp::Ordering;
use std::collections::{BinaryHeap, HashSet};

#[derive(Debug, Clone, Eq, PartialEq)]
struct Edge {
    origin: usize,
    vertex: usize,
    weight: i32,
}

impl PartialOrd for Edge {
    fn partial_cmp(&self, other: &Self) -> Option<Ordering> {
        Some(self.cmp(other))
    }
}

impl Ord for Edge {
    fn cmp(&self, other: &Self) -> Ordering {
        // Reverse the order to make BinaryHeap act as a min-heap
        other.weight.cmp(&self.weight)
    }
}

pub fn prims(n: usize, edges: &[Vec<i32>], start: usize) -> i32 {
    let mut adjacency: Vec<Vec<Edge>> = vec![Vec::new(); n + 1];
    for edge in edges {
        let (origin, vertex, weight) = (edge[0] as usize, edge[1] as usize, edge[2]);
        adjacency[origin].push(Edge {
            origin,
            vertex,
            weight,
        });
        adjacency[vertex].push(Edge {
            origin: vertex,
            vertex: origin,
            weight,
        });
    }
    let mut connected: HashSet<usize> = HashSet::new();
    let mut queue: BinaryHeap<&Edge> = BinaryHeap::new();
    let mut total = 0;
    while connected.len() < n {
        let mut vertex = start;
        while let Some(edge_obj) = queue.pop() {
            if !connected.contains(&edge_obj.vertex) {
                vertex = edge_obj.vertex;
                total += edge_obj.weight;
                break;
            }
        }
        // if vertex == start && connected.contains(&start) {
        //     break;
        // }
        connected.insert(vertex);
        for edge_obj in &adjacency[vertex] {
            queue.push(&edge_obj);
        }
    }
    total
}

pub fn main_tested(name: &str) {
    let origin = file!();
    let input = read_input(origin, name);
    let mut results: Vec<String> = Vec::new();
    let n = input[0][0].parse::<usize>().unwrap();
    let m = input[0][1].parse::<usize>().unwrap();
    let edges: Vec<Vec<i32>> = input[1..(1 + m)]
        .iter()
        .map(|inner| inner.iter().map(|s| s.parse::<i32>().unwrap()).collect())
        .collect();
    let start = input[1 + m][0].parse::<usize>().unwrap();
    let result = prims(n, &edges, start);
    results.push(result.to_string());
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
    fn test06() {
        main_tested("06");
    }
}
