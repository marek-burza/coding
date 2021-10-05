package leetcode;

import org.junit.Test;

import java.util.*;

import static junit.framework.TestCase.assertEquals;

/**
 * https://leetcode.com/problems/permutations-ii/
 * #medium
 */
public class LC047PermutationsII {
    public class Solution {
        public List<List<Integer>> permuteUnique(int[] nums) {
            // Count each number
            TreeMap<Integer, Integer> counted = new TreeMap<>();
            for (int value : nums) {
                int count = 1;
                if (counted.containsKey(value)) {
                    count += counted.get(value);
                }
                counted.put(value, count);
            }
            // Generate the permutations
            List<List<Integer>> permutations = new LinkedList<>();
            generate(new ArrayList<>(), nums.length, counted, permutations);
            return permutations;
        }

        private void generate(ArrayList<Integer> permutation, int limit, TreeMap<Integer, Integer> counted, List<List<Integer>> permutations) {
            if (permutation.size() == limit) {
                permutations.add((List<Integer>) permutation.clone());
                return;
            }
            for (int key : counted.keySet()) {
                int count = counted.get(key);
                if (count != 0) {
                    int size = permutation.size();
                    permutation.add(key);
                    counted.put(key, count - 1);
                    generate(permutation, limit, counted, permutations);
                    counted.put(key, count);
                    permutation.remove(size);
                }
            }
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
        int[] nums = {1, 1, 2};
        List<List<Integer>> result = new Solution().permuteUnique(nums);
        Collections.sort(result, new DeepComparator());
        int[][] expected = {{1, 1, 2}, {1, 2, 1}, {2, 1, 1}};
        assertEquals(expected.length, result.size());
        for (int i = 0; i < expected.length; i++) {
            assertEquals(expected[i].length, result.get(i).size());
            for (int j = 0; j < expected[i].length; j++) {
                assertEquals(expected[i][j], result.get(i).get(j).intValue());
            }
        }
    }
}
