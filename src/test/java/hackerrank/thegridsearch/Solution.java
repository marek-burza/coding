package hackerrank.thegridsearch;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * https://www.hackerrank.com/challenges/the-grid-search
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
            int r = in.nextInt();
            in.nextInt();
            String[] big = new String[r];
            for (int j = 0; j < r; j++) {
                big[j] = in.next();
            }
            r = in.nextInt();
            in.nextInt();
            String[] small = new String[r];
            for (int j = 0; j < r; j++) {
                small[j] = in.next();
            }
            outOverride.println(checkGrid(big, small) ? "YES" : "NO");
        }
    }

    private static boolean checkGrid(String[] big, String[] small) {
        String all = Arrays.stream(big).collect(Collectors.joining(""));
        int at = -1;
        do {
            at = all.indexOf(small[0], at + 1);
            if ((at % big[0].length()) + small[0].length() <= big[0].length()) {
                break;
            }
        } while (at != -1);
        if (-1 == at) {
            return false;
        }
        for (String chunk : small) {
            if (!all.substring(at, at + chunk.length()).equals(chunk)) {
                return false;
            }
            at += big[0].length();
        }
        return true;
    }
}
