package leetcode;

import java.util.Stack;

/**
 * https://leetcode.com/problems/valid-parentheses/
 */
public class LC020ValidParentheses {
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

    public static void main(String[] arguments) {
        LC020ValidParentheses solution = new LC020ValidParentheses();
        System.out.println(solution.isValid(")"));
        System.out.println(solution.isValid("()"));
        System.out.println(solution.isValid("()[]{}"));
        System.out.println(solution.isValid("([)]"));
    }
}
