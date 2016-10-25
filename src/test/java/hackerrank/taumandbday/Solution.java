package hackerrank.taumandbday;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

/**
 * https://www.hackerrank.com/challenges/taum-and-bday
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
            int b = in.nextInt();
            int w = in.nextInt();
            int x = in.nextInt();
            int y = in.nextInt();
            int z = in.nextInt();
            outOverride.println(minimumCost(b, w, x, y, z));
        }
    }

    private static long minimumCost(int b, int w, int x, int y, int z) {
        long xAdjusted = x <= y + z ? x : y + z;
        long yAdjusted = y <= x + z ? y : x + z;
        return b * xAdjusted + w * yAdjusted;
    }
}
