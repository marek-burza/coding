package hackerrank.biggerisbetter;

import java.util.Arrays;
import java.util.Scanner;

/**
 * https://www.hackerrank.com/challenges/bigger-is-greater
 */
public class Solution {
    static String advance(String s) {
        char[] array = s.toCharArray();
        for (int i = array.length - 1; 0 < i; i--) {
            if (array[i - 1] < array[i]) {
                Arrays.sort(array, i, array.length);
                for (int j = i; j < array.length; j++) {
                    if (array[i - 1] < array[j]) {
                        char exchange = array[i - 1];
                        array[i - 1] = array[j];
                        array[j] = exchange;
                        Arrays.sort(array, i, array.length);
                        return new String(array);
                    }
                }
            }
        }
        return "no answer";
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        for (int i = 0; i < n; i++) {
            String s = scanner.next();
            System.out.println(Solution.advance(s));
        }
    }
}
