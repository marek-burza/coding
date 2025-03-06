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
package leetcode.lc241_different_ways_to_add_parentheses;

import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * https://leetcode.com/problems/different-ways-to-add-parentheses/ #medium
 */
public final class LC241DifferentWaysToAddParenthesesTests {
    private void test(final int[] expected, final List<Integer> result) throws Exception {
        assertEquals(expected.length, result.size());
        Collections.sort(result);
        for (int i = 0; i < expected.length; i++) {
            assertEquals(expected[i], result.get(i).intValue());
        }
    }

    @Test
    public void testExample1() throws Exception {
        int[] expected = { 0, 2 };
        test(expected, new LC241DifferentWaysToAddParentheses().diffWaysToCompute("2-1-1"));
    }

    @Test
    public void testExample2() throws Exception {
        int[] expected = { -34, -14, -10, -10, 10 };
        test(expected, new LC241DifferentWaysToAddParentheses().diffWaysToCompute("2*3-4*5"));
    }

    @Test
    public void testOther() throws Exception {
        int[] expected = { 7 };
        test(expected, new LC241DifferentWaysToAddParentheses().diffWaysToCompute("3+4"));
    }
}
