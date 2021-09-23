using Xunit;

namespace AlgorithmDesign.leetcode.lc003_longest_substring_without_repeating_characters
{
    public class SolutionTests
    {
        [Fact]
        public void TestAbcabcbb()
        {
            Assert.Equal(3, new Solution().LengthOfLongestSubstring("bcabcbb"));
        }

        [Fact]
        public void TestBbbbb()
        {
            Assert.Equal(1, new Solution().LengthOfLongestSubstring("bbbbb"));
        }

        [Fact]
        public void TestDvdf()
        {
            Assert.Equal(3, new Solution().LengthOfLongestSubstring("dvdf"));
        }
    }
}
