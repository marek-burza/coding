package leetcode.lc011_container_with_most_water;

/**
 * https://leetcode.com/problems/container-with-most-water/
 */
public final class LC011ContainerWithMostWater {
    public int maxArea(final int[] height) {
        int max = 0;
        int low = 0;
        int high = height.length - 1;
        while (low < high) {
            int top = Math.min(height[low], height[high]);
            max = Math.max(max, top * (high - low));
            if (height[low] <= height[high]) {
                low++;
            } else {
                high--;
            }
        }
        return max;
    }
}
