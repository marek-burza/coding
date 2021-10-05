package leetcode;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * https://leetcode.com/problems/rectangle-area/
 * #medium
 */
public class LC223RectangleArea {
    public class Solution {
        private int area(int left, int bottom, int right, int top) {
            return (right - left) * (top - bottom);
        }

        public int computeArea(int A, int B, int C, int D, int E, int F, int G, int H) {
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
