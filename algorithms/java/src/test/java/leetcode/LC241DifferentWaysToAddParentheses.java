package leetcode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * https://leetcode.com/problems/different-ways-to-add-parentheses/
 * #medium
 */
public class LC241DifferentWaysToAddParentheses {
    public class Solution {
        private long key(int a, int z) {
            return (((long) a) << 32) | (long) z;
        }

        private List<Integer> traverse(String[] items, int a, int z, HashMap<Long, List<Integer>> cache) {
            List<Integer> result = cache.get(key(a, z));
            if (result == null) {
                result = new ArrayList<>();
            } else {
                return result;
            }
            if (a == z) {
                result.add(Integer.parseInt(items[a]));
            } else {
                for (int operator = a + 1; operator < z; operator += 2) {
                    List<Integer> before = traverse(items, a, operator - 1, cache);
                    List<Integer> after = traverse(items, operator + 1, z, cache);
                    for (int ante : before) {
                        for (int post : after) {
                            switch (items[operator]) {
                                case "+":
                                    result.add(ante + post);
                                    break;
                                case "-":
                                    result.add(ante - post);
                                    break;
                                case "*":
                                    result.add(ante * post);
                                    break;
                            }
                        }
                    }
                }
            }
            cache.put(key(a, z), result);
            return result;
        }

        public List<Integer> diffWaysToCompute(String input) {
            String[] items = input.split("((?<=[+\\-*])|(?=[+\\-*]))");
            List<Integer> result = traverse(items, 0, items.length - 1, new HashMap<>());
            return result;
        }
    }

    private void test(int[] expected, List<Integer> result) throws Exception {
        assertEquals(expected.length, result.size());
        Collections.sort(result);
        for (int i = 0; i < expected.length; i++) {
            assertEquals(expected[i], result.get(i).intValue());
        }
    }

    @Test
    public void test_example_1() throws Exception {
        int[] expected = {0, 2};
        test(expected, new Solution().diffWaysToCompute("2-1-1"));
    }

    @Test
    public void test_example_2() throws Exception {
        int[] expected = {-34, -14, -10, -10, 10};
        test(expected, new Solution().diffWaysToCompute("2*3-4*5"));
    }
}
