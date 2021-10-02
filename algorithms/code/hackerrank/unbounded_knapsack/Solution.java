package hackerrank.unbounded_knapsack;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

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

    public static void main(final String[] args) throws IOException {
        int[][] input = prepare(inOverride);
        Writer outUsed = outOverride != null ? outOverride
                : new OutputStreamWriter(new FileOutputStream(System.getenv("OUTPUT_PATH")), "UTF-8");
        BufferedWriter bufferedWriter = new BufferedWriter(outUsed);
        int t = input[0][0];
        for (int i = 0; i < t; i++) {
            int k = input[1 + i * 2][1];
            List<Integer> arr = Arrays.stream(input[2 + i * 2]).boxed().collect(Collectors.toList());
            int result = SolutionCore.unboundedKnapsack(k, arr);
            bufferedWriter.write(String.valueOf(result));
            bufferedWriter.newLine();
        }
        bufferedWriter.close();
    }
}
