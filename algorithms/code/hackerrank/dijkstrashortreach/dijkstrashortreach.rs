// https://www.hackerrank.com/challenges/dijkstrashortreach
// #linking

use crate::hackerrank::tester::{read_input, write_and_check_output};
use std::cmp::Ordering;
use std::collections::BinaryHeap;

#[derive(Debug, Clone)]
struct Edge {
    vertex: usize,
    weight: i32,
}

#[derive(Debug, Clone, Eq, PartialEq)]
struct Vertex {
    index: usize,
    distance: i32,
}

impl PartialOrd for Vertex {
    fn partial_cmp(&self, other: &Self) -> Option<Ordering> {
        Some(self.cmp(other))
    }
}

impl Ord for Vertex {
    fn cmp(&self, other: &Self) -> Ordering {
        // Reverse the order to make BinaryHeap act as a min-heap
        other.distance.cmp(&self.distance)
    }
}

pub fn shortest_reach(n: usize, edges: &[Vec<i32>], s: usize) -> Vec<i32> {
    let mut adjacency: Vec<Vec<Edge>> = vec![Vec::new(); n + 1];
    for edge in edges {
        let (origin, vertex, weight) = (edge[0] as usize, edge[1] as usize, edge[2]);
        adjacency[origin].push(Edge { vertex, weight });
        adjacency[vertex].push(Edge {
            vertex: origin,
            weight,
        });
    }
    let mut distances = vec![-1; n + 1];
    distances[s] = 0;
    let mut unvisited: BinaryHeap<Vertex> = BinaryHeap::new();
    unvisited.push(Vertex {
        index: s,
        distance: 0,
    });
    while let Some(vertex) = unvisited.pop() {
        for edge_obj in &adjacency[vertex.index] {
            let other = edge_obj.vertex;
            let candidate = vertex.distance + edge_obj.weight;
            if distances[other] == -1 || candidate < distances[other] {
                distances[other] = candidate;
                unvisited.push(Vertex {
                    index: other,
                    distance: candidate,
                });
            }
        }
    }
    distances
        .iter()
        .enumerate()
        .filter(|&(i, _)| i != 0 && i != s)
        .map(|(_, &distance)| distance)
        .collect()
}

pub fn main_tested(name: &str) {
    let origin = file!();
    let input = read_input(origin, name);
    let mut results: Vec<String> = Vec::new();
    let tests = input[0][0].parse::<usize>().unwrap();
    let mut offset = 1;
    for _ in 0..tests {
        let n = input[offset][0].parse::<usize>().unwrap();
        let m = input[offset][1].parse::<usize>().unwrap();
        let edges: Vec<Vec<i32>> = input[(offset + 1)..(offset + 1 + m)]
            .iter()
            .map(|inner| inner.iter().map(|s| s.parse::<i32>().unwrap()).collect())
            .collect();
        let s = input[offset + 1 + m][0].parse::<usize>().unwrap();
        offset += 1 + m + 1;
        let result = shortest_reach(n, &edges, s);
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
    fn test00() {
        main_tested("00");
    }

    #[test]
    fn test01() {
        main_tested("01");
    }

    #[test]
    fn test03() {
        main_tested("03");
    }

    #[test]
    fn test04() {
        main_tested("04");
    }
}
