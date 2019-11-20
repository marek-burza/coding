package hackerrank.kangaroo;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

/**
 * https://www.hackerrank.com/challenges/kangaroo
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
        int x1 = in.nextInt();
        int v1 = in.nextInt();
        int x2 = in.nextInt();
        int v2 = in.nextInt();
        outOverride.println(checkKangaroos(x1, v1, x2, v2) ? "YES" : "NO");
    }

    private static boolean checkKangaroos(int x1, int v1, int x2, int v2) {
        if (v1 == v2) return x1 == x2;
        return 0 == (x2 - x1) % (v2 - v1) && 0 > (x2 - x1) / (v2 - v1);
    }
}
