package hackerrank.staircase;

import java.util.Scanner;
import java.util.stream.IntStream;

/**
 * https://www.hackerrank.com/challenges/staircase
 */
public class Solution {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        IntStream.range(0, n).forEach(i -> System.out.println(generateStep(n, i)));
    }

    static String generateStep(int n, int index) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < n; i++) {
            builder.append(i < n - 1 - index ? " " : "#");
        }
        return builder.toString();
    }
}