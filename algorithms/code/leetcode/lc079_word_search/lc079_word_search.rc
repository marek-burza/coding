// https://leetcode.com/problems/word-search/
// #medium

pub struct Solution;

impl Solution {
    fn exist_inner(
        board: &Vec<Vec<char>>,
        word: &String,
        visited: &mut Vec<Vec<bool>>,
        index: usize,
        i: usize,
        j: usize,
    ) -> bool {
        // if index >= word.len() {
        //     return false;
        // }
        if visited[i][j] {
            return false;
        }
        if word.chars().nth(index).unwrap() == board[i][j] {
            if index == word.len() - 1 {
                true
            } else {
                visited[i][j] = true;
                if i + 1 < visited.len()
                    && Self::exist_inner(board, word, visited, index + 1, i + 1, j)
                {
                    return true;
                }
                if 0 < i && Self::exist_inner(board, word, visited, index + 1, i - 1, j) {
                    return true;
                }
                if j + 1 < visited[i].len()
                    && Self::exist_inner(board, word, visited, index + 1, i, j + 1)
                {
                    return true;
                }
                if 0 < j && Self::exist_inner(board, word, visited, index + 1, i, j - 1) {
                    return true;
                }
                visited[i][j] = false;
                false
            }
        } else {
            false
        }
    }

    pub fn exist(board: Vec<Vec<char>>, word: String) -> bool {
        if word.is_empty() {
            return false;
        }
        let mut visited = vec![vec![false; board[0].len()]; board.len()];
        for i in 0..board.len() {
            for j in 0..board[0].len() {
                if Self::exist_inner(&board, &word, &mut visited, 0, i, j) {
                    return true;
                }
            }
        }
        false
    }
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_example() {
        let board = vec![
            vec!['A', 'B', 'C', 'E'],
            vec!['S', 'F', 'C', 'S'],
            vec!['A', 'D', 'E', 'E'],
        ];
        assert!(Solution::exist(board, "ABCCED".to_string()));
    }

    #[test]
    fn test_other() {
        let board = vec![vec!['a', 'b'], vec!['c', 'd']];
        assert!(Solution::exist(board, "acdb".to_string()));
    }

    #[test]
    fn test_a_and_ab() {
        let board = vec![vec!['a']];
        assert!(!Solution::exist(board, "ab".to_string()));
    }

    #[test]
    fn test_a_and_a() {
        let board = vec![vec!['a']];
        assert!(Solution::exist(board, "a".to_string()));
    }

    #[test]
    fn test_aa_and_aaa() {
        let board = vec![vec!['a', 'a']];
        assert!(!Solution::exist(board, "aaa".to_string()));
    }

    #[test]
    fn test_nothing() {
        let board = vec![vec!['a']];
        assert!(!Solution::exist(board, "".to_string()));
    }
}
