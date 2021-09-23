using Xunit;

namespace AlgorithmDesign.code.leetcode.lc012_integer_to_roman
{
    public class SolutionTests
    {
        [Fact]
        public void Test1234()
        {
            Assert.Equal("MCCXXXIV", new Solution().IntToRoman(1234));
        }

        [Fact]
        public void Test9()
        {
            Assert.Equal("IX", new Solution().IntToRoman(9));
        }
    }
}