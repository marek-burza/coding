// https://leetcode.com/problems/valid-parentheses/

using System.Collections.Generic;

namespace AlgorithmDesign.code.leetcode.lc020_valid_parentheses
{
    public class Solution
    {
        public bool IsValid(string s)
        {
            if (s == null) return false;
            if (s.Length == 0) return true;
            Stack<char> stack = new Stack<char>();
            foreach (char character in s)
            {
                switch (character)
                {
                    case '(':
                    case '{':
                    case '[':
                        stack.Push(character);
                        break;
                    case ')':
                        if (stack.Count == 0 || stack.Peek() != '(')
                        {
                            return false;
                        }
                        stack.Pop();
                        break;
                    case '}':
                        if (stack.Count == 0 || stack.Peek() != '{')
                        {
                            return false;
                        }
                        stack.Pop();
                        break;
                    case ']':
                        if (stack.Count == 0 || stack.Peek() != '[')
                        {
                            return false;
                        }
                        stack.Pop();
                        break;
                    default:
                        return false;
                }
            }
            return stack.Count == 0;
        }
    }
}
