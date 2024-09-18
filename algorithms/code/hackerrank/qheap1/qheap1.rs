// https://www.hackerrank.com/challenges/qheap1

use crate::hackerrank::tester::{read_input, write_and_check_output};
use std::collections::VecDeque;

fn heap_child_left(index: usize) -> usize {
    (index << 1) + 1
}

fn heap_child_right(index: usize) -> usize {
    (index << 1) + 2
}

fn heap_parent(index: usize) -> usize {
    (index - 1) >> 1
}

fn heap_swap(heap: &mut VecDeque<i32>, index1: usize, index2: usize) {
    let exchange = *heap.get(index1).unwrap();
    *heap.get_mut(index1).unwrap() = *heap.get(index2).unwrap();
    *heap.get_mut(index2).unwrap() = exchange;
}

fn heap_ify_to_leaves(heap: &mut VecDeque<i32>, index: usize) {
    let size = heap.len();
    // Initialize smallest as root
    let mut smallest = index;
    // Left
    let left = heap_child_left(index);
    // Right
    let right = heap_child_right(index);
    // Check if left child is smallest
    if left < size && *heap.get(left).unwrap() < *heap.get(smallest).unwrap() {
        smallest = left;
    }
    // Check if right child is smallest
    if right < size && *heap.get(right).unwrap() < *heap.get(smallest).unwrap() {
        smallest = right;
    }
    // If smallest is not root
    if smallest != index {
        // Swap
        heap_swap(heap, smallest, index);
        // Recursively heapify the affected sub-tree
        heap_ify_to_leaves(heap, smallest);
    }
}

fn heap_ify_to_root(heap: &mut VecDeque<i32>, index: usize) {
    let mut current = index;
    while current > 0 && *heap.get(heap_parent(current)).unwrap() > *heap.get(current).unwrap() {
        heap_swap(heap, current, heap_parent(current));
        current = heap_parent(current);
    }
}

pub fn heap_build(heap: &mut VecDeque<i32>) {
    let size = heap.len();
    let mut index = (size / 2) - 1;
    loop {
        heap_ify_to_leaves(heap, index);
        if index == 0 {
            break;
        }
        index -= 1;
    }
}

pub fn heap_insert(heap: &mut VecDeque<i32>, value: i32) {
    heap.push_back(value);
    let index = heap.len() - 1;
    heap_ify_to_root(heap, index);
}

pub fn heap_delete_index(heap: &mut VecDeque<i32>, index: usize) {
    let size = heap.len();
    heap_swap(heap, index, size - 1);
    heap.pop_back(); // Can't swap on a popped value so first pop
    if index == size - 1 {
        return;
    }
    if index == 0 || *heap.get(heap_parent(index)).unwrap() < *heap.get(index).unwrap() {
        heap_ify_to_leaves(heap, index);
    } else {
        heap_ify_to_root(heap, index);
    }
}

pub fn heap_delete(heap: &mut VecDeque<i32>, value: i32) {
    if let Some(at) = heap_search(heap, value) {
        heap_delete_index(heap, at);
    }
}

pub fn heap_search(heap: &VecDeque<i32>, value: i32) -> Option<usize> {
    for (index, num) in heap.iter().enumerate() {
        if *num == value {
            return Some(index);
        }
    }
    None
    // Nodes at any level are unsorted
    // so stopping on first larger item might be premature
}

pub fn heap_root(heap: &VecDeque<i32>) -> i32 {
    *heap.front().unwrap()
}

pub fn main_tested(name: &str) {
    let origin = file!();
    let mut input = read_input(origin, name);
    let q = input.next().unwrap().trim().parse::<i32>().unwrap();
    let mut results: Vec<String> = Vec::new();
    let mut heap: VecDeque<i32> = VecDeque::new();
    for _ in 0..q {
        let line: Vec<String> = input
            .next()
            .unwrap()
            .split(' ')
            .map(|s| s.to_string())
            .collect();
        let operation = line[0].trim().parse::<i32>().unwrap();
        match operation {
            1 => {
                let value = line[1].trim().parse::<i32>().unwrap();
                heap_insert(&mut heap, value);
            }
            2 => {
                let value = line[1].trim().parse::<i32>().unwrap();
                heap_delete(&mut heap, value);
            }
            _ => {
                let result = heap_root(&heap);
                results.push(result.to_string());
            }
        }
    }
    write_and_check_output(origin, name, &results);
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_search_for_absent() {
        let mut heap: VecDeque<i32> = VecDeque::from([6, 3, 0, 5]);
        heap_build(&mut heap);
        assert_eq!(heap_search(&heap, -1), None);
    }

    #[test]
    fn test_delete_swaps_on_same_level() {
        let mut heap: VecDeque<i32> = VecDeque::from([0, 10, 8, 13, 14, 9]);
        heap_delete_index(&mut heap, 4);
        assert_ne!(*heap.back().unwrap(), 9);
    }

    #[test]
    fn test_example() {
        main_tested("example");
    }

    #[test]
    fn test_00() {
        main_tested("00");
    }

    #[test]
    fn test_01() {
        main_tested("01");
    }

    #[test]
    fn test_02() {
        main_tested("02");
    }

    #[test]
    fn test_08() {
        main_tested("08");
    }
}
