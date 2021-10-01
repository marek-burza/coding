package hackerrank.unbounded_knapsack;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public final class Solution {
    private Solution() {
    }

    private static Reader inOverride = null;
    private static Writer outOverride = null;

    public static void main(final String[] args) throws IOException {
        Reader inUsed = inOverride != null ? inOverride : new InputStreamReader(System.in, "UTF-8");
        Writer outUsed = outOverride != null ? outOverride
                : new OutputStreamWriter(new FileOutputStream(System.getenv("OUTPUT_PATH")), "UTF-8");
        BufferedReader bufferedReader = new BufferedReader(inUsed);
        BufferedWriter bufferedWriter = new BufferedWriter(outUsed);
        String line0 = bufferedReader.readLine();
        int t = line0 == null ? 0 : Integer.parseInt(line0.trim());
        while (t-- > 0) {
            String line1 = bufferedReader.readLine();
            if (line1 == null) {
                break;
            }
            String[] firstMultipleInput = line1.replaceAll("\\s+$", "").split(" ");
            Integer.parseInt(firstMultipleInput[0]); // n
            int k = Integer.parseInt(firstMultipleInput[1]);
            String line2 = bufferedReader.readLine();
            if (line2 == null) {
                break;
            }
            List<Integer> arr = Stream.of(line2.replaceAll("\\s+$", "").split(" ")).map(Integer::parseInt)
                    .collect(toList());
            int result = Result.unboundedKnapsack(k, arr);
            bufferedWriter.write(String.valueOf(result));
            bufferedWriter.newLine();
        }
        bufferedReader.close();
        bufferedWriter.close();
    }
}
