package hackerrank.averybigsum;

import java.util.Scanner;
import java.util.stream.Stream;

/**
 * https://www.hackerrank.com/challenges/a-very-big-sum
 */
public class Solution {
    static long bigSum(int n, int[] array) {
        long sum = 0;
        for (int i = 0; i < n; i++) {
            sum += array[i];
        }
        return sum;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        scanner.useDelimiter(System.getProperty("line.separator"));
        int n = scanner.nextInt();
        int[] array = Stream.of(scanner.next().split(" ")).mapToInt(Integer::parseInt).toArray();
        System.out.println(Long.toString(Solution.bigSum(n, array)));
    }
}
