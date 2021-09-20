using System;
using Xunit;

namespace AlgorithmDesign.leetcode.lc029_divide_two_integers
{
    public class SolutionTests
    {
        [Fact]
        public void test_Minus1010369383_Minus2147483648()
        {
            Assert.Equal(-1010369383 / -2147483648, new Solution().Divide(-1010369383, -2147483648));
        }

        [Fact]
        public void test_Minus2147483648_Minus1()
        {
            Assert.Equal(Int32.MaxValue, new Solution().Divide(-2147483648, -1));
        }

        [Fact]
        public void test_divisor_0()
        {
            Assert.Equal(Int32.MaxValue, new Solution().Divide(1, 0));
        }

        [Fact]
        public void test_dividend_0()
        {
            Assert.Equal(0, new Solution().Divide(0, 1));
        }
    }
}
