package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://leetcode.com/problems/subsets/
 */
public class LC078Subsets {
    public void subsets(int[] nums, int offset, List<Integer> current, List<List<Integer>> list) {
        list.add(new ArrayList<Integer>(current));
        for (int i = offset; i < nums.length; i++) {
            current.add(nums[i]);
            subsets(nums, i + 1, current, list);
            current.remove(current.size() - 1);
        }
    }

    public List<List<Integer>> subsets(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> list = new ArrayList<>();
        subsets(nums, 0, new ArrayList<Integer>(), list);
        return list;
    }

    public static void main(String[] arguments) {
        List<List<Integer>> list = new LC078Subsets().subsets(new int[]{1, 2, 3});
        for (List<Integer> subset : list) {
            System.out.println(Arrays.toString(subset.toArray()));
        }
    }
}
