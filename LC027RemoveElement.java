package leetcode;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/remove-element/
 */
public class LC027RemoveElement {
    public int removeElement(int[] nums, int val) {
        if (nums == null) return 0;
        int index = 0;
        for (int i = 0; i < nums.length; i++) {
            nums[index] = nums[i];
            if (nums[i] != val) {
                index++;
            }
        }
        return index;
    }

    public static void main(String[] arguments) {
        LC027RemoveElement solution = new LC027RemoveElement();
        int[] nums = {0, 42, 1, 2, 42, 3, 4};
        System.out.println(Arrays.toString(Arrays.copyOfRange(nums, 0, solution.removeElement(nums, 42))));
    }
}
