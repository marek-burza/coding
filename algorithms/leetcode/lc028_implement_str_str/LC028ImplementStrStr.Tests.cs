using Xunit;

namespace AlgorithmDesign.leetcode.lc028_implement_str_str
{
    public class SolutionTests
    {
        [Fact]
        public void test_empty()
        {
            Assert.Equal(0, new Solution().StrStr("", ""));
        }

        [Fact]
        public void test_mississippi_a()
        {
            Assert.Equal(-1, new Solution().StrStr("mississippi", "a"));
        }

        [Fact]
        public void test_mississippi_si()
        {
            Assert.Equal(3, new Solution().StrStr("mississippi", "si"));
        }

        [Fact]
        public void test_bigger_in_smaller()
        {
            Assert.Equal(-1, new Solution().StrStr("", "test"));
        }
    }
}
