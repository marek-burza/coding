package hackerrank.manasa_and_stones;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 * https://www.hackerrank.com/challenges/manasa-and-stones
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
        int t = in.nextInt();
        try {
            for (int i = 0; i < t; i++) {
                int n = in.nextInt();
                int a = in.nextInt();
                int b = in.nextInt();
                for (BigInteger value : SolutionCore.manasaAndStones(n, a, b)) {
                    outOverride.write(String.valueOf(value));
                    outOverride.write(" ");
                }
                outOverride.write('\n');
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
