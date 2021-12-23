package leetcode.lc216_combination_sum_iii;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/combination-sum-iii/
 * #medium
 */
public final class LC216CombinationSumIII {
    private void traverse(final int contains, final int sum, final int left, final int n, final List<Integer> found,
            final int start) {
        if (left == 0 && sum == n) {
            found.add(contains);
        } else {
            for (int i = start; i <= 9; i++) {
                int mask = 1 << i;
                // if ((contains & mask) == 0) {
                traverse(contains | mask, sum + i, left - 1, n, found, i + 1);
                // }
            }
        }
    }

    public List<List<Integer>> combinationSum3(final int k, final int n) {
        List<Integer> found = new ArrayList<>();
        traverse(0, 0, k, n, found, 1);
        List<List<Integer>> all = new ArrayList<>();
        for (int contains : found) {
            List<Integer> entry = new ArrayList<>();
            all.add(entry);
            for (int i = 1; i <= 9; i++) {
                int mask = 1 << i;
                if ((contains & mask) != 0) {
                    entry.add(i);
                }
            }
        }
        return all;
    }
}
