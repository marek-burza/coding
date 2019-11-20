package leetcode;

import java.util.*;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * https://leetcode.com/problems/3sum/
 */
public class LC015ThreeSum {
    public class Solution {
        public List<List<Integer>> threeSum(int[] nums) {
            if (nums.length < 3) {
                return new ArrayList<>();
            }
            // Sort all values
            Arrays.sort(nums);
            // Prepare an array with repetition count
            int[] counts = new int[nums.length];
            Arrays.fill(counts, 1);
            // Prepare array with unique values and collect their count
            int index = 0;
            for (int i = 1; i < nums.length; i++) {
                if (nums[index] != nums[i]) {
                    index++;
                    nums[index] = nums[i];
                } else {
                    counts[index]++;
                }
            }
            int length = index + 1;
            // Search for triplets
            List<List<Integer>> result = new ArrayList<>();
            for (int i = 0; i < length; i++) {
                for (int j = i; j < length; j++) {
                    if (i == j && counts[j] < 2) {
                        continue;
                    }
                    for (int k = j; k < length; k++) {
                        if (i == k && counts[k] < 2) {
                            continue;
                        }
                        if (j == k && counts[k] < 2) {
                            continue;
                        }
                        if (i == j && j == k && counts[i] < 3) {
                            continue;
                        }
                        if (nums[i] + nums[j] + nums[k] == 0) {
                            List<Integer> triplet = new ArrayList<>();
                            triplet.add(nums[i]);
                            triplet.add(nums[j]);
                            triplet.add(nums[k]);
                            result.add(triplet);
                        }
                    }
                }
            }
            return result;
        }
    }

    private void test(int[][] expected, List<List<Integer>> result) throws Exception {
        assertEquals(expected.length, result.size());
        for (int[] entry : expected) {
            for (List<Integer> candidate : result) {
                boolean matching = true;
                for (int i = 0; i < 3; i++) {
                    if (entry[i] != candidate.get(i)) {
                        matching = false;
                        break;
                    }
                }
                if (matching) {
                    result.remove(candidate);
                    break;
                }
            }
        }
        assertEquals(0, result.size());
    }

    @Test
    public void test_example() throws Exception {
        int[] s = {-1, 0, 1, 2, -1, -4};
        int[][] expected = {{-1, 0, 1}, {-1, -1, 2}};
        test(expected, new Solution().threeSum(s));
    }

    @Test
    public void test_oversized() throws Exception {
        int[] s = {14, -11, -2, -3, 4, -3, -3, -8, -15, 11, 11, -6, -14, -13, 5, -10, -13, 0, -12, -8, 14, -12, -10, 2, -4, 9, 13, 10, 2, 7, -2, -7, 4, 11, 5, -7, -15, 10, -7, -14, 6, 11, -5, 7, 6, 8, 5, 8, -7, 8, -15, 14, 11, 13, 1, -15, 7, 0, 10, -14, 14, -15, -14, 3, 4, 6, 4, 4, -7, 12, 5, 14, 0, 8, 7, 13, 1, -11, -2, 9, 12, -1, 8, 0, -11, -5, 0, 11, 2, -13, 4, 1, -12, 5, -10, -1, -12, 9, -12, -11, -2, 9, -12, 5, -6, 2, 4, 10, 6, -13, 4, 3, -7, -11, 11, -3, -9, -4, -15, 8, -9, -4, -13, -14, 8, 14};
        int[][] expected = {{-15, 1, 14}, {-15, 2, 13}, {-15, 3, 12}, {-15, 4, 11}, {-15, 5, 10}, {-15, 6, 9}, {-15, 7, 8}, {-14, 0, 14}, {-14, 1, 13}, {-14, 2, 12}, {-14, 3, 11}, {-14, 4, 10}, {-14, 5, 9}, {-14, 6, 8}, {-14, 7, 7}, {-13, -1, 14}, {-13, 0, 13}, {-13, 1, 12}, {-13, 2, 11}, {-13, 3, 10}, {-13, 4, 9}, {-13, 5, 8}, {-13, 6, 7}, {-12, -2, 14}, {-12, -1, 13}, {-12, 0, 12}, {-12, 1, 11}, {-12, 2, 10}, {-12, 3, 9}, {-12, 4, 8}, {-12, 5, 7}, {-12, 6, 6}, {-11, -3, 14}, {-11, -2, 13}, {-11, -1, 12}, {-11, 0, 11}, {-11, 1, 10}, {-11, 2, 9}, {-11, 3, 8}, {-11, 4, 7}, {-11, 5, 6}, {-10, -4, 14}, {-10, -3, 13}, {-10, -2, 12}, {-10, -1, 11}, {-10, 0, 10}, {-10, 1, 9}, {-10, 2, 8}, {-10, 3, 7}, {-10, 4, 6}, {-10, 5, 5}, {-9, -5, 14}, {-9, -4, 13}, {-9, -3, 12}, {-9, -2, 11}, {-9, -1, 10}, {-9, 0, 9}, {-9, 1, 8}, {-9, 2, 7}, {-9, 3, 6}, {-9, 4, 5}, {-8, -6, 14}, {-8, -5, 13}, {-8, -4, 12}, {-8, -3, 11}, {-8, -2, 10}, {-8, -1, 9}, {-8, 0, 8}, {-8, 1, 7}, {-8, 2, 6}, {-8, 3, 5}, {-8, 4, 4}, {-7, -7, 14}, {-7, -6, 13}, {-7, -5, 12}, {-7, -4, 11}, {-7, -3, 10}, {-7, -2, 9}, {-7, -1, 8}, {-7, 0, 7}, {-7, 1, 6}, {-7, 2, 5}, {-7, 3, 4}, {-6, -6, 12}, {-6, -5, 11}, {-6, -4, 10}, {-6, -3, 9}, {-6, -2, 8}, {-6, -1, 7}, {-6, 0, 6}, {-6, 1, 5}, {-6, 2, 4}, {-6, 3, 3}, {-5, -5, 10}, {-5, -4, 9}, {-5, -3, 8}, {-5, -2, 7}, {-5, -1, 6}, {-5, 0, 5}, {-5, 1, 4}, {-5, 2, 3}, {-4, -4, 8}, {-4, -3, 7}, {-4, -2, 6}, {-4, -1, 5}, {-4, 0, 4}, {-4, 1, 3}, {-4, 2, 2}, {-3, -3, 6}, {-3, -2, 5}, {-3, -1, 4}, {-3, 0, 3}, {-3, 1, 2}, {-2, -2, 4}, {-2, -1, 3}, {-2, 0, 2}, {-2, 1, 1}, {-1, -1, 2}, {-1, 0, 1}, {0, 0, 0}};
        test(expected, new Solution().threeSum(s));
    }

    @Test
    public void test_empty() throws Exception {
        int[] s = {};
        assertEquals(0, new Solution().threeSum(s).size());
    }
}
