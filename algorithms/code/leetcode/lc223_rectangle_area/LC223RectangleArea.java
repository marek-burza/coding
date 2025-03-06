package leetcode.lc223_rectangle_area;

/**
 * https://leetcode.com/problems/rectangle-area/
 * #medium
 */
public final class LC223RectangleArea {
    private int area(final int left, final int bottom, final int right, final int top) {
        return (right - left) * (top - bottom);
    }

    public int computeArea(final int a, final int b, final int c, final int d, final int e, final int f, final int g,
            final int h) {
        int total = area(a, b, c, d) + area(e, f, g, h);
        int top = Math.min(d, h);
        int bottom = Math.max(b, f);
        int left = Math.max(a, e);
        int right = Math.min(c, g);
        if (bottom < top && left < right) {
            total -= area(left, bottom, right, top);
        }
        return total;
    }
}
package leetcode.lc223_rectangle_area;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public final class LC223RectangleAreaTests {
    @Test
    public void testMinus30340Minus192() throws Exception {
        assertEquals(45, new LC223RectangleArea().computeArea(-3, 0, 3, 4, 0, -1, 9, 2));
    }

    @Test
    public void testMinus2Minus222Minus1416() throws Exception {
        assertEquals(20, new LC223RectangleArea().computeArea(-2, -2, 2, 2, -1, 4, 1, 6));
    }

    @Test
    public void testMinus5Minus5Minus40Minus3Minus333() throws Exception {
        assertEquals(41, new LC223RectangleArea().computeArea(-5, -5, -4, 0, -3, -3, 3, 3));
    }
}
