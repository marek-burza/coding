package hackerrank.fibonaccimodified;

import java.math.BigInteger;
import java.util.Scanner;

/**
 * https://www.hackerrank.com/challenges/fibonacci-modified
 */
public class Solution {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t1 = scanner.nextInt();
        int t2 = scanner.nextInt();
        int n = scanner.nextInt();
        System.out.println(fibonacciModified(t1, t2, n));
    }

    static String fibonacciModified(int t1, int t2, int n) {
        BigInteger tn1 = BigInteger.valueOf(t1);
        BigInteger tn2 = BigInteger.valueOf(t2);
        while (3 <= n) {
            BigInteger result = BigInteger.valueOf(0);
            result = result.add(tn2);
            result = result.multiply(result);
            result = result.add(tn1);
            tn1 = tn2;
            tn2 = result;
            n--;
        }
        return tn2.toString();
    }
}