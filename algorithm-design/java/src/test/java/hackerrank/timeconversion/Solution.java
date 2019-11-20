package hackerrank.timeconversion;

import java.util.Scanner;

/**
 * https://www.hackerrank.com/challenges/time-conversion
 */
public class Solution {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println(toMilitary(in.next()));
    }

    static String toMilitary(String ampm) {
        boolean afternoon = ampm.charAt(8) == 'P';
        String hour = ampm.substring(0, 2);
        afternoon = hour.equals("12") ? !afternoon : afternoon;
        hour = String.format("%02d", (Integer.parseInt(hour) + (afternoon ? 12 : 0)) % 24);
        return hour + ampm.substring(2, 8);
    }
}