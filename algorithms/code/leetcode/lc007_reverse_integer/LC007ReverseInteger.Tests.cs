using Xunit;

namespace AlgorithmDesign.code.leetcode.lc007_reverse_integer
{
    public class SolutionTests
    {
        [Fact]
        public void Test2000000002()
        {
            Assert.Equal(2000000002, new Solution().Reverse(2000000002));
        }

        [Fact]
        public void TestMinus2147483648()
        {
            Assert.Equal(0, new Solution().Reverse(-2147483648));
        }

        [Fact]
        public void Test1000000003()
        {
            Assert.Equal(0, new Solution().Reverse(1000000003));
        }

        [Fact]
        public void Test1534236469()
        {
            Assert.Equal(0, new Solution().Reverse(1534236469));
        }

        [Fact]
        public void TestMinus321()
        {
            Assert.Equal(-123, new Solution().Reverse(-321));
        }
    }
}
