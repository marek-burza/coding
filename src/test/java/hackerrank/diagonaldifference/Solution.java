package hackerrank.diagonaldifference;

import java.util.Scanner;

/**
 * https://www.hackerrank.com/challenges/diagonal-difference
 */
public class Solution {
    static long diagonalDifference(int n, int[][] matrix) {
        int result = 0;
        for (int i = 0; i < n; i++) {
            result += matrix[i][i] - matrix[n - 1 - i][i];
        }
        return Math.abs(result);
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int a[][] = new int[n][n];
        for (int a_i = 0; a_i < n; a_i++) {
            for (int a_j = 0; a_j < n; a_j++) {
                a[a_i][a_j] = in.nextInt();
            }
        }
        System.out.println(diagonalDifference(n, a));
    }
}
