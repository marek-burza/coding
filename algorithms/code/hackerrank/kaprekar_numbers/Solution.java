package hackerrank.kaprekar_numbers;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 * https://www.hackerrank.com/challenges/kaprekar-numbers
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
        int p = in.nextInt();
        int q = in.nextInt();
        boolean first = true;
        try {
            for (String value : SolutionCore.kaprekarNumbers(p, q)) {
                if (!first) {
                    outOverride.write(" ");
                } else {
                    first = false;
                }
                outOverride.write(String.valueOf(value));
            }
            outOverride.flush();
            outOverride.close();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        } finally {
            in.close();
        }
    }
}
