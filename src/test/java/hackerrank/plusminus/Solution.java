package hackerrank.plusminus;

import java.util.Arrays;
import java.util.Scanner;

/**
 * https://www.hackerrank.com/challenges/plus-minus
 */
public class Solution {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int arr[] = new int[n];
        for (int arr_i = 0; arr_i < n; arr_i++) {
            arr[arr_i] = in.nextInt();
        }
        double[] result = fractions(n, arr);
        Arrays.stream(result).forEach(System.out::println);
    }

    static double[] fractions(int n, int[] array) {
        int[] counts = new int[3];
        for (int value : array) {
            int index = 0 < value ? 0 : value < 0 ? 1 : 2;
            counts[index]++;

        }
        return Arrays.stream(counts).mapToDouble(a -> a).map(v -> v / (double) n).toArray();
    }
}

