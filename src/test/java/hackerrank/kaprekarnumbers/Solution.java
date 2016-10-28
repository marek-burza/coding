package hackerrank.kaprekarnumbers;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * https://www.hackerrank.com/challenges/kaprekar-numbers
 */
public class Solution {
    private static InputStream inOverride = null;
    private static PrintStream outOverride = null;

    public static void main(String[] args) {
        if (null == inOverride) {
            inOverride = System.in;
        }
        if (null == outOverride) {
            outOverride = System.out;
        }
        Scanner in = new Scanner(inOverride);
        int p = in.nextInt();
        int q = in.nextInt();
        boolean first = true;
        for (String value : kaprekarNumbers(p, q)) {
            if (!first) {
                outOverride.print(" ");
            } else {
                first = false;
            }
            outOverride.print(value);
        }
    }

    private static String[] kaprekarNumbers(int p, int q) {
        List<String> found = new ArrayList<>();
        for (int n = p; n <= q; n++) {
            int d = countDigits(n);
            long nn = (long) n * (long) n;
            long splitter = (long) Math.pow(10, d);
            long r = nn / splitter;
            long l = nn % splitter;
            if (n == r + l) {
                found.add(String.valueOf(n));
            }
        }
        if (found.isEmpty()) {
            return new String[]{"INVALID RANGE"};
        } else {
            return found.toArray(new String[found.size()]);
        }
    }

    private static int countDigits(long v) {
        return 1 + (int) Math.log10(v);
    }
}
