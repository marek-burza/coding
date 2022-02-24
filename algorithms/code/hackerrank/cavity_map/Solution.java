package hackerrank.cavity_map;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 * https://www.hackerrank.com/challenges/cavity-map
 */
public final class Solution {
    private Solution() {
    }

    private static Reader inOverride = null;
    private static Writer outOverride = null;

    public static void main(final String[] args) {
        if (null == inOverride) {
            inOverride = new InputStreamReader(System.in, StandardCharsets.UTF_8);
        }
        if (null == outOverride) {
            outOverride = new OutputStreamWriter(System.out, StandardCharsets.UTF_8);
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
        map = SolutionCore.markCavities(map);
        try {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    outOverride.write(String.valueOf(map[i][j]));
                }
                outOverride.write('\n');
            }
            outOverride.flush();
            outOverride.close();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        } finally {
            in.close();
        }
    }
}
