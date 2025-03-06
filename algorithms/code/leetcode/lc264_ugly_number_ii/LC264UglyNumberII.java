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
package leetcode.lc264_ugly_number_ii;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LC264UglyNumberIITests {
    @Test
    public void testExample() throws Exception {
        int[] expected = { 1, 2, 3, 4, 5, 6, 8, 9, 10, 12 };
        for (int i = 0; i < expected.length; i++) {
            assertEquals(expected[i], new LC264UglyNumberII().nthUglyNumber(i + 1));
        }
    }

    @Test
    public void testBigger() throws Exception {
        assertEquals(536870912, new LC264UglyNumberII().nthUglyNumber(1407));
    }
}
