package leetcode;

/**
 * https://leetcode.com/problems/jump-game-ii/
 */
public class LC045JumpGameII {
    public int jump(int[] nums) {
        if (nums.length == 1) return 0;
        int horizon = nums[0];
        int i = 0;
        int count = 1;
        while (horizon < nums.length - 1) {
            int replacement = horizon;
            while (i <= horizon) {
                if (i + nums[i] > replacement) {
                    replacement = i + nums[i];
                }
                i++;
            }
            i--;
            horizon = replacement;
            count++;
        }
        return count;
    }

    public static void main(String[] arguments) {
        LC045JumpGameII solution = new LC045JumpGameII();
        int[] nums = {2, 3, 1, 1, 4};
        System.out.println(solution.jump(nums));
    }
}
