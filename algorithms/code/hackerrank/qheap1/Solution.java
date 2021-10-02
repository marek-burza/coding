package hackerrank.qheap1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

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
        int n = input[0][0];
        List<Integer> heap = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int[] arguments = input[1 + i];
            switch (arguments[0]) {
                case 1:
                    SolutionCore.heapInsert(heap, arguments[1]);
                    break;
                case 2:
                    SolutionCore.heapDelete(heap, arguments[1]);
                    break;
                case 3:
                    bufferedWriter.write(String.valueOf(SolutionCore.heapRoot(heap)));
                    bufferedWriter.newLine();
                    break;
                default:
                    break;
            }
        }
        bufferedWriter.close();
    }
}
