using System;
using System.Collections.Generic;
using System.Linq;
using Xunit;

namespace AlgorithmDesign.code.leetcode.lc017_letter_combinations_of_a_phone_number
{
    public class SolutionTests
    {
        [Fact]
        public void TestNothing()
        {
            IList<string> result = new Solution().LetterCombinations(null);
            Assert.Equal(0, result.Count);
        }

        [Fact]
        public void TestEmpty()
        {
            IList<string> result = new Solution().LetterCombinations("");
            Assert.Equal(0, result.Count);
        }

        [Fact]
        public void TestExample()
        {
            string[] expected = { "ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf" };
            Array.Sort(expected);
            IList<string> result = new Solution().LetterCombinations("23");
            List<string> sorted = result.ToList();
            sorted.Sort();
            Assert.Equal(expected.Length, sorted.Count);
            for (int i = 0; i < expected.Length; i++)
            {
                Assert.Equal(expected[i], sorted[i]);
            }
        }
    }
}
