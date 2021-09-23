using Xunit;

namespace AlgorithmDesign.code.leetcode.lc020_valid_parentheses
{
    public class SolutionTests
    {
        [Fact]
        public void test_RE()
        {
            Assert.False(new Solution().IsValid(")"));
        }

        [Fact]
        public void test_RB_RE()
        {
            Assert.True(new Solution().IsValid("()"));
        }

        [Fact]
        public void test_RB_RE_SB_SE_CB_CE()
        {
            Assert.True(new Solution().IsValid("()[]{}"));
        }

        [Fact]
        public void test_RB_SB_RE_SE()
        {
            Assert.False(new Solution().IsValid("([)]"));
        }

        [Fact]
        public void test_RB_CB_RE_CE()
        {
            Assert.False(new Solution().IsValid("({)}"));
        }

        [Fact]
        public void test_SB_RB_SE_RE()
        {
            Assert.False(new Solution().IsValid("[(])"));
        }

        [Fact]
        public void test_RE__SE__CE()
        {
            Assert.False(new Solution().IsValid(")"));
            Assert.False(new Solution().IsValid("]"));
            Assert.False(new Solution().IsValid("}"));
        }

        [Fact]
        public void test_nothing()
        {
            Assert.False(new Solution().IsValid(null));
            Assert.True(new Solution().IsValid(""));
        }
    }
}
