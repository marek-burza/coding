package leetcode;

import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertEquals;

/**
 * https://leetcode.com/problems/generate-parentheses/
 */
public class LC022GenerateParentheses {
    public class Solution {
        public void generateParenthesis(String prefix, int standing, int n, Set<String> found) {
            if (n == 0 && standing == 0) {
                found.add(prefix);
                return;
            }
            // open
            if (n > 0) {
                generateParenthesis(prefix + "(", standing + 1, n - 1, found);
            }
            // close
            if (standing > 0) {
                generateParenthesis(prefix + ")", standing - 1, n, found);
            }
        }

        public List<String> generateParenthesis(int n) {
            if (n == 0) return new ArrayList<>();
            Set<String> found = new TreeSet<>();
            generateParenthesis("(", 1, n - 1, found);
            List<String> result = new ArrayList<>();
            result.addAll(found);
            return result;
        }
    }

    private void test(String[] expected, int n) throws Exception {
        List<String> result = new Solution().generateParenthesis(n);
        Collections.sort(result);
        Arrays.sort(expected);
        for (int i = 0; i < Math.max(result.size(), expected.length); i++) {
            assertEquals(expected[i], result.get(i));
        }
    }

    @Test
    public void test_0() throws Exception {
        String[] expected = {};
        test(expected, 0);
    }

    @Test
    public void test_1() throws Exception {
        String[] expected = {"()"};
        test(expected, 1);
    }

    @Test
    public void test_2() throws Exception {
        String[] expected = {"()()", "(())"};
        test(expected, 2);
    }

    @Test
    public void test_3() throws Exception {
        String[] expected = {"((()))", "(()())", "(())()", "()(())", "()()()"};
        test(expected, 3);
    }

    @Test
    public void test_4() throws Exception {
        String[] expected = {
                "(((())))", "((()()))", "((())())", "((()))()", "(()(()))", "(()()())", "(()())()", "(())(())",
                "(())()()", "()((()))", "()(()())", "()(())()", "()()(())", "()()()()"};
        test(expected, 4);
    }
}
