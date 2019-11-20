package hackerrank.encryption;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

/**
 * https://www.hackerrank.com/challenges/encryption
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
        String plain = in.next();
        outOverride.println(encrypt(plain));
    }

    private static String encrypt(String plain) {
        int l = plain.length();
        int floor = (int) Math.floor(Math.sqrt(l));
        int ceil = (int) Math.ceil(Math.sqrt(l));
        int rows = -1, cols = -1;
        for (int c = ceil; c >= floor; c--) {
            int r = (l / c) + ((l % c) > 0 ? 1 : 0);
            if (r * c >= l) {
                rows = r;
                cols = c;
                break;
            }
        }
        StringBuilder result = new StringBuilder();
        for (int c = 0; c < cols; c++) {
            if (c != 0) {
                result.append(" ");
            }
            for (int r = 0; r < rows; r++) {
                int index = r * cols + c;
                if (index < l) {
                    result.append(plain.charAt(index));
                }
            }
        }
        return result.toString();
    }
}
