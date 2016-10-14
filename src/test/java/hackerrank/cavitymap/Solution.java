package hackerrank.cavitymap;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

/**
 * https://www.hackerrank.com/challenges/cavity-map
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
        int n = in.nextInt();
        char[][] map = new char[n][n];
        for (int i = 0; i < n; i++) {
            String line = in.next();
            for (int j = 0; j < n; j++) {
                map[i][j] = line.charAt(j);
            }
        }
        map = markCavities(map);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                outOverride.print(map[i][j]);
            }
            outOverride.println();
        }
    }

    private static char[][] markCavities(char[][] map) {
        int[][] deltas = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};
        int n = map.length;
        for (int i = 1; i < n - 1; i++) {
            for (int j = 1; j < n - 1; j++) {
                int depth = (map[i][j] & 0x7FFF) - '0';
                boolean deeper = true;
                for (int[] delta : deltas) {
                    int other = (map[i + delta[0]][j + delta[1]] & 0x7FFF) - '0';
                    if (other >= depth) {
                        deeper = false;
                    }
                }
                if (deeper) {
                    map[i][j] |= 0x1000;
                }
            }
        }
        for (int i = 1; i < n - 1; i++) {
            for (int j = 1; j < n - 1; j++) {
                if ((map[i][j] & 0x1000) != 0) {
                    map[i][j] = 'X';
                }
            }
        }
        return map;
    }
}
