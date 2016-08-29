package hackerrank.comparethetriplets;

import java.util.Arrays;
import java.util.Scanner;

/**
 * https://www.hackerrank.com/challenges/compare-the-triplets
 */
public class Solution {
    static int[] compareTriplets(int a0, int a1, int a2, int b0, int b1, int b2) {
        int[] result = {0, 0};
        result[0] = (a0 > b0 ? 1 : 0) + (a1 > b1 ? 1 : 0) + (a2 > b2 ? 1 : 0);
        result[1] = (a0 < b0 ? 1 : 0) + (a1 < b1 ? 1 : 0) + (a2 < b2 ? 1 : 0);
        return result;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int a0 = in.nextInt();
        int a1 = in.nextInt();
        int a2 = in.nextInt();
        int b0 = in.nextInt();
        int b1 = in.nextInt();
        int b2 = in.nextInt();
        int[] result = Solution.compareTriplets(a0, a1, a2, b0, b1, b2);
        System.out.println(String.format("%d %d\n", result[0], result[1]));
    }
}

