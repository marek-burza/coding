package leetcode;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * https://leetcode.com/problems/word-search/
 * #medium
 */
public class LC079WordSearch {
    public final class Solution {
        public boolean exist(char[][] board, String word, boolean[][] visited, int index, int i, int j) {
            if (index >= word.length() || visited[i][j]) {
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

        public boolean exist(char[][] board, String word) {
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

    @Test
    public void test_a__ab() throws Exception {
        assertEquals(false, new Solution().exist(new char[][]{{'a'}}, "ab"));
    }

    @Test
    public void test_a__a() throws Exception {
        assertEquals(true, new Solution().exist(new char[][]{{'a'}}, "a"));
    }

    @Test
    public void test_a_a__aaa() throws Exception {
        assertEquals(false, new Solution().exist(new char[][]{{'a', 'a'}}, "aaa"));
    }
}
