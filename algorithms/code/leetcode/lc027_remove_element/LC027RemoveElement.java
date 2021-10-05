package leetcode.lc027_remove_element;

/**
 * https://leetcode.com/problems/remove-element/
 * #easy
 */
public final class LC027RemoveElement {
    public int removeElement(final int[] nums, final int val) {
        if (nums == null) {
            return 0;
        }
        int index = 0;
        for (int i = 0; i < nums.length; i++) {
            nums[index] = nums[i];
            if (nums[i] != val) {
                index++;
            }
        }
        return index;
    }
}
