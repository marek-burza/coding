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
