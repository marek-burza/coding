package leetcode.lc042_trapping_rain_water;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Comparator;

/**
 * https://leetcode.com/problems/trapping-rain-water/ #hard
 */
public final class LC042TrappingRainWater {
    private static class MappingComparator implements Comparator<Integer>, Serializable {
        private int[] other;

        MappingComparator(final int[] otherValue) {
            other = otherValue;
        }

        public int compare(final Integer i1, final Integer i2) {
            return other[i2] - other[i1];
        }
    }

    private int amount(final int[] height, final int from, final int to) {
        int amount = Math.min(height[from], height[to]) * (to - from - 1);
        for (int i = from + 1; i < to; i++) {
            amount -= height[i];
        }
        return amount;
    }

    public int trap(final int[] height) {
        if (height == null || height.length < 3) {
            return 0;
        }
        // Sort the terrain
        Integer[] sorted = new Integer[height.length];
        for (int i = 0; i < height.length; i++) {
            sorted[i] = i;
        }
        Arrays.sort(sorted, new MappingComparator(height));
        // Fill from the top
        // (pick highest and then extend "exclusion zone")
        int count = 0;
        int left = sorted[0];
        int right = sorted[0];
        for (int i : sorted) {
            if (right < i) {
                count += amount(height, right, i);
                right = i;
            }
            if (i < left) {
                count += amount(height, i, left);
                left = i;
            }
        }
        return count;
    }
}
