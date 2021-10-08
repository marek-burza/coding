package hackerrank.connected_cell_in_a_grid;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import hackerrank.connected_cell_in_a_grid.SolutionCore.IslandHunter;
import hackerrank.connected_cell_in_a_grid.SolutionCore.Pair;

/**
 * https://www.hackerrank.com/challenges/connected-cell-in-a-grid
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
        int m = in.nextInt();
        List<Pair<Integer, Integer>> land = new LinkedList<>();
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < m; col++) {
                if (1 == in.nextInt()) {
                    land.add(new Pair<>(col, row));
                }
            }
        }
        try {
            outOverride.write(String.valueOf(new IslandHunter(land).measureBiggestIsland()));
            outOverride.write('\n');
            outOverride.flush();
            outOverride.close();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        } finally {
            in.close();
        }
    }
}
