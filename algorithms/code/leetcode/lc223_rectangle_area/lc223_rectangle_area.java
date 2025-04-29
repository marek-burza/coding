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
```rust
// rectangle_area.rs

/// https://leetcode.com/problems/rectangle-area/
pub struct LC223RectangleArea;

impl LC223RectangleArea {
    /// Calculate the area of a rectangle.
    pub fn area(&self, left: i32, bottom: i32, right: i32, top: i32) -> i32 {
        (right - left) * (top - bottom)
    }

    /// Compute the total area of two rectangles and subtract the overlap area if they intersect.
    pub fn compute_area(&self, a: i32, b: i32, c: i32, d: i32, e: i32, f: i32, g: i32, h: i32) -> i32 {
        let total = self.area(a, b, c, d) + self.area(e, f, g, h);
        let top = (d < h).unwrap_or(d == h) as i32;
        let bottom = ((b > f).unwrap_or(b == f)) as i32;
        let left = (a < e).unwrap_or(a == e) as i32;
        let right = ((c < g).unwrap_or(c == g)) as i32;

        if bottom < top && left < right {
            -self.area(left, bottom, right, top)
        } else {
            total
        }
    }
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_minus30340_minus192() {
        assert_eq!(45, LC223RectangleArea::compute_area(-3, 0, 3, 4, 0, -1, 9, 2));
    }

    #[test]
    fn test_minus2_minus222_minus1416() {
        assert_eq!(20, LC223RectangleArea::compute_area(-2, -2, 2, 2, -1, 4, 1, 6));
    }

    #[test]
    fn test_minus5_minus5_minus40_minus3_minus333() {
        assert_eq!(41, LC223RectangleArea::compute_area(-5, -5, -4, 0, -3, -3, 3, 3));
    }
}
```