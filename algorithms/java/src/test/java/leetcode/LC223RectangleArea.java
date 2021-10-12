package leetcode;

import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * https://leetcode.com/problems/rectangle-area/
 * #medium
 */
public final class LC223RectangleArea {
    public final class Solution {
        private int area(final int left, final int bottom, final int right, final int top) {
            return (right - left) * (top - bottom);
        }

        public int computeArea(final int A, final int B, final int C, final int D, final int E, final int F, final int G, final int H) {
            int total = area(A, B, C, D) + area(E, F, G, H);
            int top = Math.min(D, H);
            int bottom = Math.max(B, F);
            int left = Math.max(A, E);
            int right = Math.min(C, G);
            if (bottom < top && left < right) {
                total -= area(left, bottom, right, top);
            }
            return total;
        }
    }

    @Test
    public void test_Minus3_0_3_4_0_Minus1_9_2() throws Exception {
        assertEquals(45, new Solution().computeArea(-3, 0, 3, 4, 0, -1, 9, 2));
    }
}
