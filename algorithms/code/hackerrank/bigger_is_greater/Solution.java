package hackerrank.bigger_is_greater;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.IntStream;

/**
 * https://www.hackerrank.com/challenges/bigger-is-greater
 */
public final class Solution {
    private Solution() {
    }

    private static Reader inOverride = null;
    private static Writer outOverride = null;

    public static String[] prepare(final Reader override) throws IOException {
        Reader reader = override == null ? new InputStreamReader(System.in, "UTF-8") : override;
        BufferedReader bufferedReader = new BufferedReader(reader);
        List<String> prepared = new LinkedList<>();
        for (String line = bufferedReader.readLine(); line != null; line = bufferedReader.readLine()) {
            prepared.add(line.strip());
        }
        bufferedReader.close();
        return prepared.toArray(new String[0]);
    }

    public static void main(final String[] args) throws IOException {
        String[] input = prepare(inOverride);
        Writer outUsed = outOverride != null ? outOverride
                : new OutputStreamWriter(new FileOutputStream(System.getenv("OUTPUT_PATH")), "UTF-8");
        BufferedWriter bufferedWriter = new BufferedWriter(outUsed);
        int t = Integer.parseInt(input[0]);
        IntStream.range(0, t).forEach(ti -> {
            try {
                String w = input[1 + ti];
                String result = SolutionCore.biggerIsGreater(w);
                bufferedWriter.write(result);
                bufferedWriter.newLine();
            } catch (IOException exception) {
                throw new RuntimeException(exception);
            }
        });
        bufferedWriter.close();
    }
}
