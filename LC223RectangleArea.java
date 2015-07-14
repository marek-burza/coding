package leetcode;

/**
 * https://leetcode.com/problems/rectangle-area/
 */
public class LC223RectangleArea {
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

    public static void main(String[] arguments) {
        LC223RectangleArea solution = new LC223RectangleArea();
        System.out.println(solution.computeArea(-3, 0, 3, 4, 0, -1, 9, 2));
    }
}
