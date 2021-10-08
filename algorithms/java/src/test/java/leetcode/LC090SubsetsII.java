package leetcode;

import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertEquals;

/**
 * https://leetcode.com/problems/subsets-ii/
 * #medium
 */
public class LC090SubsetsII {
    public final class Solution {
        public void subsets(int[] nums, int offset, List<Integer> current, List<List<Integer>> list) {
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

        public List<List<Integer>> subsetsWithDup(int[] nums) {
            Arrays.sort(nums);
            List<List<Integer>> list = new ArrayList<>();
            subsets(nums, 0, new ArrayList<Integer>(), list);
            return list;
        }
    }

    private class OrderlyComparator implements Comparator<List<Integer>> {
        @Override
        public int compare(List<Integer> l1, List<Integer> l2) {
            int difference = l1.size() - l2.size();
            if (0 != difference) {
                return difference;
            } else {
                for (int i = 0; i < l1.size(); i++) {
                    difference = l1.get(i).compareTo(l2.get(i));
                    if (0 != difference) {
                        return difference;
                    }
                }
                return 0;
            }
        }
    }

    public void test(int[][] expected, List<List<Integer>> result) throws Exception {
        Collections.sort(result, new OrderlyComparator());
        assertEquals(expected.length, result.size());
        for (int i = 0; i < expected.length; i++) {
            assertEquals(expected[i].length, result.get(i).size());
            Collections.sort(result.get(i));
            for (int j = 0; j < expected[i].length; j++) {
                assertEquals(expected[i][j], result.get(i).get(j).intValue());
            }
        }
    }

    @Test
    public void test_1_2_2() throws Exception {
        List<List<Integer>> list = new Solution().subsetsWithDup(new int[]{1, 2, 2});
        int[][] expected = {
                {},
                {1}, {2},
                {1, 2}, {2, 2},
                {1, 2, 2}
        };
        test(expected, list);
    }
}
