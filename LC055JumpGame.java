package leetcode;

/**
 * https://leetcode.com/problems/jump-game/
 */
public class LC055JumpGame {
    public boolean canJump(int[] nums) {
        if (nums == null || nums.length == 0) {
            return false;
        }
        if (nums.length == 1) {
            return true;
        }
        int front = 0;
        for (int i = 0; i <= front; i++) {
            if (front >= nums.length - 1) return true;
            if (i == front && nums[front] == 0) return false;
            if (front < i + nums[i]) front = i + nums[i];
        }
        return false;
    }

    public static void main(String[] arguments) {
        LC055JumpGame solution = new LC055JumpGame();
        int[] nums1 = new int[25003];
        for (int i = 0; i < nums1.length; i++) {
            nums1[i] = 25000 - i;
        }
        nums1[25000] = 1;
        nums1[25001] = 0;
        nums1[25002] = 0;
        System.out.println(solution.canJump(nums1));
        int[] nums2 = {1, 2, 3};
        System.out.println(solution.canJump(nums2));
    }
}
