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
