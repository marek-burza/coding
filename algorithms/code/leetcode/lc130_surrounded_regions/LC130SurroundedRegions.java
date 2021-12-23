package leetcode.lc130_surrounded_regions;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * https://leetcode.com/problems/surrounded-regions/
 * #medium
 */
public final class LC130SurroundedRegions {
    private long key(final int i, final int j) {
        return ((long) i << 32) | j;
    }

    private int i(final long key) {
        return (int) (key >> 32);
    }

    private int j(final long key) {
        return (int) key;
    }

    private void enqueue(final int i, final int j, final List<Long> check, final Set<Long> visited) {
        if (i < 0 || j < 0) {
            return;
        }
        long key = key(i, j);
        if (!visited.contains(key)) {
            check.add(key);
            visited.add(key);
        }
    }

    public void solve(final char[][] board) {
        if (board == null || board.length == 0) {
            return;
        }
        for (char[] row : board) {
            if (row == null || row.length == 0) {
                return;
            }
        }
        List<Long> check = new ArrayList<>();
        Set<Long> visited = new HashSet<>();
        for (int i = 0; i < board.length; i++) {
            enqueue(i, 0, check, visited);
            enqueue(i, board[i].length - 1, check, visited);
        }
        for (int j = 1; j < board[0].length - 1; j++) {
            enqueue(0, j, check, visited);
        }
        for (int j = 1; j < board[board.length - 1].length - 1; j++) {
            enqueue(board.length - 1, j, check, visited);
        }
        while (check.size() > 0) {
            long key = check.get(0);
            check.remove(0);
            int i = i(key);
            int j = j(key);
            if (board.length <= i || board[i].length <= j) {
                continue;
            }
            if (board[i][j] == 'O') {
                board[i][j] = 'V';
                enqueue(i + 1, j, check, visited);
                enqueue(i - 1, j, check, visited);
                enqueue(i, j + 1, check, visited);
                enqueue(i, j - 1, check, visited);
            }
        }
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == 'O') {
                    board[i][j] = 'X';
                }
                if (board[i][j] == 'V') {
                    board[i][j] = 'O';
                }
            }
        }
    }
}
