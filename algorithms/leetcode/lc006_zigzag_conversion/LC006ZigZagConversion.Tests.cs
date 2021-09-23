using Xunit;

namespace AlgorithmDesign.leetcode.lc006_zigzag_conversion
{
    public class SolutionTests
    {
        [Fact]
        public void Test1()
        {
            Assert.Equal("PAHNAPLSIIGYIR", new Solution().Convert("PAYPALISHIRING", 3));
        }

        [Fact]
        public void Test2()
        {
            Assert.Equal("PINALSIGYAHRPI", new Solution().Convert("PAYPALISHIRING", 4));
        }

        [Fact]
        public void Test3()
        {
            Assert.Equal("A", new Solution().Convert("A", 1));
        }

        [Fact]
        public void TestAbcd()
        {
            Assert.Equal("ABDC", new Solution().Convert("ABCD", 3));
        }

        [Fact]
        public void TestAbc()
        {
            Assert.Equal("ACB", new Solution().Convert("ABC", 2));
        }

        [Fact]
        public void TestNothing()
        {
            Assert.Null(new Solution().Convert(null, 2));
        }

        [Fact]
        public void TestZero()
        {
            Assert.Null(new Solution().Convert("A", 0));
        }
    }
}
