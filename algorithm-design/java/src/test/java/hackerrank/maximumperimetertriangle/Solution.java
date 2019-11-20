package hackerrank.maximumperimetertriangle;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.*;

/**
 * https://www.hackerrank.com/challenges/maximum-perimeter-triangle
 */
public class Solution {
    private static InputStream inOverride = null;
    private static PrintStream outOverride = null;

    public static void main(String[] args) {
        if (null == inOverride) {
            inOverride = System.in;
        }
        if (null == outOverride) {
            outOverride = System.out;
        }
        Scanner in = new Scanner(inOverride);
        int n = in.nextInt();
        List<Integer> sticks = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            sticks.add(in.nextInt());
        }
        int[] picked = pick(sticks);
        if (null == picked) {
            outOverride.println(-1);
        } else {
            outOverride.printf("%d %d %d\n", picked[0], picked[1], picked[2]);
        }
    }

    private static int[] pick(List<Integer> sticks) {
        Collections.sort(sticks, Collections.reverseOrder());
        for (int i = 2; i < sticks.size(); i++) {
            for (int j = 0; j < i - 1; j++) {
                for (int k = j + 1; k < i; k++) {
                    int a = sticks.get(i);
                    int b = sticks.get(k);
                    int c = sticks.get(j);
                    if (a + b <= c || a + c <= b || b + c <= a) {
                        continue;
                    }
                    return new int[]{a, b, c};
                }
            }
        }
        return null;
    }
}
