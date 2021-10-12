package leetcode.lc053_maximum_subarray;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LC053MaximumSubarrayTests {
    @Test
    public void testExample1() throws Exception {
        assertEquals(6, new LC053MaximumSubarray().maxSubArray(new int[] { -2, 1, -3, 4, -1, 2, 1, -5, 4 }));
    }

    @Test
    public void testExample2() throws Exception {
        assertEquals(1, new LC053MaximumSubarray().maxSubArray(new int[] { 1 }));
    }

    @Test
    public void testExample3() throws Exception {
        assertEquals(23, new LC053MaximumSubarray().maxSubArray(new int[] { 5, 4, -1, 7, 8 }));
    }

    @Test
    public void testMinus2And1() throws Exception {
        assertEquals(1, new LC053MaximumSubarray().maxSubArray(new int[] { -2, 1 }));
    }

    @Test
    public void testMinus2AndMinus1() throws Exception {
        assertEquals(-1, new LC053MaximumSubarray().maxSubArray(new int[] { -2, -1 }));
    }
}
