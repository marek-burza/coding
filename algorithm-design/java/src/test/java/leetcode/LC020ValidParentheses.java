package leetcode;

import java.util.Stack;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * https://leetcode.com/problems/valid-parentheses/
 */
public class LC020ValidParentheses {
    public class Solution {
        public boolean isValid(String s) {
            if (s == null) return false;
            if (s.length() == 0) return true;
            Stack<Character> stack = new Stack<>();
            for (int i = 0; i < s.length(); i++) {
                char character = s.charAt(i);
                switch (character) {
                    case '(':
                    case '{':
                    case '[':
                        stack.push(character);
                        break;
                    case ')':
                        if (stack.size() == 0 || stack.peek() != '(') {
                            return false;
                        } else {
                            stack.pop();
                        }
                        break;
                    case '}':
                        if (stack.size() == 0 || stack.peek() != '{') {
                            return false;
                        } else {
                            stack.pop();
                        }
                        break;
                    case ']':
                        if (stack.size() == 0 || stack.peek() != '[') {
                            return false;
                        } else {
                            stack.pop();
                        }
                        break;
                }
            }
            return stack.size() == 0;
        }
    }

    @Test
    public void test_RE() throws Exception {
        assertEquals(false, new Solution().isValid(")"));
    }

    @Test
    public void test_RB_RE() throws Exception {
        assertEquals(true, new Solution().isValid("()"));
    }

    @Test
    public void test_RB_RE_SB_SE_CB_CE() throws Exception {
        assertEquals(true, new Solution().isValid("()[]{}"));
    }

    @Test
    public void test_RB_SB_RE_SE() throws Exception {
        assertEquals(false, new Solution().isValid("([)]"));
    }
}
