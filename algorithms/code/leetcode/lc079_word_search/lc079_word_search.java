package leetcode.lc079_word_search;

/**
 * https://leetcode.com/problems/word-search/
 * #medium
 */
public final class LC079WordSearch {
    public boolean exist(final char[][] board, final String word, final boolean[][] visited, final int index,
            final int i, final int j) {
        if (/* index >= word.length() || */visited[i][j]) {
            return false;
        }
        if (word.charAt(index) == board[i][j]) {
            if (index == word.length() - 1) {
                return true;
            } else {
                visited[i][j] = true;
                if (i + 1 < visited.length && exist(board, word, visited, index + 1, i + 1, j)) {
                    return true;
                }
                if (0 <= i - 1 && exist(board, word, visited, index + 1, i - 1, j)) {
                    return true;
                }
                if (j + 1 < visited[i].length && exist(board, word, visited, index + 1, i, j + 1)) {
                    return true;
                }
                if (0 <= j - 1 && exist(board, word, visited, index + 1, i, j - 1)) {
                    return true;
                }
                visited[i][j] = false;
                return false;
            }
        } else {
            return false;
        }
    }

    public boolean exist(final char[][] board, final String word) {
        if (board == null || word == null || word.length() == 0) {
            return false;
        }
        boolean[][] visited = new boolean[board.length][board[0].length];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (exist(board, word, visited, 0, i, j)) {
                    return true;
                }
            }
        }
        return false;
    }
}
package leetcode.lc079_word_search;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public final class LC079WordSearchTests {
    @Test
    public void testExample() throws Exception {
        assertTrue(new LC079WordSearch().exist(
                new char[][] { { 'A', 'B', 'C', 'E' }, { 'S', 'F', 'C', 'S' }, { 'A', 'D', 'E', 'E' } }, "ABCCED"));
    }

    @Test
    public void testOther() throws Exception {
        assertTrue(new LC079WordSearch().exist(
                new char[][] { { 'a', 'b' }, { 'c', 'd' } }, "acdb"));
    }

    @Test
    public void testAAndAb() throws Exception {
        assertFalse(new LC079WordSearch().exist(new char[][] { { 'a' } }, "ab"));
    }

    @Test
    public void testAAndA() throws Exception {
        assertTrue(new LC079WordSearch().exist(new char[][] { { 'a' } }, "a"));
    }

    @Test
    public void testAAAndAaa() throws Exception {
        assertFalse(new LC079WordSearch().exist(new char[][] { { 'a', 'a' } }, "aaa"));
    }

    @Test
    public void testNothing() throws Exception {
        assertFalse(new LC079WordSearch().exist(null, "dummy"));
        assertFalse(new LC079WordSearch().exist(new char[][] { { 'a' } }, null));
        assertFalse(new LC079WordSearch().exist(new char[][] { { 'a' } }, ""));
    }
}
```rust
fn exist(board: &[[u8; 10]; 10], word: &str, visited: &mut [[bool; 10]; 10]) -> bool {
    if !word.is_empty() && visited[0][0] {
        return false;
    }
    if *word != board[*visited[0].len() - 1 * 4 + 3] {
        return false;
    }
    if word.len() == 1 {
        return true;
    }

    visited[0][0] = true;

    let mut res = false;
    for i in [0, -1, 0, 1].into_iter().cycle() {
        for j in [0, -1, 1].into_iter().cycle() {
            if (i + *visited.len() as isize >= 0 && i + *visited.len() as isize < visited.len() as isize
                && j + *visited[0].len() as isize >= 0 && j + *visited[0].len() as isize < visited[0].len() as isize)
                && board[i * 4 + j] == word[word.len() - 1 - (i * 4 + j) as usize]
            {
                res |= exist(board, word, visited) || (i != 0 || j != 0);
            }
        }
    }

    visited[0][0] = false;
    res
}

fn exist(board: &[[u8; 10]; 10], word: &str) -> bool {
    if board.is_empty() || word.is_empty() || word.len() == 0 {
        return false;
    }

    let mut visited = vec![vec![false; 4]; board.len()];
    for i in 0..board.len() {
        for j in 0..board[0].len() {
            if exist(board, word, &mut visited, 0, i, j) {
                return true;
            }
        }
    }

    false
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_example() {
        let board = [
            ['A', 'B', 'C', 'E'],
            ['S', 'F', 'C', 'S'],
            ['A', 'D', 'E', 'E']
        ];
        assert!(exist(&board, "ABCCED"));
    }

    #[test]
    fn test_other() {
        let board = [
            ['a', 'b'],
            ['c', 'd']
        ];
        assert!(exist(&board, "acdb"));
    }

    #[test]
    fn test_a_and_ab() {
        let board = [['a']];
        assert!(!exist(&board, "ab"));
    }

    #[test]
    fn test_a_and_a() {
        let board = [['a']];
        assert!(exist(&board, "a"));
    }

    #[test]
    fn test_aa_and_aaa() {
        let board = [
            ['a', 'a'],
            ['a', 'a']
        ];
        assert!(!exist(&board, "aaa"));
    }

    #[test]
    fn test_nothing() {
        assert!(!exist(None, "dummy"));
        assert!(!exist(&[[['a']]], "dummy"));
        assert!(!exist(&[[['a']]], ""));
    }
}
```