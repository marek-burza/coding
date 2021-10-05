package leetcode;

import org.junit.Test;

import java.util.PriorityQueue;

import static org.junit.Assert.assertEquals;

/**
 * https://leetcode.com/problems/ugly-number-ii/
 * #medium
 */
public class LC264UglyNumberII {
    public class Solution {
        public int nthUglyNumber(int n) {
            if (n == 1) return 1;
            PriorityQueue<Long> uglies = new PriorityQueue();
            uglies.add(1l);
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

    @Test
    public void test_example() throws Exception {
        int[] expected = {1, 2, 3, 4, 5, 6, 8, 9, 10, 12};
        for (int i = 0; i < expected.length; i++) {
            assertEquals(expected[i], new Solution().nthUglyNumber(i + 1));
        }
    }

    @Test
    public void test_bigger() throws Exception {
        assertEquals(536870912, new Solution().nthUglyNumber(1407));
    }
}
