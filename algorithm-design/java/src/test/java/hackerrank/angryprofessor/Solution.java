package hackerrank.angryprofessor;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

/**
 * https://www.hackerrank.com/challenges/angry-professor
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
            int k = in.nextInt();
            int[] a = new int[n];
            for (int j = 0; j < n; j++) {
                a[j] = in.nextInt();
            }
            outOverride.println(checkCancellation(a, k) ? "YES" : "NO");
        }
    }

    private static boolean checkCancellation(int[] a, int k) {
        int absent = 0;
        int n = a.length;
        for (int arrival : a) {
            if (arrival > 0) {
                absent++;
            }
            if (n - absent < k) {
                return true;
            }
        }
        return n - absent < k;
    }
}
