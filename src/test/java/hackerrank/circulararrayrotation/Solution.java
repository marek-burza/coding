package hackerrank.circulararrayrotation;

import java.util.Scanner;
import java.util.stream.Stream;

/**
 * https://www.hackerrank.com/challenges/circular-array-rotation
 */
public class Solution {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        scanner.useDelimiter(System.getProperty("line.separator"));
        int[] config = Stream.of(scanner.next().split(" ")).mapToInt(Integer::parseInt).toArray();
        int n = config[0];
        int k = config[1];
        int q = config[2];
        int[] array = Stream.of(scanner.next().split(" ")).mapToInt(Integer::parseInt).toArray();
        for (int i = 0; i < q; i++) {
            System.out.println(query(n, k, array, scanner.nextInt()));
        }
    }

    static int query(int n, int k, int[] array, int index) {
        return array[(index + n - (k % n)) % n];
    }
}
