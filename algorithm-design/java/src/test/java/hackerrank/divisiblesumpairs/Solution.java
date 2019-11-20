package hackerrank.divisiblesumpairs;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.*;

/**
 * https://www.hackerrank.com/challenges/divisible-sum-pairs
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
        int a[] = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = in.nextInt();
        }
        outOverride.println(countDivisibleSumPairs(a, k));
    }

//    private static BigInteger nChooseK(final int N, final int K) {
//        BigInteger result = BigInteger.ONE;
//        for (int k = 0; k < K; k++) {
//            result = result.multiply(BigInteger.valueOf(N - k)).divide(BigInteger.valueOf(k + 1));
//        }
//        return result;
//    }

    private static long nChooseK(final int N, final int K) {
        long result = 1;
        for (int k = 0; k < K; k++) {
            result = result * (N - k) / (k + 1);
        }
        return result;
    }

    private static int countDivisibleSumPairs(int[] array, int k) {
        Map<Integer, Integer> counted = new HashMap<>();
        for (int value : array) {
            int rest = value % k;
            Integer count = counted.get(rest);
            if (null == count) {
                counted.put(rest, 1);
            } else {
                counted.put(rest, count + 1);
            }
        }
        int total = 0;
        Set<Integer> covered = new HashSet<>();
        for (int a : counted.keySet()) {
            if (covered.contains(a)) {
                continue;
            }
            if (a == 0) {
                total += nChooseK(counted.get(a), 2);
                covered.add(a);
            } else {
                int b = k - a;
                if (b == a) {
                    total += nChooseK(counted.get(a), 2);
                    covered.add(a);
                }else {
                    if (counted.containsKey(b)) {
                        total += counted.get(a) * counted.get(b);
                        covered.add(a);
                        covered.add(b);
                    }
                }
            }
        }
        return total;
    }
}
