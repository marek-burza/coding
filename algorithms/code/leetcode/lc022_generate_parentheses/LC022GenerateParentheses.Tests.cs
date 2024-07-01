using Xunit;

using System;
using System.Collections.Generic;
using System.Linq;

namespace AlgorithmDesign.code.leetcode.lc022_generate_parentheses
{
    public class SolutionTests
    {
        private void Generic(string[] expected, int n)
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
            Generic(expected, 0);
        }

        [Fact]
        public void test_1()
        {
            string[] expected = { "()" };
            Generic(expected, 1);
        }

        [Fact]
        public void test_2()
        {
            string[] expected = { "()()", "(())" };
            Generic(expected, 2);
        }

        [Fact]
        public void test_3()
        {
            string[] expected = { "((()))", "(()())", "(())()", "()(())", "()()()" };
            Generic(expected, 3);
        }

        [Fact]
        public void test_4()
        {
            string[] expected = {
                "(((())))", "((()()))", "((())())", "((()))()", "(()(()))", "(()()())", "(()())()", "(())(())",
                "(())()()", "()((()))", "()(()())", "()(())()", "()()(())", "()()()()"};
            Generic(expected, 4);
        }
    }

}
