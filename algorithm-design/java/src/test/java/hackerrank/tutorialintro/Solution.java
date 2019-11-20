package hackerrank.tutorialintro;

import java.util.*;
import java.util.stream.*;

/**
 * https://www.hackerrank.com/challenges/tutorial-intro
 */
public class Solution {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        scanner.useDelimiter(System.getProperty("line.separator"));
        int v = scanner.nextInt();
        int n = scanner.nextInt();
        int[] array = Stream.of(scanner.next().split(" ")).mapToInt(Integer::parseInt).toArray();
        System.out.println(search(v, n, array));
    }

    static int search(int v, int n, int[] array) {
        int a = 0;
        int z = n - 1;
        while (a <= z) {
            int m = (a + z) >> 1;
            if (array[m] == v) return m;
            if (array[m] < v) a = m + 1;
            if (array[m] > v) z = m - 1;
        }
        return -1;
    }
}
