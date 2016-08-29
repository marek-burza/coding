package hackerrank.simplearraysum;

import java.util.Scanner;
import java.util.stream.Stream;

/**
 * https://www.hackerrank.com/challenges/simple-array-sum
 */
public class Solution {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        scanner.useDelimiter(System.getProperty("line.separator"));
        int n = scanner.nextInt();
        int[] array = Stream.of(scanner.next().split(" ")).mapToInt(Integer::parseInt).toArray();
        System.out.println(Long.toString(Solution.simpleArraySum(n, array)));
    }

    static long simpleArraySum(int n, int[] array) {
        long sum = 0;
        for (int i = 0; i < n; i++) {
            sum += array[i];
        }
        return sum;
    }
}