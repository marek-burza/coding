package hackerrank.compare_the_triplets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * https://www.hackerrank.com/challenges/compare-the-triplets
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
            int[] a = input[0];
            int[] b = input[1];
            int[] result = SolutionCore.compareTriplets(a, b);
            outOverride.write(String.valueOf(result[0]));
            outOverride.write(" ");
            outOverride.write(String.valueOf(result[1]));
            outOverride.write('\n');
            outOverride.flush();
            outOverride.close();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }
}
