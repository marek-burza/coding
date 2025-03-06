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
package leetcode.lc090_subsets_ii;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.Serializable;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public final class LC090SubsetsIITests {
    private static class OrderlyComparator implements Comparator<List<Integer>>, Serializable {
        @Override
        public int compare(final List<Integer> l1, final List<Integer> l2) {
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

    public void test(final int[][] expected, final List<List<Integer>> result) throws Exception {
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
    public void test122() throws Exception {
        List<List<Integer>> list = new LC090SubsetsII().subsetsWithDup(new int[] { 1, 2, 2 });
        int[][] expected = {
                {},
                { 1 }, { 2 },
                { 1, 2 }, { 2, 2 },
                { 1, 2, 2 }
        };
        test(expected, list);
    }
}
