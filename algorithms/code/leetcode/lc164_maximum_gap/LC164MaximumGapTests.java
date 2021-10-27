package leetcode.lc164_maximum_gap;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public final class LC164MaximumGapTests {
    @Test
    public void test33And2and100And70() throws Exception {
        int[] nums1 = { 33, 2, 100, 70 };
        assertEquals(37, new LC164MaximumGap().maximumGap(nums1));
    }

    @Test
    public void testNothing() throws Exception {
        assertEquals(0, new LC164MaximumGap().maximumGap(null));
        assertEquals(0, new LC164MaximumGap().maximumGap(new int[0]));
    }
}
