using Xunit;

namespace AlgorithmDesign.code.leetcode.lc009_palindrome_number
{
    public class SolutionTests
    {
        [Fact]
        public void Test213()
        {
            Assert.False(new Solution().IsPalindrome(213));
        }

        [Fact]
        public void Test456()
        {
            Assert.False(new Solution().IsPalindrome(456));
        }

        [Fact]
        public void Test454()
        {
            Assert.True(new Solution().IsPalindrome(454));
        }

        [Fact]
        public void Test99()
        {
            Assert.True(new Solution().IsPalindrome(99));
        }

        [Fact]
        public void Test1()
        {
            Assert.True(new Solution().IsPalindrome(1));
        }

        [Fact]
        public void Test10()
        {
            Assert.False(new Solution().IsPalindrome(10));
        }

        [Fact]
        public void TestMinus1()
        {
            Assert.False(new Solution().IsPalindrome(-1));
        }

        [Fact]
        public void Test0()
        {
            Assert.True(new Solution().IsPalindrome(0));
        }
    }
}
