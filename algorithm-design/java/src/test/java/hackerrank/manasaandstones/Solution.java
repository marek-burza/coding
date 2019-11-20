package hackerrank.manasaandstones;

import java.io.InputStream;
import java.io.PrintStream;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * https://www.hackerrank.com/challenges/manasa-and-stones
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
        int t = in.nextInt();
        for (int i = 0; i < t; i++) {
            int n = in.nextInt();
            int a = in.nextInt();
            int b = in.nextInt();
            for (BigInteger value : manasaAndStones(n, a, b)) {
                outOverride.print(value);
                outOverride.print(" ");
            }
            outOverride.println();
        }
    }

    private static List<BigInteger> manasaAndStones(int n, int a, int b) {
        List<BigInteger> result = new ArrayList<>();
        if (a > b) {
            int exchange = a;
            a = b;
            b = exchange;
        }
        BigInteger current = BigInteger.valueOf(a * (n - 1));
        BigInteger delta = BigInteger.valueOf(b - a);
        result.add(current);
        if (a != b) {
            for (int i = 0; i < n - 1; i++) {
                current = current.add(delta);
                result.add(current);
            }
        }
        return result;
    }
}
