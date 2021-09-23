using Xunit;

namespace AlgorithmDesign.code.leetcode.lc013_roman_to_integer
{
    public class SolutionTests
    {
        [Fact]
        public void TestMcmliv()
        {
            Assert.Equal(1954, new Solution().RomanToInt("MCMLIV"));
        }

        [Fact]
        public void TestNothing()
        {
            Assert.Equal(0, new Solution().RomanToInt(null));
        }
    }
}
