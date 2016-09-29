package hackerrank.pairs;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Scanner;

/**
 * https://www.hackerrank.com/challenges/connected-cell-in-a-grid
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
        int k = in.nextInt();
        int[] array = new int[n];
        for (int i = 0; i < n; i++) {
            array[i] = in.nextInt();
        }
        outOverride.println(countPairs(array, k));
    }

    private static int countPairs(int[] array, int k) {
        Arrays.sort(array);
        int count = 0;
        for (int i = 0; i < array.length - 1; i++) {
            if (Arrays.binarySearch(array, i + 1, array.length, array[i] + k) >= 0) {
                count++;
            }
        }
        return count;
    }
}
