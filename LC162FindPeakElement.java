package leetcode;

/**
 * https://leetcode.com/problems/find-peak-element/
 */
public class LC162FindPeakElement {
    public int findPeakElement(int[] nums) {
        boolean anteRising = true;
        for (int i = 1; i <= nums.length; i++) {
            boolean postFalling;
            if (i == nums.length) {
                postFalling = true;
            } else {
                postFalling = nums[i - 1] > nums[i];
            }
            if (anteRising && postFalling) {
                return i - 1;
            }
            anteRising = !postFalling;
        }
        return -1;
    }

    public static void main(String[] arguments) {
        LC162FindPeakElement solution = new LC162FindPeakElement();
        System.out.println(solution.findPeakElement(new int[]{1, 2, 3, 1}));
        System.out.println(solution.findPeakElement(new int[]{1, 2, 3, 4}));
    }
}
