package leetcode.lc164_maximum_gap;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/maximum-gap/ #hard
 */
public final class LC164MaximumGap {
    public int maximumGap(final int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int n = nums.length;
        int maxE = Arrays.stream(nums).max().getAsInt();
        int minE = Arrays.stream(nums).min().getAsInt();
        double len = (double) (maxE - minE) / (double) (n - 1);
        int[] maxA = new int[n];
        Arrays.fill(maxA, Integer.MIN_VALUE);
        int[] minA = new int[n];
        Arrays.fill(minA, Integer.MAX_VALUE);
        for (int num : nums) {
            int index = (int) ((num - minE) / len);
            maxA[index] = Math.max(maxA[index], num);
            minA[index] = Math.min(minA[index], num);
        }
        int gap = 0;
        int prev = maxA[0];
        for (int i = 1; i < n; i++) {
            if (minA[i] == Integer.MAX_VALUE) {
                continue;
            }
            gap = Math.max(gap, minA[i] - prev);
            prev = maxA[i];
        }
        return gap;
        // Pigeon hole principle:
        // We keep the biggest and smallest pigeon fitting in the hole
        // and that's enough to find the gap in linear way
    }
}
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
