package hackerrank.time_conversion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.nio.charset.StandardCharsets;

/**
 * https://www.hackerrank.com/challenges/time-conversion
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
        BufferedReader bufferedReader = new BufferedReader(inOverride);
        try {
            outOverride.write(SolutionCore.toMilitary(bufferedReader.readLine()));
            outOverride.write('\n');
            outOverride.flush();
            outOverride.close();
            bufferedReader.close();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }
}
