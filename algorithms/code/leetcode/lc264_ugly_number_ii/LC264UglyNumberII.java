package leetcode.lc264_ugly_number_ii;

import java.util.PriorityQueue;

/**
 * https://leetcode.com/problems/ugly-number-ii/ #medium
 */
public final class LC264UglyNumberII {
    public int nthUglyNumber(final int n) {
        if (n == 1) {
            return 1;
        }
        PriorityQueue<Long> uglies = new PriorityQueue<>();
        uglies.add(1L);
        for (int i = 1; i < n; i++) {
            long smallest = uglies.poll();
            while (!uglies.isEmpty() && uglies.peek() == smallest) {
                uglies.poll();
            }
            uglies.add(smallest * 2);
            uglies.add(smallest * 3);
            uglies.add(smallest * 5);
        }
        return uglies.poll().intValue();
    }
}
