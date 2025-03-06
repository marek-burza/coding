package leetcode.lc162_find_peak_element;

/**
 * https://leetcode.com/problems/find-peak-element/
 * #medium
 */
public final class LC162FindPeakElement {
    public int findPeakElement(final int[] nums) {
        for (int i = 1; i <= nums.length; i++) {
            boolean postFalling;
            postFalling = i == nums.length || nums[i - 1] > nums[i];
            if (postFalling) {
                return i - 1;
            }
        }
        return -1;
    }
}
package leetcode.lc162_find_peak_element;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public final class LC162FindPeakElementTests {
    @Test
    public void test1231() throws Exception {
        assertEquals(2, new LC162FindPeakElement().findPeakElement(new int[] { 1, 2, 3, 1 }));
    }

    @Test
    public void test1234() throws Exception {
        assertEquals(3, new LC162FindPeakElement().findPeakElement(new int[] { 1, 2, 3, 4 }));
    }

    @Test
    public void testNothing() throws Exception {
        assertEquals(-1, new LC162FindPeakElement().findPeakElement(new int[] {}));
    }
}
