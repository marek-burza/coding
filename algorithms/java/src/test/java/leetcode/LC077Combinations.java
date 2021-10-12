package leetcode;

import org.junit.jupiter.api.Test;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * https://leetcode.com/problems/combinations/
 * #medium
 */
public class LC077Combinations {
    public final class Solution {
        public void combine(int m, int n, int k, List<Integer> prefix, List<List<Integer>> found) {
            for (int i = m; i <= n - (k - 1) + prefix.size(); i++) {
                prefix.add(i);
                if (prefix.size() == k) {
                    found.add(new ArrayList(prefix));
                } else {
                    combine(i + 1, n, k, prefix, found);
                }
                prefix.remove(prefix.size() - 1);
            }
        }

        public List<List<Integer>> combine(int n, int k) {
            List<List<Integer>> found = new ArrayList();
            combine(1, n, k, new ArrayList(), found);
            return found;
        }
    }

    private class IntegerListComparator implements Comparator<List<Integer>> {
        @Override
        public int compare(List<Integer> l1, List<Integer> l2) {
            if (l1.size() < l2.size()) return -1;
            if (l1.size() > l2.size()) return 1;
            for (int i = 0; i < l1.size(); i++) {
                if (l1.get(i) < l2.get(i)) return -1;
                if (l1.get(i) > l2.get(i)) return 1;
            }
            return 0;
        }
    }

    @Test
    public void test_example() throws Exception {
        int[][] expected = {{1, 2}, {1, 3}, {1, 4}, {2, 3}, {2, 4}, {3, 4}};
        List<List<Integer>> result = new Solution().combine(4, 2);
        Collections.sort(result, new IntegerListComparator());
        assertEquals(expected.length, result.size());
        for (int i = 0; i < expected.length; i++) {
            assertEquals(expected[i].length, result.get(i).size());
            for (int j = 0; j < expected[i].length; j++) {
                assertEquals(expected[i][j], result.get(i).get(j).intValue());
            }
        }
    }
}
