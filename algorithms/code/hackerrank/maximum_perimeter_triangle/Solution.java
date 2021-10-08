package hackerrank.maximum_perimeter_triangle;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * https://www.hackerrank.com/challenges/maximum-perimeter-triangle
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
        List<Integer> sticks = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            sticks.add(in.nextInt());
        }
        int[] picked = SolutionCore.pick(sticks);
        try {
            if (null == picked) {
                outOverride.write("-1");
            } else {
                outOverride.write(String.valueOf(picked[0]));
                outOverride.write(" ");
                outOverride.write(String.valueOf(picked[1]));
                outOverride.write(" ");
                outOverride.write(String.valueOf(picked[2]));
            }
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
