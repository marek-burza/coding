using System;
using Xunit;

namespace AlgorithmDesign.leetcode.lc008_string_to_integer_a_to_i
{
    public class SolutionTests
    {
        [Fact]
        public void TestMinusMinus3241()
        {
            Assert.Equal(0, new Solution().MyAtoi("--3241"));
        }

        [Fact]
        public void TestPlusPlus3241()
        {
            Assert.Equal(0, new Solution().MyAtoi("++3241"));
        }

        [Fact]
        public void TestMinusPlus3241()
        {
            Assert.Equal(0, new Solution().MyAtoi("-+3241"));
        }

        [Fact]
        public void TestMinus3241()
        {
            Assert.Equal(-3241, new Solution().MyAtoi("-3241"));
        }

        [Fact]
        public void TestSpaceMinusPlus3241A()
        {
            Assert.Equal(-3241, new Solution().MyAtoi(" -3241a"));
        }

        [Fact]
        public void Test9223372036854775809()
        {
            Assert.Equal(Int32.MaxValue, new Solution().MyAtoi("9223372036854775809"));
        }

        [Fact]
        public void TestMinus9223372036854775809()
        {
            Assert.Equal(Int32.MinValue, new Solution().MyAtoi("-9223372036854775809"));
        }

        [Fact]
        public void TestNothing()
        {
            Assert.Equal(0, new Solution().MyAtoi("nothing"));
        }
    }
}
