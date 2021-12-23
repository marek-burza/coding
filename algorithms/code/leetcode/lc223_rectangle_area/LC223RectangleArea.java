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
