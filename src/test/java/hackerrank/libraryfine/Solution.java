package hackerrank.libraryfine;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

/**
 * https://www.hackerrank.com/challenges/library-fine
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
        int da = in.nextInt();
        int ma = in.nextInt();
        int ya = in.nextInt();
        int de = in.nextInt();
        int me = in.nextInt();
        int ye = in.nextInt();
        outOverride.println(libraryFine(da, ma, ya, de, me, ye));
    }

    private static int libraryFine(int da, int ma, int ya, int de, int me, int ye) {
        if (ya > ye) {
            return 10000;
        } else if (ya == ye) {
            if (ma > me) {
                return (ma - me) * 500;
            } else if (ma == me) {
                if (da > de) {
                    return (da - de) * 15;
                }
            }
        }
        return 0;
    }
}
