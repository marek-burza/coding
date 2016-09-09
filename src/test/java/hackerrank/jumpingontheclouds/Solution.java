package hackerrank.jumpingontheclouds;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

/**
 * https://www.hackerrank.com/challenges/jumping-on-the-clouds
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
        int n = in.nextInt();
        int a[] = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = in.nextInt();
        }
        outOverride.println(countJumps(a));
    }

    private static int countJumps(int[] a) {
        int n = a.length;
        int count = 0;
        for (int i = 0; i < n - 1; ) {
            if (i + 2 > n - 1) {
                count++;
                break;
            }
            count += 1 + a[i + 2];
            i += 2 + a[i + 2];
//            if (1 == a[i + 2]) {
//                count += 2;
//                i += 3;
//            } else {
//                count++;
//                i += 2;
//            }
        }
        return count;
    }
}
