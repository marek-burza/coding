package leetcode.lc241_different_ways_to_add_parentheses;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * https://leetcode.com/problems/different-ways-to-add-parentheses/ #medium
 * #dynamic-programing
 */
public final class LC241DifferentWaysToAddParentheses {
    private static long key(final int a, final int z) {
        return (((long) a) << 32) | (long) z;
    }

    private List<Integer> traverse(final String[] items, final int a, final int z,
            final HashMap<Long, List<Integer>> cache) {
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
                            default: // "*"
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

    public List<Integer> diffWaysToCompute(final String input) {
        String[] items = input.split("((?<=[+\\-*])|(?=[+\\-*]))");
        List<Integer> result = traverse(items, 0, items.length - 1, new HashMap<>());
        return result;
    }
}
