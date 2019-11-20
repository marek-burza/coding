package hackerrank.sherlockandarray;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

/**
 * https://www.hackerrank.com/challenges/sherlock-and-array
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
            int[] a = new int[n];
            for (int j = 0; j < n; j++) {
                a[j] = in.nextInt();
            }
            outOverride.println(equilibrium(a) ? "YES" : "NO");
        }
    }

    private static boolean equilibrium(int[] a) {
        int[] left = new int[a.length];
        int[] right = new int[a.length];
        for (int i = 1; i < a.length; i++) {
            left[i] = left[i - 1] + a[i - 1];
            right[a.length - 1 - i] = right[a.length - i] + a[a.length - i];
        }
        for (int i = 0; i < a.length; i++) {
            if (left[i] == right[i]) return true;
        }
        return false;
    }
}
