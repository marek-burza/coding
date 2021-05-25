using Xunit;

namespace AlgorithmDesign.leetcode.lc014_longest_common_prefix
{
    public class SolutionTests
    {
        [Fact]
        public void test_Ala_AlaMaKota()
        {
            Assert.Equal("Ala", new Solution().LongestCommonPrefix(new[] { "Ala", "Ala Ma Kota" }));
        }

        [Fact]
        public void TestAaA()
        {
            Assert.Equal("a", new Solution().LongestCommonPrefix(new[] { "aa", "a" }));
        }

        [Fact]
        public void TestNone()
        {
            Assert.Equal("", new Solution().LongestCommonPrefix(new string[] { }));
        }

        [Fact]
        public void TestEmptyB()
        {
            Assert.Equal("", new Solution().LongestCommonPrefix(new[] { "", "b" }));
        }

        [Fact]
        public void TestNullB()
        {
            Assert.Equal("", new Solution().LongestCommonPrefix(new[] { null, "b" }));
        }

        [Fact]
        public void TestSame()
        {
            Assert.Equal("same", new Solution().LongestCommonPrefix(new[] { "same", "same" }));
        }

        [Fact]
        public void TestNull()
        {
            Assert.Equal("", new Solution().LongestCommonPrefix(null));
        }
    }
}
