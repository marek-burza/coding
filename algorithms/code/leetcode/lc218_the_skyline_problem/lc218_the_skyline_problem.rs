// https://leetcode.com/problems/the-skyline-problem/
// #hard

use std::cmp::Ordering;
use std::collections::{BinaryHeap, HashMap};

#[derive(Clone, Debug)]
struct Building {
    left: i32,
    right: i32,
    height: i32,
}

impl PartialEq for Building {
    fn eq(&self, other: &Self) -> bool {
        self.left == other.left && self.right == other.right && self.height == other.height
        // self.height == other.height
    }
}

impl Eq for Building {}

impl PartialOrd for Building {
    fn partial_cmp(&self, other: &Self) -> Option<Ordering> {
        Some(self.cmp(other))
    }
}

impl Ord for Building {
    fn cmp(&self, other: &Self) -> Ordering {
        self.height.cmp(&other.height)
        // other.height.cmp(&self.height)
    }
}

pub struct Solution;

impl Solution {
    pub fn get_skyline(buildings: Vec<Vec<i32>>) -> Vec<(i32, i32)> {
        let mut skyline = Vec::new();
        if buildings.is_empty() {
            return skyline;
        }
        // Build list of spots
        let mut spots: HashMap<i32, Vec<Building>> = HashMap::new();
        for building in buildings {
            if building[0] == building[1] {
                continue;
            }
            for spot in building[..2].iter() {
                if !spots.contains_key(spot) {
                    spots.insert(*spot, vec![]);
                }
                spots.get_mut(spot).unwrap().push(Building {
                    left: building[0],
                    right: building[1],
                    height: building[2],
                });
            }
        }
        let mut sorted_spots: Vec<i32> = spots.keys().cloned().collect();
        sorted_spots.sort();
        for spot in &sorted_spots {
            println!("{}: {:?}", spot, spots.get(spot));
        }
        println!("Sorted spots: {:?}", sorted_spots);
        // Prepare view
        let ground = Building {
            left: 0,
            right: *sorted_spots.last().unwrap(),
            height: 0,
        };
        let mut view: BinaryHeap<Building> = BinaryHeap::new();
        view.push(ground);
        // Check all spots and build skyline
        let mut current = 0;
        for at in sorted_spots {
            println!("At: {}", at);
            for building in spots.get(&at).unwrap() {
                if at == building.left {
                    // Building entering the view
                    view.push(building.clone());
                } else {
                    // Building leaving the view
                    let mut temporary_buildings: Vec<Building> = Vec::new();
                    while let Some(temporary_building) = view.pop() {
                        if temporary_building != *building {
                            temporary_buildings.push(temporary_building);
                        }
                    }
                    for temporary_building in temporary_buildings {
                        view.push(temporary_building);
                    }
                }
            }
            let following = view.peek().unwrap().height;
            if current != following {
                let point = (at, following);
                skyline.push(point);
            }
            current = following;
        }
        skyline
    }
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_example_1() {
        let buildings = vec![
            vec![2, 9, 10],
            vec![3, 7, 15],
            vec![5, 12, 12],
            vec![15, 20, 10],
            vec![19, 24, 8],
        ];
        let expected = vec![
            (2, 10),
            (3, 15),
            (7, 12),
            (12, 0),
            (15, 10),
            (20, 8),
            (24, 0),
        ];
        assert_eq!(Solution::get_skyline(buildings), expected);
    }

    #[test]
    fn test_example_2() {
        let buildings = vec![vec![0, 2, 3], vec![2, 5, 3]];
        let expected = vec![(0, 3), (5, 0)];
        assert_eq!(Solution::get_skyline(buildings), expected);
    }

    #[test]
    fn test_coverage_gaps() {
        let buildings = vec![vec![2, 5, 3], vec![0, 2, 3], vec![0, 0, 10]];
        let expected = vec![(0, 3), (5, 0)];
        assert_eq!(Solution::get_skyline(buildings), expected);
        assert_eq!(Solution::get_skyline(vec![]).len(), 0);
    }
}
