package leetcode;

import org.junit.jupiter.api.Test;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * https://leetcode.com/problems/combination-sum/
 * #medium
 */
public class LC039CombinationSum {
    public final class Solution {
        public void combinationSum(int[] candidates, int target, List<Integer> path, int sum, int index, List<List<Integer>> combos) {
            if (sum == target) {
                combos.add(new ArrayList(path));
                return;
            }
            path = new ArrayList(path);
            for (int partial = 0; partial <= target - sum && index < candidates.length; partial += candidates[index]) {
                combinationSum(candidates, target, path, sum + partial, index + 1, combos);
                path.add(candidates[index]);
            }
        }

        public List<List<Integer>> combinationSum(int[] candidates, int target) {
            List<List<Integer>> combos = new ArrayList();
            combinationSum(candidates, target, new ArrayList(), 0, 0, combos);
            return combos;
        }
    }

    private class DeepComparator implements Comparator<List<Integer>> {
        @Override
        public int compare(List<Integer> list1, List<Integer> list2) {
            if (list1.size() < list2.size()) return -1;
            if (list1.size() > list2.size()) return 1;
            for (int i = 0, length = list1.size(); i < length; i++) {
                if (list1.get(i) < list2.get(i)) return -1;
                if (list1.get(i) > list2.get(i)) return 1;
            }
            return 0;
        }
    }

    @Test
    public void test_example() throws Exception {
        int[] candidates = {2, 3, 6, 7};
        int[][] expected = {{7}, {2, 2, 3}};
        List<List<Integer>> combos = new Solution().combinationSum(candidates, 7);
        for (List<Integer> list : combos)
            Collections.sort(list);
        Collections.sort(combos, new DeepComparator());
        assertEquals(expected.length, combos.size());
        for (int i = 0; i < expected.length; i++) {
            assertEquals(expected[i].length, combos.get(i).size());
            for (int j = 0; j < expected[i].length; j++) {
                assertEquals(expected[i][j], combos.get(i).get(j).intValue());
            }
        }
    }
}
