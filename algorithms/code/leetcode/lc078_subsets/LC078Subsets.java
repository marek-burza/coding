package leetcode.lc078_subsets;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://leetcode.com/problems/subsets/
 * #medium
 */
public final class LC078Subsets {
    public void subsets(final int[] nums, final int offset, final List<Integer> current,
            final List<List<Integer>> list) {
        list.add(new ArrayList<>(current));
        for (int i = offset; i < nums.length; i++) {
            current.add(nums[i]);
            subsets(nums, i + 1, current, list);
            current.remove(current.size() - 1);
        }
    }

    public List<List<Integer>> subsets(final int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> list = new ArrayList<>();
        subsets(nums, 0, new ArrayList<Integer>(), list);
        return list;
    }
}
