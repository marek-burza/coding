package hackerrank.camelcase;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.util.LinkedList;
import java.util.List;

/**
 * https://www.hackerrank.com/challenges/camelcase
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

    public static void main(final String[] args) {
        if (null == inOverride) {
            inOverride = new InputStreamReader(System.in, StandardCharsets.UTF_8);
        }
        if (null == outOverride) {
            outOverride = new OutputStreamWriter(System.out, StandardCharsets.UTF_8);
        }
        try {
            String[] input = prepare(inOverride);
            String s = input[0];
            outOverride.write(String.valueOf(SolutionCore.countWords(s)));
            outOverride.flush();
            outOverride.close();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }
}
