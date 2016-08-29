package hackerrank.camelcase;

import java.util.Scanner;

/**
 * https://www.hackerrank.com/challenges/camelcase
 */
public class Solution {
    static int countWords(String s) {
        int count = 1;
        for (int i = s.length() - 1; 0 <= i; i--) {
            if (Character.isUpperCase(s.charAt(i))) {
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String s = in.next();
        System.out.println(countWords(s));
    }
}
