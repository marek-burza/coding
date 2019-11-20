package hackerrank.maximizingxor;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

/**
 * https://www.hackerrank.com/challenges/maximizing-xor
 */
public class Solution {
    private static InputStream inOverride = null;
    private static PrintStream outOverride = null;

//    private static int maximizingXORSlow(int l, int r) {
//        int max = Integer.MIN_VALUE;
//        for (int a = l; a <= r - 1; a++) {
//            for (int b = a + 1; b <= r; b++) {
//                if (max < (a ^ b)) {
//                    max = a ^ b;
//                }
//            }
//        }
//        return max;
//    }

    private static int maximizingXOR(int l, int r) {
        int max = l ^ r;
        max |= max >> 1;
        max |= max >> 2;
        max |= max >> 4;
        max |= max >> 8;
        return max;
    }

    public static void main(String[] args) {
        if (null == inOverride) {
            inOverride = System.in;
        }
        if (null == outOverride) {
            outOverride = System.out;
        }
        Scanner in = new Scanner(inOverride);
        int l = in.nextInt();
        int r = in.nextInt();
        outOverride.println(maximizingXOR(l, r));
    }
}
