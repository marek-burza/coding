package leetcode.lc020_valid_parentheses;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public final class LC020ValidParenthesesTests {
    @Test
    public void testGarbage() throws Exception {
        LC020ValidParentheses solution;
        solution = new LC020ValidParentheses();
        assertFalse(solution.isValid("*"));
    }

    @Test
    public void testRB() throws Exception {
        LC020ValidParentheses solution;
        solution = new LC020ValidParentheses();
        assertFalse(solution.isValid("("));
    }

    @Test
    public void testRE() throws Exception {
        LC020ValidParentheses solution;
        solution = new LC020ValidParentheses();
        assertFalse(solution.isValid(")"));
    }

    @Test
    public void testRBRE() throws Exception {
        LC020ValidParentheses solution;
        solution = new LC020ValidParentheses();
        assertTrue(solution.isValid("()"));
    }

    @Test
    public void testRBRESBSECBCE() throws Exception {
        LC020ValidParentheses solution;
        solution = new LC020ValidParentheses();
        assertTrue(solution.isValid("()[]{}"));
    }

    @Test
    public void testRBSBRESE() throws Exception {
        LC020ValidParentheses solution;
        solution = new LC020ValidParentheses();
        assertFalse(solution.isValid("([)]"));
    }

    @Test
    public void testRBCBRECE() throws Exception {
        LC020ValidParentheses solution;
        solution = new LC020ValidParentheses();
        assertFalse(solution.isValid("({)}"));
    }

    @Test
    public void testSBRBSERE() throws Exception {
        LC020ValidParentheses solution;
        solution = new LC020ValidParentheses();
        assertFalse(solution.isValid("[(])"));
    }

    @Test
    public void testCBRBCERE() throws Exception {
        LC020ValidParentheses solution;
        solution = new LC020ValidParentheses();
        assertFalse(solution.isValid("{(})"));
    }

    @Test
    public void testRESECE() throws Exception {
        LC020ValidParentheses solution;
        solution = new LC020ValidParentheses();
        assertFalse(solution.isValid(")"));
        assertFalse(solution.isValid("]"));
        assertFalse(solution.isValid("}"));
    }

    @Test
    public void testNothing() throws Exception {
        LC020ValidParentheses solution;
        solution = new LC020ValidParentheses();
        assertFalse(solution.isValid(null));
        assertTrue(solution.isValid(""));
    }
}
