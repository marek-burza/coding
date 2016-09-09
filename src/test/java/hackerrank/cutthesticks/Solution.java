package hackerrank.cutthesticks;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * https://www.hackerrank.com/challenges/cut-the-sticks
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
        List<Integer> cuts = countCuts(a);
        for (int i = 0; i < cuts.size(); i++) {
            outOverride.println(cuts.get(i));
        }
    }

    private static List<Integer> countCuts(int[] a) {
        Arrays.sort(a);
        List<Integer> cuts = new ArrayList<>();
        int cut = 0;
        int n = a.length;
        for (int i = 0; i < n; ) {
            cuts.add(n - i);
            cut = a[i];
            while (i < n && a[i] - cut <= 0) {
                i++;
            }
        }
        return cuts;
    }
}
