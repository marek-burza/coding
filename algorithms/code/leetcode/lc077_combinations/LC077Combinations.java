package leetcode.lc077_combinations;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/combinations/
 * #medium
 */
public final class LC077Combinations {
    public void combine(final int m, final int n, final int k, final List<Integer> prefix,
            final List<List<Integer>> found) {
        for (int i = m; i <= n - (k - 1) + prefix.size(); i++) {
            prefix.add(i);
            if (prefix.size() == k) {
                found.add(new ArrayList<>(prefix));
            } else {
                combine(i + 1, n, k, prefix, found);
            }
            prefix.remove(prefix.size() - 1);
        }
    }

    public List<List<Integer>> combine(final int n, final int k) {
        List<List<Integer>> found = new ArrayList<>();
        combine(1, n, k, new ArrayList<>(), found);
        return found;
    }
}
