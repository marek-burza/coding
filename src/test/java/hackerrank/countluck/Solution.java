package hackerrank.countluck;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

/**
 * https://www.hackerrank.com/challenges/count-luck
 */
public class Solution {
    private static InputStream inOverride = null;
    private static PrintStream outOverride = null;

    public static void main(String[] args) {
        if (null == inOverride) {
            inOverride = System.in;
        }
        if (null == outOverride) {
            outOverride = System.out;
        }
        Scanner in = new Scanner(inOverride);
        int t = in.nextInt();
        for (int i = 0; i < t; i++) {
            int n = in.nextInt();
            int m = in.nextInt();
            char[][] forest = new char[n][m];
            for (int r = 0; r < n; r++) {
                String line = in.next();
                for (int c = 0; c < m; c++) {
                    forest[r][c] = line.charAt(c);
                }
            }
            int k = in.nextInt();
            outOverride.println(check(forest, k) ? "Impressed" : "Oops!");
        }
    }

    private static boolean check(char[][] forest, int k) {
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

    private final static int[][] DELTAS = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    private static List<At> lookAround(char[][] forest, At at) {
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

    private static class At {
        public final int row;
        public final int col;

        private At(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }
}
