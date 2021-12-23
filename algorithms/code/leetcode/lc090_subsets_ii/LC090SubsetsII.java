package leetcode.lc090_subsets_ii;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://leetcode.com/problems/subsets-ii/
 * #medium
 */
public final class LC090SubsetsII {
    public void subsets(final int[] nums, final int offset, final List<Integer> current,
            final List<List<Integer>> list) {
        list.add(new ArrayList<>(current));
        int i = offset;
        while (i < nums.length) {
            int count = 1;
            for (int j = i + 1; j < nums.length && nums[j - 1] == nums[j]; j++) {
                count++;
            }
            for (int j = 0; j < count; j++) {
                current.add(nums[i]);
                subsets(nums, i + count, current, list);
            }
            for (int j = 0; j < count; j++) {
                current.remove(current.size() - 1);
            }
            i += count;
        }
    }

    public List<List<Integer>> subsetsWithDup(final int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> list = new ArrayList<>();
        subsets(nums, 0, new ArrayList<Integer>(), list);
        return list;
    }
}
