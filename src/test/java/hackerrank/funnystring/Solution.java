package hackerrank.funnystring;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

/**
 * https://www.hackerrank.com/challenges/funny-string
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
            outOverride.println(isFunny(in.next()) ? "Funny" : "Not Funny");
        }
    }

    private static boolean isFunny(String s) {
        String r = new StringBuilder(s).reverse().toString();
        int n = s.length();
        for (int i = 1; i < n; i++) {
            if (Math.abs(s.charAt(i) - s.charAt(i - 1)) != Math.abs(r.charAt(i) - r.charAt(i - 1))) {
                return false;
            }
        }
        return true;
    }
}
