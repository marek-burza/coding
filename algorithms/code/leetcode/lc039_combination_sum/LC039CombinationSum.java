package leetcode.lc039_combination_sum;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/combination-sum/
 * #medium
 */
public final class LC039CombinationSum {
    public void combinationSum(final int[] candidates, final int target, final List<Integer> path, final int sum,
            final int index, final List<List<Integer>> combos) {
        if (sum == target) {
            combos.add(new ArrayList<>(path));
            return;
        }
        List<Integer> innerPath = new ArrayList<>(path);
        for (int partial = 0; partial <= target - sum && index < candidates.length; partial += candidates[index]) {
            combinationSum(candidates, target, innerPath, sum + partial, index + 1, combos);
            innerPath.add(candidates[index]);
        }
    }

    public List<List<Integer>> combinationSum(final int[] candidates, final int target) {
        List<List<Integer>> combos = new ArrayList<>();
        combinationSum(candidates, target, new ArrayList<>(), 0, 0, combos);
        return combos;
    }
}
