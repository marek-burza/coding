using Xunit;

using System;
using System.Collections.Generic;
using System.Linq;

namespace AlgorithmDesign.code.leetcode.lc022_generate_parentheses
{
    public class SolutionTests
    {
        private void generic(string[] expected, int n)
        {
            List<string> result = new Solution().GenerateParenthesis(n).ToList();
            result.Sort();
            Array.Sort(expected);
            for (int i = 0; i < Math.Max(result.Count, expected.Length); i++)
            {
                Assert.Equal(expected[i], result[i]);
            }
        }

        [Fact]
        public void test_0()
        {
            string[] expected = { };
            generic(expected, 0);
        }

        [Fact]
        public void test_1()
        {
            string[] expected = { "()" };
            generic(expected, 1);
        }

        [Fact]
        public void test_2()
        {
            string[] expected = { "()()", "(())" };
            generic(expected, 2);
        }

        [Fact]
        public void test_3()
        {
            string[] expected = { "((()))", "(()())", "(())()", "()(())", "()()()" };
            generic(expected, 3);
        }

        [Fact]
        public void test_4()
        {
            string[] expected = {
                "(((())))", "((()()))", "((())())", "((()))()", "(()(()))", "(()()())", "(()())()", "(())(())",
                "(())()()", "()((()))", "()(()())", "()(())()", "()()(())", "()()()()"};
            generic(expected, 4);
        }
    }

}
