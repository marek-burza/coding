package hackerrank.count_luck;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * https://www.hackerrank.com/challenges/count-luck
 */
public final class SolutionCore {
    private SolutionCore() {
    }

    protected static boolean check(final char[][] forest, final int k) {
        Queue<At> queue = new LinkedList<>();
        Queue<Integer> counts = new LinkedList<>();
        counts.add(0);
        for (int r = 0; r < forest.length; r++) {
            for (int c = 0; c < forest[r].length; c++) {
                if (forest[r][c] == 'M') {
                    queue.add(new At(r, c));
                }
            }
        }
        while (!queue.isEmpty()) {
            At at = queue.poll();
            int count = counts.poll();
            if ('*' == forest[at.row][at.col]) {
                return count == k;
            }
            forest[at.row][at.col] = 'X';
            List<At> ways = lookAround(forest, at);
            for (At way : ways) {
                queue.add(way);
                counts.add(count + (ways.size() > 1 ? 1 : 0));
            }
        }
        return false;
    }

    private static final int[][] DELTAS = { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 } };

    private static List<At> lookAround(final char[][] forest, final At at) {
        List<At> ways = new LinkedList<>();
        for (int[] delta : DELTAS) {
            int dr = at.row + delta[0];
            int dc = at.col + delta[1];
            if (dr < 0 || forest.length <= dr) {
                continue;
            }
            if (dc < 0 || forest[dr].length <= dc) {
                continue;
            }
            if ('X' == forest[dr][dc]) {
                continue;
            }
            ways.add(new At(dr, dc));
        }
        return ways;
    }

    private static final class At {
        public final int row;
        public final int col;

        private At(final int rowValue, final int colValue) {
            row = rowValue;
            col = colValue;
        }
    }
}
