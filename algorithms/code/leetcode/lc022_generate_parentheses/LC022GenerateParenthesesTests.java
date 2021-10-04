package leetcode.lc022_generate_parentheses;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public final class LC022GenerateParenthesesTests {
    private void test(final String[] expected, final int n) throws Exception {
        LC022GenerateParentheses solution;
        solution = new LC022GenerateParentheses();
        List<String> result = solution.generateParenthesis(n);
        Collections.sort(result);
        Arrays.sort(expected);
        for (int i = 0; i < Math.max(result.size(), expected.length); i++) {
            assertEquals(expected[i], result.get(i));
        }
    }

    @Test
    public void test0() throws Exception {
        String[] expected = {};
        test(expected, 0);
    }

    @Test
    public void test1() throws Exception {
        String[] expected = { "()" };
        test(expected, 1);
    }

    @Test
    public void test2() throws Exception {
        String[] expected = { "()()", "(())" };
        test(expected, 2);
    }

    @Test
    public void test3() throws Exception {
        String[] expected = { "((()))", "(()())", "(())()", "()(())", "()()()" };
        test(expected, 3);
    }

    @Test
    public void test4() throws Exception {
        String[] expected = { "(((())))", "((()()))", "((())())", "((()))()", "(()(()))", "(()()())", "(()())()",
                "(())(())", "(())()()", "()((()))", "()(()())", "()(())()", "()()(())", "()()()()" };
        test(expected, 4);
    }
}
