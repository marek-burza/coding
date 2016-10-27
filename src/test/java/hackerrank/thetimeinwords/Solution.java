package hackerrank.thetimeinwords;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

/**
 * https://www.hackerrank.com/challenges/the-time-in-words
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
        int h = in.nextInt();
        int m = in.nextInt();
        outOverride.println(sayTime(h, m));
    }

    private final static String[] lut = {
            "zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten",
            "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen", "nineteen", "twenty",
            "twenty one", "twenty two", "twenty three", "twenty four", "twenty five", "twenty six", "twenty seven", "twenty eight", "twenty nine", "thirty"
    };

    private static String sayTime(int h, int m) {
        StringBuilder result = new StringBuilder();
        if (m != 0) {
            if (m == 30) {
                result.append("half past ");
            } else if (m == 15) {
                result.append("quarter past ");
            } else if (m == 45) {
                result.append("quarter to ");
                h = (h + 1) % 12;
            } else if (m < 30) {
                result.append(lut[m]);
                if (m == 1) {
                    result.append(" minute past ");
                } else {
                    result.append(" minutes past ");
                }
            } else if (m > 30) {
                m = 60 - m;
                result.append(lut[m]);
                if (m == 1) {
                    result.append(" minute to ");
                } else {
                    result.append(" minutes to ");
                }
                h = (h + 1) % 12;
            }
        }
        result.append(lut[h]);
        if (m == 0) {
            result.append(" o' clock");
        }
        return result.toString();
    }
}
