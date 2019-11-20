package hackerrank.acmicpcteam;

import java.io.InputStream;
import java.io.PrintStream;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * https://www.hackerrank.com/challenges/acm-icpc-team
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
        int m = in.nextInt();
        List<String> people = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            people.add(in.next());
        }
        for (int i : bestTeam(people)) {
            outOverride.println(i);
        }
    }

    private static int[] bestTeam(List<String> people) {
        int maximum = 0;
        int count = 0;
        List<BigInteger> encoded = new ArrayList<>();
        int n = people.size();
        for (int i = 0; i < n; i++) {
            encoded.add(new BigInteger(people.get(i), 2));
        }
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                BigInteger coverage = encoded.get(i).or(encoded.get(j));
                int covered = coverage.bitCount();
                if (maximum == covered) {
                    count++;
                } else if (maximum < covered) {
                    maximum = covered;
                    count = 1;
                }
            }
        }
        return new int[]{maximum, count};
    }
}
