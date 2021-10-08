package hackerrank.circular_array_rotation;

import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * https://www.hackerrank.com/challenges/circular-array-rotation
 */
public final class Solution {
    private Solution() {
    }

    private static Reader inOverride = null;
    private static Writer outOverride = null;

    public static int[][] prepare(final Reader override) throws IOException {
        Reader reader = override == null ? new InputStreamReader(System.in, "UTF-8") : override;
        BufferedReader bufferedReader = new BufferedReader(reader);
        List<int[]> prepared = new LinkedList<>();
        for (String line = bufferedReader.readLine(); line != null; line = bufferedReader.readLine()) {
            int[] entry = Arrays.stream(line.strip().split(" ")).mapToInt(Integer::parseInt).toArray();
            prepared.add(entry);
        }
        bufferedReader.close();
        return prepared.toArray(new int[0][]);
    }

    public static void main(final String[] args) {
        if (null == inOverride) {
            inOverride = new InputStreamReader(System.in, StandardCharsets.UTF_8);
        }
        if (null == outOverride) {
            outOverride = new OutputStreamWriter(System.out, StandardCharsets.UTF_8);
        }
        try {
            int[][] input = prepare(inOverride);
            int n = input[0][0];
            int k = input[0][1];
            int q = input[0][2];
            int[] array = input[1];
            for (int i = 0; i < q; i++) {
                outOverride.write(String.valueOf(SolutionCore.query(n, k, array, input[2 + i][0])));
                outOverride.write('\n');
            }
            outOverride.flush();
            outOverride.close();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }
}
