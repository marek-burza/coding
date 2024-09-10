// https://web.archive.org/web/20170803130133/http://qa.geeksforgeeks.org/6310/find-the-nearest-locker-in-the-city

use std::collections::VecDeque;

#[derive(Clone, Copy)]
pub struct Coordinates {
    x: usize,
    y: usize,
}

struct Deltas {
    x: i64,
    y: i64,
}

const DIRECTIONS: [Deltas; 4] = [
    Deltas { x: 0, y: 1 },
    Deltas { x: 0, y: -1 },
    Deltas { x: 1, y: 0 },
    Deltas { x: -1, y: 0 },
];

fn mark_lockers(city: &mut [Vec<u64>], lockers: &[Coordinates]) {
    for locker in lockers.iter() {
        city[locker.x][locker.y] = 0;
    }
}

fn init_bfs(lockers: &[Coordinates], queue: &mut VecDeque<Coordinates>) {
    for locker in lockers.iter() {
        queue.push_back(*locker);
    }
}

fn progress_bfs(
    sizex: usize,
    sizey: usize,
    city: &mut [Vec<u64>],
    queue: &mut VecDeque<Coordinates>,
) {
    let position = queue.pop_front().unwrap();
    let distance = city[position.x][position.y] + 1;
    for direction in DIRECTIONS.iter() {
        let x = position.x as i64 + direction.x;
        let y = position.y as i64 + direction.y;
        if 0 <= x && x < sizex as i64 && 0 <= y && y < sizey as i64 {
            let x_valid = x as usize;
            let y_valid = y as usize;
            if distance < city[x_valid][y_valid] {
                city[x_valid][y_valid] = distance;
                queue.push_back(Coordinates {
                    x: x_valid,
                    y: y_valid,
                });
            }
        }
    }
}

pub fn locker_distances(sizex: usize, sizey: usize, lockers: &[Coordinates]) -> Vec<Vec<u64>> {
    let mut city: Vec<Vec<u64>> = vec![vec![u64::MAX; sizey]; sizex];
    mark_lockers(&mut city, lockers);
    let mut queue: VecDeque<Coordinates> = VecDeque::new();
    init_bfs(lockers, &mut queue);
    while !queue.is_empty() {
        progress_bfs(sizex, sizey, &mut city, &mut queue);
    }
    city
}

#[cfg(test)]
mod tests {
    use super::*;

    fn generic_test(result: &[Vec<u64>], expected: &[Vec<u64>]) {
        assert_eq!(result.len(), expected.len());
        for i in 0..expected.len() {
            assert_eq!(result[i].len(), expected[i].len());
            for j in 0..expected[i].len() {
                assert_eq!(result[i][j], expected[i][j]);
            }
        }
    }

    #[test]
    fn test_result_present() {
        let result = locker_distances(1, 1, &[]);
        assert!(!result.is_empty());
        assert_eq!(result[0][0], u64::MAX);
    }

    #[test]
    fn test_result_just_locker() {
        let lockers: Vec<Coordinates> = vec![Coordinates { x: 0, y: 0 }];
        let result = locker_distances(1, 1, &lockers);
        assert!(!result.is_empty());
        assert_eq!(result[0][0], 0);
    }

    #[test]
    fn test_example1() {
        let lockers: Vec<Coordinates> = vec![Coordinates { x: 0, y: 0 }];
        let result = locker_distances(3, 5, &lockers);
        let expected = vec![
            vec![0, 1, 2, 3, 4],
            vec![1, 2, 3, 4, 5],
            vec![2, 3, 4, 5, 6],
        ];
        generic_test(&result, &expected);
    }

    #[test]
    fn test_example2() {
        let lockers: Vec<Coordinates> =
            vec![Coordinates { x: 1, y: 2 }, Coordinates { x: 3, y: 6 }];
        let result = locker_distances(5, 7, &lockers);
        let expected = vec![
            vec![3, 2, 1, 2, 3, 4, 3],
            vec![2, 1, 0, 1, 2, 3, 2],
            vec![3, 2, 1, 2, 3, 2, 1],
            vec![4, 3, 2, 3, 2, 1, 0],
            vec![5, 4, 3, 4, 3, 2, 1],
        ];
        generic_test(&result, &expected);
    }
}
