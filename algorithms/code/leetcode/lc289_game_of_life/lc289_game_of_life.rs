// https://leetcode.com/problems/game-of-life/
// #medium

pub struct Solution;

impl Solution {
    fn count_alive(board: &mut [&mut [i32]], row: usize, col: usize) -> i32 {
        let mut count = 0;
        if row > 0 && col > 0 {
            count += board[row - 1][col - 1] & 1;
        }
        if row > 0 {
            count += board[row - 1][col] & 1;
        }
        if row > 0 && col < board[row].len() - 1 {
            count += board[row - 1][col + 1] & 1;
        }
        if col > 0 {
            count += board[row][col - 1] & 1;
        }
        if col < board[row].len() - 1 {
            count += board[row][col + 1] & 1;
        }
        if row < board.len() - 1 && col > 0 {
            count += board[row + 1][col - 1] & 1;
        }
        if row < board.len() - 1 {
            count += board[row + 1][col] & 1;
        }
        if row < board.len() - 1 && col < board[row].len() - 1 {
            count += board[row + 1][col + 1] & 1;
        }
        count
    }

    pub fn game_of_life(board: &mut [&mut [i32]]) {
        for row in 0..board.len() {
            for col in 0..board[row].len() {
                let count = Self::count_alive(board, row, col);
                let mask = if (board[row][col] & 1) == 1 {
                    if !(2..=3).contains(&count) { 0 } else { 2 }
                } else if count == 3 {
                    2
                } else {
                    0
                };
                board[row][col] |= mask;
            }
        }
        for row in board.iter_mut() {
            for row_col in row.iter_mut() {
                *row_col >>= 1;
            }
        }
    }
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_empty() {
        let mut board = vec![vec![]];
        let mut tmp = board
            .iter_mut()
            .map(|v| v.as_mut_slice())
            .collect::<Vec<_>>();
        let expected = vec![vec![]];
        Solution::game_of_life(tmp.as_mut_slice());
        assert_eq!(board, expected);
    }

    #[test]
    fn test_example_1() {
        let mut board = vec![vec![0, 1, 0], vec![0, 0, 1], vec![1, 1, 1], vec![0, 0, 0]];
        let mut tmp = board
            .iter_mut()
            .map(|v| v.as_mut_slice())
            .collect::<Vec<_>>();
        let expected = vec![vec![0, 0, 0], vec![1, 0, 1], vec![0, 1, 1], vec![0, 1, 0]];
        Solution::game_of_life(tmp.as_mut_slice());
        assert_eq!(board, expected);
    }

    #[test]
    fn test_example_2() {
        let mut board = vec![vec![1, 1], vec![1, 0]];
        let mut tmp = board
            .iter_mut()
            .map(|v| v.as_mut_slice())
            .collect::<Vec<_>>();
        let expected = vec![vec![1, 1], vec![1, 1]];
        Solution::game_of_life(tmp.as_mut_slice());
        assert_eq!(board, expected);
    }

    #[test]
    fn test_example_3() {
        let mut board = vec![
            vec![0, 0, 0, 0, 0, 0],
            vec![0, 0, 0, 0, 0, 0],
            vec![0, 0, 1, 1, 1, 0],
            vec![0, 1, 1, 1, 0, 0],
            vec![0, 0, 0, 0, 0, 0],
            vec![0, 0, 0, 0, 0, 0],
        ];
        let mut tmp = board
            .iter_mut()
            .map(|v| v.as_mut_slice())
            .collect::<Vec<_>>();
        let expected = vec![
            vec![0, 0, 0, 0, 0, 0],
            vec![0, 0, 0, 1, 0, 0],
            vec![0, 1, 0, 0, 1, 0],
            vec![0, 1, 0, 0, 1, 0],
            vec![0, 0, 1, 0, 0, 0],
            vec![0, 0, 0, 0, 0, 0],
        ];
        Solution::game_of_life(tmp.as_mut_slice());
        assert_eq!(board, expected);
    }
}
