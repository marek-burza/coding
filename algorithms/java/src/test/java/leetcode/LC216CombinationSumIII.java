package leetcode;

import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertEquals;

/**
 * https://leetcode.com/problems/combination-sum-iii/
 */
public class LC216CombinationSumIII {
    public class Solution {
        private void traverse(int contains, int sum, int left, int n, List<Integer> found, int start) {
            if (left == 0 && sum == n) {
                found.add(contains);
            } else {
                for (int i = start; i <= 9; i++) {
                    int mask = 1 << i;
                    if ((contains & mask) == 0) {
                        traverse(contains | mask, sum + i, left - 1, n, found, i + 1);
                    }
                }
            }
        }

        public List<List<Integer>> combinationSum3(int k, int n) {
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

    private void test(int[][] expected, List<List<Integer>> result) {
        for (List<Integer> entry : result) {
            Collections.sort(entry);
        }
        //Collections.sort(result, );
        assertEquals(expected.length, result.size());
        for (int i = 0; i < expected.length; i++) {
            List<Integer> entry = result.get(i);
            assertEquals(expected[i].length, entry.size());
            for (int j = 0; j < expected[i].length; j++) {
                assertEquals(expected[i][j], entry.get(j).intValue());
            }
        }
    }

    @Test
    public void test_3_7() throws Exception {
        int[][] expected = {{1, 2, 4}};
        test(expected, new Solution().combinationSum3(3, 7));
    }

    @Test
    public void test_3_9() throws Exception {
        int[][] expected = {{1, 2, 6}, {1, 3, 5}, {2, 3, 4}};
        test(expected, new Solution().combinationSum3(3, 9));
    }
}
